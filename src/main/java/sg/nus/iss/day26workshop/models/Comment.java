package sg.nus.iss.day26workshop.models;

import org.bson.Document;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String _id;
    private String c_id;
    private String user;
    private Integer rating;
    private String c_text;
    private Integer gid;


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getC_text() {
        return c_text;
    }
    public void setC_text(String c_text) {
        this.c_text = c_text;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public static Comment create(Document d){
        Comment c = new Comment();
        c.set_id(d.getObjectId("_id").toString());
        c.setC_id(d.getString("c_id"));
        c.setUser(d.getString("user"));
        c.setRating(d.getInteger("rating"));
        c.setC_text(d.getString("c_text"));
        c.setGid(d.getInteger("gid"));
        return c;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
        .add("_id", this.get_id())
        .add("c_id ", this.getC_id())
        .add("user", this.getUser())
        .add("rating", this.getRating())
        .add("c_text", this.getC_text())
        .add("gid", this.getGid())
        .build();
    }

    

}
