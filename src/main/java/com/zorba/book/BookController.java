package com.zorba.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //this is used in traditional MVC applications where the controller is responsible for handling requests and
			  // and returning views.
//public class BookController {
	
//	@RequestMapping(value="/books", method=RequestMethod.GET)
//	@ResponseBody  //This will return the string as it is, mentioned in the getBooks() method, ie,"This is just testing for book",
				// which the user will be veiwing on the web page.s it is. But, if we want to send response in JSON format
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
	
//	@GetMapping("/books")           
//	public BookEntity getBooks() {
//		BookEntity bookInfo = bookService.saveBookInfo();
//		return bookInfo;
//	}	
	
	@GetMapping("/books")  //This is for getting all the books
	public List<BookEntity> getAllBooks() {
		List<BookEntity> listBooks = bookService.getAllBooks();
		return listBooks;
		
	}
	
	@GetMapping("/books/{id}")
	public BookEntity getBookById(@PathVariable("id") int id) {	//Must need to define path variable to bind this method
		                                                       // with the input which user gives in the URL
		return bookService.getBookById(id);
		
	}
	
	
	
}
