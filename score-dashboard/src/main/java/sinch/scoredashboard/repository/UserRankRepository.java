package sinch.scoredashboard.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sinch.scoredashboard.model.UserRank;

public interface UserRankRepository extends CrudRepository<UserRank, UUID>{
    List<UserRank> getByCategoryNameOrderByRankAsc(String categoryName, Pageable pageable);
    default List<UserRank> findCategoriesByName(String categoryName, int count){
        if(categoryName.equals("Overall"))
        {
            List<UserRank> userRanks = new ArrayList<UserRank>();
            findAll().forEach(userRanks::add);
            Collections.sort(userRanks);
            return userRanks.subList(0, count);
        }
        return getByCategoryNameOrderByRankAsc(categoryName, PageRequest.of(0, count));
    }
}