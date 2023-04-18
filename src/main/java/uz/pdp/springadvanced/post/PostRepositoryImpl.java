package uz.pdp.springadvanced.post;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import lombok.NonNull;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostRepositoryImpl implements PostRepository {
    private static final MongoClient CLIENT = MongoClients.create("mongodb://pdpjava:123@localhost:27017/pdpjava");
    private static final MongoDatabase DB = CLIENT.getDatabase("pdpjava");
    private static final MongoCollection<Document> COLLECTION = DB.getCollection("posts");

    @Override
    public Post get(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document document = COLLECTION.find(filter).first();
        if (document == null)
            return null;
        return new Post(document);
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        FindIterable<Document> documents = COLLECTION.find();
        for (Document document : documents)
            posts.add(new Post(document));
        return posts;
    }

    @Override
    public List<Post> getAll(int page, int size) {
        List<Post> posts = new ArrayList<>();
        Bson sortByUserId = Sorts.ascending("userId");
        Bson sortById = Sorts.descending("id");
        Bson sort = Sorts.orderBy(Sorts.ascending("userId"), Sorts.ascending("id"));
        // userId >= 3 and userId <= 5 and id > 30
        Bson filter = Filters.and(
                Filters.gte("userId", 3),
                Filters.lte("userId", 5),
                Filters.gt("id", 30)
        );
        FindIterable<Document> documents = COLLECTION
                .find(filter)
                .sort(sort)
                .skip(page * size)
                .limit(size);
        for (Document document : documents)
            posts.add(new Post(document));
        return posts;
    }

    @Override
    public Post save(@NonNull Post post) {
        Document document = new Document(Map.of(
                "id", post.getId(),
                "userId", post.getUserId(),
                "title", post.getTitle(),
                "body", post.getBody()
        ));
        InsertOneResult insertOneResult = COLLECTION.insertOne(document);
        if (insertOneResult.wasAcknowledged()) {
            ObjectId objectId = insertOneResult.getInsertedId().asObjectId().getValue();
            post.setMongoID(objectId);
            return post;
        }
        return null;
    }

    @Override
    public List<Post> saveAll(@NonNull List<Post> posts) {
        for (Post post : posts) {
            save(post);
        }
        return posts;
    }

    @Override
    public boolean delete(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        return COLLECTION.deleteOne(filter).wasAcknowledged();
    }

    @Override
    public boolean update(Post post) {
        Bson filter = Filters.eq("_id", post.getMongoID());
        Bson update = Updates.combine(
                Updates.set("title", post.getTitle()),
                Updates.set("body", post.getBody())
        );
        return COLLECTION.updateOne(filter, update).wasAcknowledged();
    }
}
