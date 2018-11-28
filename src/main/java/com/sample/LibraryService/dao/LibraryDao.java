package com.sample.LibraryService.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sample.LibraryService.model.Book;
import com.sample.LibraryService.model.Member;
import com.sample.LibraryService.model.Transaction;

@Mapper
public interface LibraryDao {
	
	@Insert("INSERT into library.book(bookName, authorName, category, edition, price, copies) "
			+ "VALUES(#{bookName}, #{authorName},#{category}, #{edition},#{price}, #{copies})")
	public void addBook(Book book);
	
	
	@Select("SELECT * from library.book WHERE bookName = #{bookName} and "
			+ "authorName = #{authorName} and edition = #{edition}")
	public Book searchBook(Book book);
	
	@Update("<script>"+
			  "update library.book"+
			    "<set>"+
			      "<if test='book.price != null '>price=#{book.price},</if>"+
			      "<if test='book.copies !=null '>copies=#{book.copies},</if>"+
			      "<if test='book.category != null'>category=#{book.category}</if>"+
			    "</set>"+
			  "where bookID=#{id}"+
			"</script>")
	public void updateBook(@Param("book")Book book,@Param("id")int ID);
	
	@Select("SELECT * from library.member WHERE memberName = #{memberName} and "
			+ "phoneNumber = #{phoneNumber} ")
	public Member searchMember(Member member);
	
	@Insert("INSERT into library.member(memberName, phoneNumber, emailID) "
			+ "VALUES(#{memberName}, #{phoneNumber},#{emailID})")
	public void addMember(Member member);
	
	@Update("<script>"+
			  "update library.member"+
			    "<set>"+
			      "<if test='member.memberName != null '>memberName=#{member.memberName},</if>"+
			      "<if test='member.phoneNumber !=null '>phoneNumber=#{member.phoneNumber},</if>"+
			      "<if test='member.emailID != null'>emailID=#{member.emailID}</if>"+
			    "</set>"+
			  "where memberID=#{id}"+
			"</script>")
	public void updateMember(@Param("member")Member member,@Param("id")int ID);

	@Update("UPDATE library.book SET copies=#{copies} WHERE bookID =#{bookID}")
	public void updateBookCopies(Book book);
	
	@Select("SELECT bookID FROM library.txn WHERE bookID=#{bookID} and memberID=#{memberID}")
	public Integer searchTransaction(@Param("memberID") int memberID, @Param("bookID") int bookID);

	@Insert("INSERT into library.txn(bookID,memberID,issuedDate,expiryDate,state)"
			+ "VALUES(#{book.bookID},#{member.memberID},curdate(),ADDDATE(curdate(), INTERVAL 30 DAY),#{transaction.state})")
	public void addTransaction(@Param("book")Book book, @Param("member")Member member, @Param("transaction") Transaction transaction);


	@Select("select m.memberName, m.phoneNumber, b.bookName, b.authorName,b.edition,"+
			"t.state, t.issuedDate, t.expiryDate "+
			"from library.txn t join library.book b on b.bookID=t.bookID join library.member m on m.memberID=t.memberID "+
			"where t.memberID=(select memberID from library.member where memberName=#{member.memberName} and phoneNumber=#{member.phoneNumber})")
	public List<Transaction> getTransactionByMember(@Param("member")Member member);

	@Select("select m.memberName, m.phoneNumber, b.bookName, b.authorName,b.edition,"+
			"t.state, t.issuedDate, t.expiryDate "+
			"from library.txn t join library.book b on b.bookID=t.bookID join library.member m on m.memberID=t.memberID "+
			"where t.bookID=(select bookID from library.book where bookName=#{bookName} and authorName=#{authorName} and edition=#{edition})")
	public List<Transaction> getTransactionByBook(Book book);

	@Select("Select * from library.book where bookName like #{search} OR authorName like #{search}")
	public List<Book> searchBookByName(String search);

	@Delete("DELETE from library.txn WHERE bookID =#{book.bookID} and memberID=#{member.memberID}")
	public void deleteTransaction(@Param("book")Book book, @Param("member")Member member);

	@Select("Select * from library.book limit 10 ")
	public List<Book> getBooks();

	@Select("Select password from library.admin where username=#{userName} ")
	public String getUser(String userName);

	


}
