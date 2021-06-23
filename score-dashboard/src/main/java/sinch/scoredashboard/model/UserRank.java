package sinch.scoredashboard.model;


import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class UserRank implements Comparable<UserRank>{

    @Id
    @JsonIgnore
    private UUID id;
    private String categoryName;
    private String userName; 
    private long rank;
    private long level;
    private long xp;

    public UserRank() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public long getRank() {
        return rank;
    }
    public void setRank(long rank) {
        this.rank = rank;
    }
    public long getLevel() {
        return level;
    }
    public void setLevel(long level) {
        this.level = level;
    }
    public long getXp() {
        return xp;
    }
    public void setXp(long xp) {
        this.xp = xp;
    }
    @Override
	public int compareTo(UserRank d) {
		return (int)(this.rank - d.getRank());
	}

    @Override
	public String toString() {
		return userName + rank;
	}
}
