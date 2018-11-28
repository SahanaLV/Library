package com.sample.LibraryService.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Book {
	
private int bookID;
	
@NotNull
@NotEmpty
private String bookName;

@NotNull
@NotEmpty
private String authorName;

@NotNull
@NotEmpty
private String edition;

private Integer price;

private String category;

private Integer copies;

public int getBookID() {
	return bookID;
}

public void setBookID(int bookID) {
	this.bookID = bookID;
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

public Integer getPrice() {
	return price;
}

public void setPrice(Integer price) {
	this.price = price;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public Integer getCopies() {
	return copies;
}

public void setCopies(Integer copies) {
	this.copies = copies;
}


}
