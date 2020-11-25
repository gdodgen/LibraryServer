package servlets;

import static org.junit.Assert.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;


import Service.BookService;
import Template.BookTemplate;
import dao.DatabaseBookDAO;
import model.Book;


public class BookServletTest {
	
	DatabaseBookDAO mockBookDAO = mock(DatabaseBookDAO.class);
	BookService mockBookService = mock( BookService.class);
	
	BookTemplate testBook1Temp = new BookTemplate("title1", "ISBN1", "Author1", "Genre1", false);
	BookTemplate testBook2Temp = new BookTemplate("title2", "ISBN2", "Author2", "Genre2", false);
	BookTemplate testBook3Temp = new BookTemplate("title3", "ISBN3", "Author3", "Genre3", false);
	
	Book testBook1 = mockBookService.newBook(testBook1Temp);
	Book testBook2 = mockBookService.newBook(testBook2Temp);
	Book testBook3 = mockBookService.newBook(testBook3Temp);
	
	/*
	@Test
	public void testNewBook() {
		BookTemplate testBook4Temp = new BookTemplate("title4", "ISBN4", "Author4", "Genre4", false); 
		Book testBook4 = mockBookDAO.newBook("title4", "ISBN4", "Author4", "Genre4");
		
		when(mockBookService.newBook(testBook4Temp)).thenReturn(testBook4);
		Assert.assertEquals(mockBookService.newBook(testBook4Temp), testBook4.getTitle());
	}*/
	//i dont understand mocking
	/*
	@Test
	public void testCheckOut() {
		
		mockBookService.checkOut("ISBN1", "1");
		Assert.assertTrue(testBook3.getCheckedOut());
	} */

}
