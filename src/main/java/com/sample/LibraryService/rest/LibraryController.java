package com.sample.LibraryService.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.LibraryService.model.Book;
import com.sample.LibraryService.model.Member;
import com.sample.LibraryService.model.Transaction;
import com.sample.LibraryService.service.LibraryService;

/**
 * Library Controller contains all the end points for Library management
 * transactions
 * 
 * @author Sahana
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryService service;

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public String addOrUpdateBook(@Valid @RequestBody Book book) {
		return service.addOrUpdateBook(book);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/member")
	public String addOrUpdateMember(@Valid @RequestBody Member member) {
		return service.addOrUpdateMember(member);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transaction")
	public String addTransaction(@Valid @RequestBody Transaction transaction) {
		return service.addTransaction(transaction);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transaction/member")
	public List<Transaction> getTransactionByMember(
			@Valid @RequestBody Member member) {
		return service.getTransactionByMember(member);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transaction/book")
	public List<Transaction> getTransactionByBook(@Valid @RequestBody Book book) {
		return service.getTransactionByBook(book);
	}

	@RequestMapping("/book/{searchString}")
	public List<Book> searchBook(
			@PathVariable("searchString") String searchString) {
		return service.searchBook(searchString);
	}

	@RequestMapping("/books")
	public List<Book> getBooks() {
		return service.getBooks();
	}

}
