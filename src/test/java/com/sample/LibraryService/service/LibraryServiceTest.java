package com.sample.LibraryService.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sample.LibraryService.dao.LibraryDao;
import com.sample.LibraryService.model.Book;
import com.sample.LibraryService.service.impl.LibraryServiceImpl;

public class LibraryServiceTest {
	
	private LibraryDao daoMock;
	private LibraryService service;

	 @Before
	    public void setUp() {
	        daoMock = Mockito.mock(LibraryDao.class);
	        service = new LibraryServiceImpl(daoMock);
	    }
	 
	 @Test
	    public void searchBookTest() throws Exception {
		 Book book= new Book();
		 List<Book> books= new ArrayList<Book>();
		 books.add(book);
	        when(daoMock.searchBookByName("test")).thenReturn(books);
	        List<Book> bookList= service.searchBook("Foo");
	        assertNotNull(bookList);
	    }

}
