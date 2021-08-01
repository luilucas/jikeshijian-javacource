create table order_info(
order_id int unsigned primary key auto_increment not null,
customer_id int unsigned not null,
order_serial_number bigint unsigned not null,
create_time timestamp not null,
order_price decimal(8, 2) not null,
order_payment decimal(8, 2)not null,
shipping_payment decimal(8, 2) not null,
payment_method tinyint not null,
shipping_company_name varchar(50) not null,
shipping_cserial_number varchar(20)not null,
ship_time datetime not null,
Ship_send_address nvarchar(100) not null,
ship_receive_address nvarchar(100) not null,
total_weight float not null,
create_time timestamp not null
)ENGINE = innodb

create table order_detail(
order_detail_id int unsigned primary key auto_increment not null,
order_id int unsigned not null,
product_id int not null,
product_name varchar(50) not null,
product_count int not null,
product_prise decimal(8,2) not null
)ENGINE = innodb