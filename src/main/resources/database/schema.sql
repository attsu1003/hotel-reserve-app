create table IF NOT EXISTS hotelreservedb.MEMBER (
 name varchar(30) NOT NULL PRIMARY KEY,
 password VARCHAR(64) NOT NULL
);

create table IF NOT EXISTS hotelreservedb.RESERVE (
    id varchar(32) PRIMARY KEY,
    checkindate date not null,
    checkoutdate date not null,
    memberid varchar(30) not null,
    foreign key (memberid) references MEMBER (name)
);  

create table IF NOT EXISTS hotelreservedb.ROOM (
	id char(3)		not null,
	constraint pk_reserve primary key (id)
);