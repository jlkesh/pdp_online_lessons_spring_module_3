package uz.pdp.springadvanced.book;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

   @GetMapping("/paged")
    public ResponseEntity<Page<Book>> getAllPaged(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam String field

    ) {
        Sort sort = Sort.by(direction, field);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(bookRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable String id) {
        Book post = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not Found"));
        return ResponseEntity.ok(post);
    }


    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookRepository.save(post));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Book updatingData) {
        return ResponseEntity.ok(bookRepository.update(updatingData));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookRepository.delete(new ObjectId(id));
        return ResponseEntity.noContent().build();
    }
}
