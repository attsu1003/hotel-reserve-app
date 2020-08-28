create table IF NOT EXISTS hotelreservedb.MEMBER (
 	id varchar(30) NOT NULL PRIMARY KEY,
 	password VARCHAR(64) NOT NULL
);

create table IF NOT EXISTS hotelreservedb.RESERVE (
    id varchar(32) PRIMARY KEY,
    checkindate date NOT NULL,
    checkoutdate date NOT NULL,
    memberid varchar(30) NOT NULL,
    foreign key (memberid) references MEMBER (id)
);  

create table IF NOT EXISTS hotelreservedb.ROOM (
	id char(3) NOT NULL,
	constraint pk_reserve primary key (id)
);