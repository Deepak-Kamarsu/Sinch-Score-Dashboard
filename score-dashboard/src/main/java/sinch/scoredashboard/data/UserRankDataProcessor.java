package sinch.scoredashboard.data;


import java.util.UUID;
import org.springframework.batch.item.ItemProcessor;
import sinch.scoredashboard.model.UserRank;

public class UserRankDataProcessor implements ItemProcessor<UserRankInput, UserRank> {


  @Override
  public UserRank process(final UserRankInput input) throws Exception {
    var userRank = new UserRank();
    userRank.setId(UUID.randomUUID());
    userRank.setCategoryName(input.getCategoryName());
    userRank.setUserName(input.getUserName());
    userRank.setRank(Long.parseLong(input.getRank()));
    userRank.setLevel(Long.parseLong(input.getLevel()));
    userRank.setXp(Long.parseLong(input.getXp()));
    return userRank;
  }

}