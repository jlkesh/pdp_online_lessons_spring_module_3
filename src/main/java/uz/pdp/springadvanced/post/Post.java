package uz.pdp.springadvanced.post;

import lombok.*;
import org.bson.Document;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"title","body"})
@Builder
public class Post {
    private ObjectId mongoID;
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public Post(Document document) {
        this.mongoID = document.getObjectId("_id");
        this.id = document.getInteger("id");
        this.userId = document.getInteger("userId");
        this.title = document.getString("title");
        this.body = document.getString("body");
    }
}
