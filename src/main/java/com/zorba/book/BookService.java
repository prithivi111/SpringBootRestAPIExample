package com.zorba.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	//	This is a suppose, think as if a data base data for the time being.
	//We will later join the Spring Data JPA to link actually fetch and store the data from/into the database,
	//where we will be further using one more layer called Repository!!
	
//	public BookEntity saveBookInfo() {
//		BookEntity bookEntity = new BookEntity();
//		bookEntity.setId(123);
//		bookEntity.setAuthor("Harry Lauren");
//		bookEntity.setTitle("Throne Of Glass");
//		return bookEntity;
//		}
	
	//Instead, we are making a fake database to do similar-like-database operations.
	//Lets make list of books in this class itself.
	//If we had not made the below database in this class, we need to make another layer to connect to the database called
	//Repository Layer that we learned in Spring Data JPA.
		
		private static List<BookEntity> list = new ArrayList<>();
		
		{
			list.add(new BookEntity(12,"Alibaba and Chalis Chor", "Ramesh"));
			list.add(new BookEntity(22, "Life in Japan", "Yoshida"));
			list.add(new BookEntity(998,"Newyork Night", "Simon"));
		}
		
		//get all books
		public List<BookEntity> getAllBooks(){
			return list;
		}
	
		//get single book by Id
		public BookEntity getBookById(int id) {  
			BookEntity bookMatch= null;
			for(BookEntity book:list) {
				if (book.getId()==id){
					bookMatch = book;
					break;
				}
				
			}
			return bookMatch;
		}
}
	
