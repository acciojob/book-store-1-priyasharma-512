package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{books}")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(this.id);
        this.bookList.add(book);
        this.id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id)
    {
        Book newBook = null;
        int copy_id = Integer.parseInt(id);
        for(Book book : bookList) {
            if (book.getId() == copy_id) {
                newBook = book;
                break;
            }
        }
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
    // pass id as path variable
    // getBookById()

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable String id)
    {
        int copy_id = Integer.parseInt(id);
        this.bookList.removeIf(book -> book.getId() == copy_id);
        return new ResponseEntity<>("Book Deleted Successfully", HttpStatus.CREATED);
    }
    // pass id as path variable


    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return new ResponseEntity<>(this.getBookList(), HttpStatus.CREATED);
    }


   @DeleteMapping("/delete-all-books")
   public ResponseEntity<String> deleteAllBooks()
   {
       this.bookList.clear();
       return new ResponseEntity<>("All Book Deleted Successfully", HttpStatus.CREATED);
   }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author)
    {
        List<Book> listOfBooksByAuthor = new ArrayList<>();

        for(Book book : bookList) {
            if(book.getAuthor().equals(author)) {
                listOfBooksByAuthor.add(book);
            }
        }
        return new ResponseEntity<>(listOfBooksByAuthor, HttpStatus.CREATED);
    }
    // pass author name as request param

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre)
    {
        List<Book> listOfBooksByGenre = new ArrayList<>();

        for(Book book : bookList) {
            if(book.getGenre().equals(genre)) {
                listOfBooksByGenre.add(book);
            }
        }
        return new ResponseEntity<>(listOfBooksByGenre, HttpStatus.CREATED);
    }
    // pass genre name as request param
}
