create table product_info(
product_id int unsigned primary key auto_increment not null,
product_number char(16) not null,
product_name varchar(50) not null,
product_category_id smallint not null,
product_brand_id smallint not null,
product_price decimal(8,2) not null,
product_color smallint not null,
product_weight float not null,
product_length float not null,
product_height float not null,
product_width float not null,
product_date datetime not null,
product_description text not null,
product_supplier_id smallint not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table product_category(
category_id smallint unsigned primary key auto_increment not null,
category_name varchar(16) not null,
category_code varchar(16) not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table product_brand(
brand_id smallint unsigned primary key auto_increment not null,
brand_name varchar(50) not null,
brand_description text not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table product_supplier(
supplier_id smallint unsigned primary key auto_increment not null,
supplier_name varchar(50) not null,
supplier_code char(8) not null,
supplier_address varchar(50) not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb

create table product_comment(
comment_id int unsigned primary key auto_increment not null,
comment_title varchar(50) not null,
comment_content char(200) not null,
order_id int not null,
customer_id int not null,
create_time timestamp not null,
update_time timestamp not null,
)ENGINE = innodb