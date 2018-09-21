package test.mass.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.mass.dto.query.VoteQuery;
import com.mass.service.VoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;
import test.mass.TestApplication;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestVoteService {


  @Autowired
  private VoteService voteService;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testQuest() throws Exception {
    VoteQuery query = new VoteQuery();
    query.setEndTime(new Timestamp(System.currentTimeMillis()));
    PageInfo info = voteService.query(query, 1);
    objectMapper.writeValueAsString(info);

  }
}
