package uz.pdp.springadvanced.book;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final MongoTemplate mongoTemplate;

    public BookRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Book save(Book book) {
        return mongoTemplate.save(book);
    }

    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }

    public Page<Book> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Book> books = mongoTemplate.find(query, Book.class);
        return PageableExecutionUtils
                .getPage(books, pageable, () -> mongoTemplate.count(new Query(), Book.class));
    }

    public boolean delete(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query, Book.class);
        return deleteResult.wasAcknowledged();
    }


    public boolean update(Book book) {
        Criteria criteria = Criteria.where("_id").is(book.getId());
        Query query = new Query(criteria);
        Update update = new Update();
        if (book.getName() != null)
            update.set("name", book.getName());
        if (book.getAuthor() != null)
            update.set("author", book.getAuthor());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Book.class);
        return updateResult.wasAcknowledged();
    }


    public Optional<Book> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return Optional.ofNullable(mongoTemplate.findOne(query, Book.class));
    }
}
