package uz.pdp.springadvanced;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SpringadvancedApplication {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/?directConnection=true");
        MongoDatabase db = mongoClient.getDatabase("pdpjava");
        MongoCollection<Document> postsCollection = db.getCollection("posts");
        // insertOne(postsCollection);
        // insertMany(postsCollection);
        // find(postsCollection);
        // updateDocument(postsCollection);
        // delete(postsCollection);

    }

    private static void delete(MongoCollection<Document> postsCollection) {
        //Bson filter = Filters.regex("post_title", "Post.*");
        Bson filter = Filters.empty();
        postsCollection.deleteMany(filter);
    }

    private static void updateDocument(MongoCollection<Document> postsCollection) {
    /*Bson filter = Filters.regex("title", ".*o.*");
    Bson update = Updates.set("createdAt", new Date());
    // postsCollection.updateOne(filter, update);
    UpdateResult updateResult = postsCollection.updateMany(filter, update);
    System.out.println("updateResult.getModifiedCount() = " + updateResult.getModifiedCount());*/
        /*Bson filter = Filters.exists("title");
        Bson update = Updates.rename("title", "post_title");
        UpdateResult updateResult = postsCollection.updateMany(filter, update);
        System.out.println("updateResult.getModifiedCount() = " + updateResult.getModifiedCount());*/

        /*Bson filter = Filters.exists("createdAt");
        Bson update = Updates.unset("createdAt");
        postsCollection.updateMany(filter, update);*/

        Bson filter = Filters.empty();
        Bson update = Updates.unset("createdAt");
        UpdateResult updateResult = postsCollection.updateMany(filter, update);
        System.out.println("updateResult.getModifiedCount() = " + updateResult.getModifiedCount());
    }

    private static void find(MongoCollection<Document> postsCollection) {
        /*FindIterable<Document> documentFindIterable = postsCollection.find();
        for (Document document : documentFindIterable) {
            System.out.println("document.getString(\"title\") = " + document.getString("title"));
            String title = document.get("title", String.class);
            System.out.println("document = " + document);
        }*/
        Bson filter = Filters.eq("_id", new ObjectId("643e134843c5735856cfbd1e"));
        Document document = postsCollection
                .find(filter)
                .first();
        System.out.println("document = " + document);
        Document address = document.get("address", Document.class);
        String string = address.getString("zipcode");
        System.out.println("string = " + string);
    }

    private static void insertMany(MongoCollection<Document> postsCollection) {
        String post1 = """
                { 
                  "title" : "Post 3 Title",
                  "body" : "Post 3 Body"
                }
                """;
        Map<String, Object> map = Map.of(
                "title", "Post 2 Title",
                "body", "Post 2 Body"
        );
        List<Document> documents = List.of(
                Document.parse(post1),
                new Document(map));
        InsertManyResult insertManyResult = postsCollection.insertMany(documents);
        if (insertManyResult.wasAcknowledged()) {
            insertManyResult.getInsertedIds().forEach((k, v) -> {
                ObjectId objectId = v.asObjectId().getValue();
                System.out.println(k + " : " + objectId.toString());
            });
        }
    }

    private static void insertOne(MongoCollection<Document> postsCollection) {
        Document post = Document.parse("""
                                
                {
                  "id": 1,
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz",
                  "address": {
                    "street": "Kulas Light",
                    "suite": "Apt. 556",
                    "city": "Gwenborough",
                    "zipcode": "92998-3874",
                    "geo": {
                      "lat": "-37.3159",
                      "lng": "81.1496"
                    }
                  },
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org",
                  "company": {
                    "name": "Romaguera-Crona",
                    "catchPhrase": "Multi-layered client-server neural-net",
                    "bs": "harness real-time e-markets"
                  }
                }"""); /*new Document("title", "How To Connect MongoDB From Java Applications")
                .append("body", "It is easy with mongodb-driver-sync dependency")
                .append("createdAt", new Date());*/
        InsertOneResult insertOneResult = postsCollection.insertOne(post);
        if (insertOneResult.wasAcknowledged()) {
            BsonValue insertedId = insertOneResult.getInsertedId();
            ObjectId objectId = insertedId.asObjectId().getValue();
            System.out.println("objectId.toString() = " + objectId.toString());
        }
    }

}
