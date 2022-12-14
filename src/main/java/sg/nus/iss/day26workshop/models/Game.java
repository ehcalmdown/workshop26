package sg.nus.iss.day26workshop.models;

import org.bson.Document;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return users_rated;
    }

    public void setUsersRated(Integer usersRated) {
        this.users_rated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Game [gid= "
                .concat("" + this.gid)
                .concat(", name=")
                .concat(this.name)
                .concat(", year=")
                .concat("" + this.year)
                .concat(", ranking=")
                .concat("" + this.ranking)
                .concat(", usersRated=")
                .concat("" + this.users_rated)
                .concat(", url=")
                .concat(this.url)
                .concat(", image=")
                .concat(this.image)
                .concat("]");
    }

    public static Game create(Document d) {
        Game g = new Game();
        g.setGid(d.getInteger("gid"));
        g.setName(d.getString("name"));
        g.setYear(d.getInteger("year"));
        g.setRanking(d.getInteger("ranking"));
        g.setUsersRated(d.getInteger("users_rated"));
        g.setUrl(d.getString("url"));
        g.setImage(d.getString("image"));

        return g;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("gid", getGid())
                .add("name", getName())
                .add("year", getYear())
                .add("ranking", getRanking())
                .add("users_rated", getUsersRated() != null ? getUsersRated() : 0)
                .add("url", getUrl())
                .add("image", getImage())
                .build();
    }

}
