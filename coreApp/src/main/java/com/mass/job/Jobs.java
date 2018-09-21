package com.mass.job;

import com.mass.dao.ActivitiesMapper;
import com.mass.dao.NoticeMapper;
import com.mass.dao.RegisterMapper;
import com.mass.dao.VoteMapper;
import com.mass.dto.query.NoticeQuery;
import com.mass.dto.query.VoteQuery;
import com.mass.entity.Notice;
import com.mass.entity.Vote;
import com.mass.feign.FeignService;
import com.mass.feign.dto.SysUser;
import com.mass.service.NoticeMemService;
import com.mass.service.NoticeService;
import com.mass.service.VoteMemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Jobs {

    private static Logger logger = Logger.getLogger(Jobs.class);
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private FeignService feignService;
    @Autowired
    private VoteMemService voteMemService;
    @Autowired
    private NoticeMemService memService;
    @Autowired
    private NoticeMapper noticeMapper;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private ActivitiesMapper activitiesMapper;

    @Scheduled(fixedDelay = 60000)
    public void checkNotice() {
        List<Vote> voteList = voteMapper.selectByStart(new Date());
        for (Vote vote : voteList) {
            String[] names = vote.getRange().split(",");
            List<String> nameList = Arrays.asList(names);
            List<SysUser> users = feignService.getUserByMassName(nameList);
            logger.info(vote.getName());
            voteMemService.create(users, vote.getId());
            vote.setLastModifyTime(new Date());
            vote.setStatus(2);
            voteMapper.updateByPrimaryKey(vote);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void checkVote() {
        List<Notice> notices = noticeMapper.selectByStart(new Date());
        for (Notice notice : notices) {
            String[] names = notice.getRange().split(",");
            List<String> nameList = Arrays.asList(names);
            List<SysUser> users = feignService.getUserByMassName(nameList);
            logger.info(notice.getName());
            memService.create(users, notice);
            notice.setLastModifyTime(new Date());
            notice.setStatus(2);
            noticeMapper.updateByPrimaryKey(notice);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void closeVote() {
        voteMapper.closeVote();
        noticeMapper.closeNotice();
    }

    @Scheduled(fixedDelay = 60000)
    public void signJob() {
        registerMapper.checkClose(new Date());
        registerMapper.checkStart(new Date());
    }
    @Scheduled(cron ="0 1 0 * * ?")
    public void activityJob() {
        String date = dateFormat.format(new Date());
        activitiesMapper.checkActivity(date);
    }
}
