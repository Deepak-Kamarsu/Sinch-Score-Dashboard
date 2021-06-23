package sinch.scoredashboard.data;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sinch.scoredashboard.model.Category;


@Component
public class UserRankJobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(UserRankJobCompletionNotificationListener.class);

  private final EntityManager em;
  Map<String, Category> categoryData = new HashMap<>();

  @Autowired
  public UserRankJobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  // @Override
  // public void afterJob(JobExecution jobExecution) {
  //   if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
  //     log.info("!!! JOB FINISHED! Time to verify the results");

  //     jdbcTemplate.query("SELECT top 5 id, category_name FROM user_rank",
  //       (rs, row) -> " name "+ rs.getString(1)
  //     ).forEach(str -> System.out.println(str));
  //   }
  // }
  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      em.createQuery("select ur.categoryName, count(*) from UserRank ur group by ur.categoryName", Object[].class)
      .getResultList()
      .stream()
      .map(e -> new Category((String) e[0]))
      .forEach(category -> categoryData.put(category.getName(), category));
    categoryData.values().forEach(category -> em.persist(category));
    categoryData.values().forEach(category -> System.out.println(category));

    }




  }
}