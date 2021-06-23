package sinch.scoredashboard.data;

public class UserRankInput {
    private String categoryName;
    private String userName; 
    private String rank;
    private String level;
    private String xp;
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
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getXp() {
        return xp;
    }
    public void setXp(String xp) {
        this.xp = xp;
    }
}
