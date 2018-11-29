
CREATE TABLE library.book (bookID int NOT NULL AUTO_INCREMENT
 PRIMARY KEY, bookName VARCHAR(100), authorName VARCHAR(50)
, category VARCHAR(20), edition VARCHAR(5), price int, copies int
);

insert into library.book (bookName, authorName, category, edition, price, copies)
values('test', 'test', 'test', 'test', 100, 12)


CREATE TABLE library.member (memberID int NOT NULL AUTO_INCREMENT
 PRIMARY KEY, memberName VARCHAR(50), phoneNumber VARCHAR(10)
, emailID VARCHAR(50)
);

CREATE TABLE library.txn (bookID int NOT NULL,
    memberID int NOT NULL,
    issuedDate date,
    expiryDate date,
	state varchar(10),
    PRIMARY KEY (bookID, memberID),
    FOREIGN KEY (bookID) REFERENCES library.book(bookID),
	FOREIGN KEY (memberID) REFERENCES library.member(memberID)
);

CREATE TABLE library.admin ( userName VARCHAR(), password VARCHAR());

insert into library.admin values ('admin','oRyvq8XIzUJeFqzi0GXl2C2DZvu9qHftw1f/2rN34Fg=')



