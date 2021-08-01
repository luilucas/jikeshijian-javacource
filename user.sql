create table customer_login(
customer_id int unsigned primary key auto_increment, not null,
username varchar(20) not null,
`password` varchar(20) not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table customer_info(
customer_info_id int unsigned primary key auto_increment not null,
customer_id int unsigned not null,
customer_name varchar(20) not null,
customer_gender char(1) not null,
customer_identity_type tinyint not null default 1,
customer_identity_number varchar(20) not null,
customer_mobile_phone int unsigned not null,
customer_email varchar(50) not null,
customer_balance decimal(8,2) not null default 0.00,
customer_point int unsigned default 0,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table customer_addr(
customer_addr_id int unsigned primary key auto_increment not null,
customer_id int unsigned int not null,
zip smallint not null,
provice_id smallint not null,
city_id smallint not null,
district smallint not null,
customer_address varchar(20) not null,
is_default tinyint not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb