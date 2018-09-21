package com.mass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mass.common.exception.ServiceException;
import com.mass.common.utils.MassUtils;
import com.mass.dao.FinanceMapper;
import com.mass.dao.GoodsMapper;
import com.mass.dto.create.CreateFinanceDto;
import com.mass.dto.create.CreateGoodsDto;
import com.mass.dto.query.FinMassQuery;
import com.mass.dto.query.FinanceQuery;
import com.mass.dto.result.FinanceDto;
import com.mass.dto.result.GoodsDto;
import com.mass.dto.update.AuditFinanceDto;
import com.mass.entity.Finance;
import com.mass.entity.Goods;
import com.mass.service.FinanceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceMapper financeMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    private static final int DEF_DIV_SCALE = 10;

    @Override
    public void create(CreateFinanceDto dto) {
        Finance finance = dtoToEntity(dto);
        finance.setStatus(4);
        if (finance.getType().equals("报销")) {
            finance.setStatus(1);
        }
        finance.buildDefaultTimeStamp();
        finance.setTimes(new Date());
        if (dto.getGoodsDtoList().size() == 0) {
            throw new ServiceException("请填写物资信息");
        }
        List<Goods> goodsList = dtoToEntity(dto,
                finance.getId(), finance.getStatus());
        if (goodsList.size() == 0) {
            throw new ServiceException("物资信息无效");
        }
        financeMapper.insert(finance);
        for (Goods goods : goodsList) {
            goods.setFinanceId(finance.getId());
            goods.setStatus(finance.getStatus());
            goodsMapper.insert(goods);
        }
    }

    @Override
    public void update(CreateFinanceDto dto) {
        Finance finance = financeMapper.selectByPrimaryKey(dto.getId());
        if (null == finance) {
            return;
        }
        if (finance.getStatus() != 1 && finance.getStatus() != 2) {
            throw new ServiceException("财务记录已经不可修改");
        }
        finance = dtoToEntity(dto);
        finance.setStatus(1);
        List<Goods> goodsList = dtoToEntity(dto,
                finance.getId(), finance.getStatus());
        if (goodsList.size() == 0) {
            throw new ServiceException("物资信息无效");
        }
        financeMapper.updateByPrimaryKey(finance);
        goodsMapper.delectByFinanceId(dto.getId());
        for (Goods goods : goodsList) {
            goods.setStatus(1);
            goodsMapper.insert(goods);
        }
    }

    @Override
    public PageInfo<FinanceDto> query(FinanceQuery query, Integer page) {
        PageHelper.startPage(page, 10);
        List<FinanceDto> list = financeMapper.selectByQuery(query);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo<FinanceDto> query(FinMassQuery query, Integer page) {
        if (null == query.getMassId()) {
            throw new ServiceException("社团ID不存在");
        }
        FinanceQuery query1 = new FinanceQuery();
        query1.setMassId(query.getMassId());
        query1.setType(query.getType());
        query1.setStatus(query.getStatus());
        return query(query1, page);
    }

    @Override
    public FinanceDto getById(Long id) {
        Finance finance = financeMapper.selectByPrimaryKey(id);
        List<Goods> goodsList = goodsMapper.selectByFinanceId(id);
        return entityToDto(finance, goodsList);
    }

    @Override
    public void audit(AuditFinanceDto dto) {
        Finance finance = financeMapper.selectByPrimaryKey(dto.getId());
        if (finance.getStatus() != 1) {
            throw new ServiceException("改记录不需要审核");
        }
        finance.setStatus(2);
        if (dto.getStatus() == 1) {
            finance.setStatus(3);
        } else if (dto.getStatus() == 0) {
            finance.setStatus(2);
        } else if (dto.getStatus() == -1) {
            finance.setStatus(0);
        } else {
            throw new ServiceException("未知审核类型");
        }
        finance.setLastModifyTime(MassUtils.getCurrentTimestamp());
        financeMapper.updateByPrimaryKey(finance);
        List<Goods> goodsList = goodsMapper.selectByFinanceId(dto.getId());
        for (Goods goods : goodsList) {
            goods.setStatus(finance.getStatus());
            goods.setLastModifyTime(MassUtils.getCurrentTimestamp());
            goodsMapper.updateByPrimaryKey(goods);
        }
    }

    @Override
    public void settle(Long id) {
        Finance finance = financeMapper.selectByPrimaryKey(id);
        if (finance.getStatus() != 3) {
            throw new ServiceException("改记录还不能结算");
        }
        finance.setStatus(4);
        finance.setLastModifyTime(MassUtils.getCurrentTimestamp());
        financeMapper.updateByPrimaryKey(finance);
        List<Goods> goodsList = goodsMapper.selectByFinanceId(id);
        for (Goods goods : goodsList) {
            goods.setStatus(finance.getStatus());
            goods.setLastModifyTime(MassUtils.getCurrentTimestamp());
            goodsMapper.updateByPrimaryKey(goods);
        }
    }

    @Override
    public PageInfo<FinanceDto> getByAudit(Integer page) {
        PageHelper.startPage(page, 10);
        FinanceQuery query1 = new FinanceQuery();
        query1.setStatus(1);
        List<FinanceDto> list = financeMapper.selectByQuery(query1);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    private Finance dtoToEntity(CreateFinanceDto dto) {
        Finance finance = new Finance();
        finance.setActivitiesId(dto.getActivitiesId());
        finance.setActivitiesName(dto.getActivitiesName());
        finance.setMassId(dto.getMassId());
        finance.setMassName(dto.getMassName());
        finance.setName(dto.getName());
        finance.setTimes(dto.getTimes());
        finance.setTotalFree(buildTotalFree(dto));
        finance.setType(dto.getType());
        finance.setStatus(4);
        if (!"报备".equals(finance.getType())) {
            finance.setStatus(1);
        }
        finance.setId(dto.getId());
        return finance;
    }

    private List<Goods> dtoToEntity(CreateFinanceDto dtos, Long financeId, Integer status) {
        List<Goods> goods = new ArrayList<Goods>(dtos.getGoodsDtoList().size());
        for (CreateGoodsDto dto : dtos.getGoodsDtoList()) {
            if (StringUtils.isEmpty(dto.getName())) {
                continue;
            }
            if (dto.getPrice().intValue() == 0) {
                continue;
            }
            if (dto.getNum().intValue() == 0) {
                continue;
            }
            goods.add(dtoToEntity(dto, financeId, status, dtos));
        }
        return goods;
    }

    private BigDecimal buildTotalFree(CreateFinanceDto dto) {
        BigDecimal totalFree = new BigDecimal(0);
        List<CreateGoodsDto> goodsDtos = dto.getGoodsDtoList();
        for (CreateGoodsDto goodsDto : goodsDtos) {
            BigDecimal num = goodsDto.getNum();
            BigDecimal price = goodsDto.getPrice();
            BigDecimal goodFree = price.multiply(num);
            totalFree = totalFree.add(goodFree);
        }
        return totalFree;
    }

    private Goods dtoToEntity(CreateGoodsDto dto, Long financeId, Integer status,
                              CreateFinanceDto financeDto) {
        Goods goods = new Goods();
        goods.setFinanceId(financeId);
        goods.setIsExpendable(dto.getIsExpendable());
        goods.setIsExpense(dto.getIsExpense());
        goods.setMassId(financeDto.getMassId());
        goods.setMassName(financeDto.getMassName());
        goods.setNum(dto.getNum());
        goods.setName(dto.getName());
        goods.setPrice(dto.getPrice());
        goods.setStatus(status);
        return goods;
    }

    private FinanceDto entityToDto(Finance finance, List<Goods> goodsList) {
        FinanceDto dto = new FinanceDto();
        dto.setActivitiesId(finance.getActivitiesId());
        dto.setActivitiesName(finance.getActivitiesName());
        dto.setMassId(finance.getMassId());
        dto.setName(finance.getName());
        dto.setId(finance.getId());
        dto.setMassName(finance.getMassName());
        dto.setStatus(finance.getStatus());
        dto.setTimes(finance.getTimes());
        dto.setTotalFree(finance.getTotalFree());
        dto.setType(finance.getType());
        dto.setGoodsDtoList(entityToDto(goodsList));
        return dto;
    }

    private List<GoodsDto> entityToDto(List<Goods> goodsList) {
        List<GoodsDto> result = new ArrayList<>(goodsList.size());
        for (Goods goods : goodsList) {
            result.add(entityToDto(goods));
        }
        return result;
    }

    private GoodsDto entityToDto(Goods goods) {
        GoodsDto dto = new GoodsDto();
        dto.setFinanceId(goods.getFinanceId());
        dto.setIsExpendable(goods.getIsExpendable());
        dto.setIsExpense(goods.getIsExpense());
        dto.setMassId(goods.getMassId());
        dto.setMassName(goods.getMassName());
        dto.setName(goods.getName());
        dto.setNum(goods.getNum());
        dto.setPrice(goods.getPrice());
        dto.setStatus(goods.getStatus());
        dto.setId(goods.getId());
        return dto;
    }
}

