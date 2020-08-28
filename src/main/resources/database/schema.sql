create table IF NOT EXISTS hotelreservedb.MEMBER (
 	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 	name varchar(30) not null,
 	password VARCHAR(64) not null
);

create table IF NOT EXISTS hotelreservedb.RESERVE (
    id varchar(32) PRIMARY KEY,
    checkindate date not null,
    checkoutdate date not null,
    memberid INT not null,
    foreign key (memberid) references MEMBER (id)
);  

create table IF NOT EXISTS hotelreservedb.ROOM (
	id char(3)		not null,
	constraint pk_reserve primary key (id)
);