package com.sample.LibraryService.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



public class Transaction {
	@NotNull
	@NotEmpty
	private String memberName;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
	
	@NotNull
	@NotEmpty
	private String bookName;
	
	@NotNull
	@NotEmpty
	private String authorName;
	
	@NotNull
	@NotEmpty
	private String edition;
	
	@NotNull
	@NotEmpty
    private String state;
    
    private Date issuedDate;
    
    private Date expiryDate;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getState() {
		return state;
	}

	public void setState(String action) {
		this.state = action;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	
    
}
