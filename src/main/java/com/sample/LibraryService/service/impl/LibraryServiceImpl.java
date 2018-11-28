package com.sample.LibraryService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.LibraryService.dao.LibraryDao;
import com.sample.LibraryService.model.Book;
import com.sample.LibraryService.model.Member;
import com.sample.LibraryService.model.Transaction;
import com.sample.LibraryService.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	private LibraryDao libraryDao;

	@Autowired
	public LibraryServiceImpl(LibraryDao dao) {
		this.libraryDao = dao;
	}

	/**
	 * This method checks whether book is already present in db with bookName,
	 * authorName and edition If book is present it will check whether any value
	 * is changed and update it in database If book is not present it will add
	 * new book to database
	 */
	public String addOrUpdateBook(Book book) {
		Book availableBook = libraryDao.searchBook(book);
		if (availableBook == null)
			libraryDao.addBook(book);
		else {
			if ((book.getPrice() != null && book.getPrice() != availableBook
					.getPrice())
					|| (book.getCopies() != null && book.getCopies() != availableBook
							.getCopies())
					|| (book.getCategory() != null && !(book.getCategory()
							.equalsIgnoreCase(availableBook.getCategory()))))

				libraryDao.updateBook(book, availableBook.getBookID());
			return "Book updated";

		}
		return "Added new book";
	}

	/**
	 * This method checks whether member is already present in db with
	 * memberName and phonenumber If member is present it will check whether any
	 * value is changed and update it in database If member is not present it
	 * will add new member to database
	 */
	public String addOrUpdateMember(Member member) {
		Member availableMember = libraryDao.searchMember(member);
		if (availableMember == null)
			libraryDao.addMember(member);
		else {
			if (member.getEmailID() != null
					&& member.getEmailID().equalsIgnoreCase(
							availableMember.getEmailID()))
				libraryDao.updateMember(member, availableMember.getMemberID());
			return "Member updated";
		}
		return "Added new member";
	}

	/**
	 * This method is used to issue or return book with bookName, authorName and
	 * edition it will get bookID from book table with memberName and
	 * phoneNumber it will get memberID from member Table If Member is not
	 * present return "Member not present message" Check whether book is already
	 * issued, if issued return "Book is already issued" If State is "issue" add
	 * transaction to txn table and update copies in book table If State is
	 * "return" delete record in txn table and update copies in book table
	 */
	public String addTransaction(Transaction transaction) {
		Book book = new Book();
		book.setBookName(transaction.getBookName());
		book.setAuthorName(transaction.getAuthorName());
		book.setEdition(transaction.getEdition());
		book = libraryDao.searchBook(book);
		Member member = new Member();
		member.setMemberName(transaction.getMemberName());
		member.setPhoneNumber(transaction.getPhoneNumber());
		member = libraryDao.searchMember(member);
		if (member == null) {
			return "Member not Present";
		} else {
			Integer present = libraryDao.searchTransaction(
					member.getMemberID(), book.getBookID());
			if (transaction.getState().equalsIgnoreCase("Issue")) {

				if (present == null) {
					book.setCopies(book.getCopies() - 1);
					libraryDao.updateBookCopies(book);
					libraryDao.addTransaction(book, member, transaction);
					return "Issued book successfully";
				} else {
					return "Book is already issued";
				}
			}

			if (transaction.getState().equalsIgnoreCase("Return")) {
				if (present != null) {
					book.setCopies(book.getCopies() + 1);
					libraryDao.updateBookCopies(book);
					libraryDao.deleteTransaction(book, member);
					return "Returned book successfully";
				} else {
					return "Book is not issued";
				}
			}

		}
		return null;
	}

	/**
	 * Get transactions by memberName and phoneNumber
	 */
	public List<Transaction> getTransactionByMember(Member member) {
		return libraryDao.getTransactionByMember(member);

	}

	/**
	 * Get transaction by book name , author Name and edition
	 */
	public List<Transaction> getTransactionByBook(Book book) {
		return libraryDao.getTransactionByBook(book);
	}

	/**
	 * get book list with string matching with bookName or authorName
	 */
	public List<Book> searchBook(String searchString) {
		return libraryDao.searchBookByName("%" + searchString + "%");
	}

	/**
	 * Get first 10 books from book table
	 */
	public List<Book> getBooks() {
		return libraryDao.getBooks();
	}

	/**
	 * Get hashed password of user from admin table
	 */
	public String validateUser(String userName) {
		return libraryDao.getUser(userName);
	}

}
