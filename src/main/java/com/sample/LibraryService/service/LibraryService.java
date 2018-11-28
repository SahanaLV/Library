package com.sample.LibraryService.service;

import java.util.List;

import com.sample.LibraryService.model.Book;
import com.sample.LibraryService.model.Member;
import com.sample.LibraryService.model.Transaction;

public interface LibraryService {

	public String addOrUpdateBook(Book book);

	public String addOrUpdateMember(Member member);

	public String addTransaction(Transaction transaction);

	public List<Transaction> getTransactionByMember(Member member);

	public List<Transaction> getTransactionByBook(Book book);

	public List<Book> searchBook(String searchString);

	public List<Book> getBooks();

	public String validateUser(String userName);

}
