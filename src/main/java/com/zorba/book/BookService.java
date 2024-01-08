package com.zorba.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	//	Think as if the values below I provided, are the values of the data base data for the time being.
	//We will later join the Spring Data JPA to link actually fetch and store the data from/into the database,
	//where we will be further using one more layer called Repository, that we have alreay learned before!
	
//	public BookEntity saveBookInfo() {
//		BookEntity bookEntity = new BookEntity();
//		bookEntity.setId(123);
//		bookEntity.setAuthor("Harry Lauren");
//		bookEntity.setTitle("Throne Of Glass");
//		return bookEntity;
//		}
	
	//Lets make list of books in this class itself, and consider as the datas of the database again.
	//If we had not made the below database values in this class, we need to make another layer to connect to the database called
	//Repository Layer that we learned in Spring Data JPA.
		
		private static List<BookEntity> list = new ArrayList<>();	
		{
//			list.add(new BookEntity(12,"Alibaba and Chalis Chor", "Ramesh"));
//			list.add(new BookEntity(22, "Life in Japan", "Yoshida"));
//			list.add(new BookEntity(998,"Newyork Night", "Simon"));
		}
		
		//return all books to the User who is fetching via browser
		public List<BookEntity> getAllBooks(){
			return list;
			
		}
	
		//return a single book searching by Id by the User who is fetching via browser
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
		
		//adding the book in our DB as per the information provided by the user via browser
		public BookEntity addBook(BookEntity book) {
			list.add(book);
			return book;
		}
		
		//delete the book from our DB as per the information (id)
		public void deleteBook(int id) {
			list=list.stream().filter(book->{
				if(book.getId()!=id) {
					return true;
				}else {
					return false;
				}			
			}).collect(Collectors.toList());		
			//We can write this above code in simple form.
			//We just filtered our list where we used lamda expression and stream API. The code might be little confusing for you.
		}
		
		//update the book in our DB as per the 
		public void updateBook(BookEntity book, int bookId) {
//			list = list.stream().map(e->{
//				if(e.getId()==bookId) {
//					e.setTitle(book.getTitle());
//					e.setAuthor(book.getAuthor());
//				}
//				return e;
//			}).collect(Collectors.toList());
//		
//		
		//The above code is equivalent to:
			for (int i = 0; i < list.size(); i++) {
			  BookEntity e = list.get(i);
			    if (e.getId() == bookId) {
			          e.setTitle(book.getTitle());
			          e.setAuthor(book.getAuthor());
			          break;
			        }
			 }
			
	}	
}
	
