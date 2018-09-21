package com.mass.dao;

import com.mass.dto.query.VoteQuery;
import com.mass.dto.result.VoteDto;
import com.mass.entity.Vote;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

/**
 * 投票DAO接口
 *
 * @author liangliang
 */
public interface VoteMapper {
    /**
     * 按照主键逻辑删除投票
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 增加一个投票
     *
     * @param record 投票主信息
     * @return 影响行数
     */
    Long insert(Vote record);

    /**
     * 按照主键查找投票
     *
     * @param id
     * @return
     */
    Vote selectByPrimaryKey(Long id);

    /**
     * 按照主键更新投票
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Vote record);

    /**
     * 查询投票信息
     *
     * @param query
     * @return
     */
    List<Vote> selectByQuery(VoteQuery query);

    List<Vote> selectByStart(Date startTime);

    int closeVote();


    List<VoteDto> selectMyVote(@Param("sno") String sno, @Param("status") Integer status);
}