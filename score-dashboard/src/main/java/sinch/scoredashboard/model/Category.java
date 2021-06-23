package sinch.scoredashboard.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    private String name;
    @Transient
    private Iterable<UserRank> userRankDetails;

    public Iterable<UserRank> getUserRankDetails() {
        return userRankDetails;
    }

    public void setUserRankDetails(Iterable<UserRank> userRankDetails) {
        this.userRankDetails = userRankDetails;
    }

    public Category() {
        
    }

    public Category(String name) {
        this.name = name;
    }

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this. name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    
}
