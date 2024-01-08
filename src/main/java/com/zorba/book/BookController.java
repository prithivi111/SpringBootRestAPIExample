package com.zorba.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //this is used in traditional MVC applications where the controller is responsible for handling requests and returning views.
//public class BookController {
	
//	@RequestMapping(value="/books", method=RequestMethod.GET)
//	@ResponseBody  //This will return the string as it is, mentioned in the getBooks() method, ie,"This is just testing for book",
				// which the user will be veiwing on the web page as it is. But, if we want to send response in JSON format
				// rather than the rendered view,  we can change the @Controller to @RestController and we also don't need to
				// mention @ResponseBody. Meaning that it combines the @Controller and @ResponseBody annotations, meaning that
				//every method in a @RestController class retuns the data directly(not a view) in a foramt like JSON or XML.
				
				//Also, if we mention @GetMapping("/books"), (which combines the 'method' and 'value'), we donot need to
				//@RequestMapping
             
//	public String getBooks() {
//		return "This is just testing for book";
//	}

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//Get one-book Handler (Assuming that there is only one book)
//	@GetMapping("/books")           
//	public BookEntity getBooks() {
//		BookEntity bookInfo = bookService.saveBookInfo();
//		return bookInfo;
//	}	
	
	//Get All books Handler
//	@GetMapping("/books")  
//	public List<BookEntity> getAllBooks() {
//		List<BookEntity> listBooks = bookService.getAllBooks();
//		return listBooks;
//		
//	}

	//Get Single book handler with id in the parameter
	@GetMapping("/books/{id}")
	public BookEntity getBookById(@PathVariable("id") int id) {	//Must need to define path variable to bind this method
		                                                       // with the input which user gives in the URL
		return bookService.getBookById(id);
		
	}
	
	//New Book handler
	@PostMapping("/books")  //Here we are writing Post instead of Get because we are adding book into the DB this time.
	public BookEntity addBook(@RequestBody BookEntity book) {  
		BookEntity b= this.bookService.addBook(book);
		System.out.println(book);
		return b;
	}
	//@RequestBody annotation indicates that the book parameter should be bound to the HTTP request body.
	//@RequestBody annotation is crucial here because it tells Spring to 
	//deserialize the incoming JSON (or other supported format) from the request body into a BookEntity object.
	
	//Delete Book Handler
	@DeleteMapping("/books/{Bookkoid}")
	public void deleteBook(@PathVariable("Bookkoid") int id) {
		 this.bookService.deleteBook(id);
		}
	
	//Update Book handler
	@PutMapping("/books/{bookid}")
	public BookEntity updateBook(@RequestBody BookEntity book, @PathVariable("bookid") int id) {
		this.bookService.updateBook(book, id);
			return book;
	}
	
	//Get All Books Handler (implementation of codes handling HTTP response, assuming there might not be any data in DB)
	//Here, we are trying to get the data from the our list(Database), and there is no data in the database.
	//So we must send the something back to the User regarding Status so that User will get to know that there is nothing in DB.
	//If we do not code this exception then, the UI will have blank on their screen! So, in reality, for getting the data,
	//we need to write the code as below, which will include response regarding the Status of the User's Request as well.
	//For doing this, lets comment-out our list of books in the Service Class and I commented-out.
	//At the very top, we wrote the code somthing like below and the we get the list of books and we return back the list of
	//books to the users and User will be able to see on their screen.
//		@GetMapping("/books")
//			public List<BookEntity> getAllBooks() {
//				List<BookEntity> listBooks = bookService.getAllBooks();
//					return listBooks;
//			}
//	
	//But lets write in the different way handling the exception/status, if no book record is there in the DB.
	//In the postman as well, you will see the status as 200 Ok, and there will be no data obviously,
	
	@GetMapping("/books")
	public ResponseEntity<List<BookEntity>> getAllBooks() { 
		//here we are sending back "Status not found" right, that is why the return type should be ResponseEntity.
		//And ResponseEntity will have List<BookEntity>.		
			
			List<BookEntity> list = bookService.getAllBooks();
				if(list.size()<=0) {
			
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					//ResponseEntity contains the status of NOT_FOUND and the object of ResponseEntity will be created by build().
				}else {
					return ResponseEntity.of(Optional.of(list));
					//or we can write: 
					//return ResponseEntity.ok(list);
					//ResponseEntity.ok() is a static factory method provided by the ResponseEntity class in Spring.
					//It creates a ResponseEntity object that will provide an HTTP status of 200 OK.
				}	
	
	//ResponseEntity is a class in Spring Framework that represents the entire HTTP response. It allows you to control
	//the HTTP status code, headers, and body of the response.
	
	//HttpStatus.NOT_FOUND: HttpStatus is an enumeration in Spring that represents HTTP status codes. 
	//NOT_FOUND is a constant representing the 404 status code,which indicates that the requested resource is not found
    //on the server.
	
	//.build():	It's a method of the ResponseEntity class used to build and create the final response.
    //In this context, it's used to create a response(object) with the specified HTTP status code and no response body.
	
	//Optional: Optional is class that belongs to java.util package, and it has seveeral methods. It is a container object
	//that may or may not contain a non-null value. It helps handle scenarios
	//where a value might be present or absent without explicitly using null. It has methods, which are used for more
//expressive an null-safe code, especlly in cases where a value might be absent and Optional.of(value) is one of those methods.
	
	//Optional.of(list) is a static method of the Optional Class that creates an Optional object containing the provided list.
	//Even if the list is empty, it will still be wrapped in an Optional.
				
	//ResponseEntity.of(...): The .of method is an extension method provided by Spring for the ResponseEntity class.
	//It creates a new ResponseEntity with the specified body and status. 
	//In the above case, the body is an Optional containing the list.
	}
	
}
