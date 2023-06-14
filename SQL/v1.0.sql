

create table role (
	id int auto_increment not null,
    name varchar(20) not null,
    created_at timestamp default now(),
    primary key (id)
);

create table user_role(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint user_role_fk1 foreign key (user_id) references user (id),
    constraint user_role_fk2 foreign key (role_id) references role(id)
);

insert into role(name) values ("ROLE_VENDEDOR");
insert into role(name) values ("ROLE_CLIENTE");

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);

create table refresh_token(
	id int auto_increment not null,
	token varchar(256) not null,
    user_id int not null,
    expiry_at datetime not null,
    primary key (id),
    constraint refresh_token_fk1 foreign key (user_id) references user (id)
);

create table offers (
	id int auto_increment not null,
    category_id int not null,
    title varchar(50) not null,
    description varchar(100),
    advertiser int not null,
    value float not null,
    contraste numeric(1,0),
    details varchar(256),
    location varchar(256),
    primary key(id),
    constraint offers_fk1 foreign key (advertiser) references user(id),
    constraint offers_fk2 foreign key (category_id) references category(id)
);

alter table images add column name varchar(256);

create table images (
	id int auto_increment not null,
    offer_id int not null,
    type varchar(256) not null,
    url mediumblob not null,
    primary key(id)
);

insert into offers (category,title,description,advertiser,value,contraste)
values("restaurante","Super Burger","Rodízio de Mini-hambúrger com opção de entrada","Original Burger",29.90,1);

insert into offers (category,title,description,advertiser,value,contraste)
values("restaurante","Cozinha Mexicanar","Almoço ou Jantar com Rodízio Mexicano delicioso","Mexicana",32.90,1);

insert into offers (category,title,description,advertiser,value,contraste)
values("diversao","Estância das águas","Diversão garantida com piscinas, trilhas e muito mais","Estância das águas",31.90,1);

insert into images (offer_id, url)
values (1, "/assets/ofertas/1/img1.jpg");
insert into images (offer_id, url)
values (1, "/assets/ofertas/1/img2.jpg");
insert into images (offer_id, url)
values (1, "/assets/ofertas/1/img3.jpg");
insert into images (offer_id, url)
values (1, "/assets/ofertas/1/img4.jpg");

insert into images (offer_id, url)
values (2, "/assets/ofertas/2/img1.jpg");
insert into images (offer_id, url)
values (2, "/assets/ofertas/2/img2.jpg");
insert into images (offer_id, url)
values (2, "/assets/ofertas/2/img3.jpg");
insert into images (offer_id, url)
values (2, "/assets/ofertas/2/img4.jpg");

insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img1.jpg");
insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img2.jpg");
insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img3.jpg");
insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img4.jpg");
insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img5.jpg");
insert into images (offer_id, url)
values (3, "/assets/ofertas/4/img6.jpg");

create table cart_item (
	id int auto_increment not null,
    offer_id int not null,
    amount int,
    user_id int,
    primary key(id),
    constraint cart_item_fk1 foreign key (offer_id) references offers (id),
    constraint cart_item_fk2 foreign key (user_id) references user(id)
);

create table orders (
	id int auto_increment not null,
    address varchar(256) not null,
    number varchar(10) not null,
    complement varchar(256),
    form_payment varchar(50),
    user_id int not null,
    value_total float not null,
    primary key (id),
    constraint orders_fk1 foreign key (user_id) references user (id)
);

create table order_details (
	id int auto_increment not null,
    order_id int not null,
    offer_id int not null,
    amount int not null,
    primary key (id),
    constraint order_details_fk1 foreign key (order_id) references orders (id),
    constraint order_details_fk2 foreign key (offer_id) references offers (id)
);

create table category (
	id int auto_increment not null,
    name varchar(30) not null,
    description varchar(256) not null,
    path varchar(30),
    created_at timestamp default now(),
    primary key (id)
);

create table category_role (
    category_id int not null,
    role_id int not null,
    primary key (category_id, role_id),
    constraint category_role_fk1 foreign key (category_id) references category (id),
    constraint category_role_fk2 foreign key (role_id) references role(id)
);

insert into category (name, description, path) values ("Restaurante", "Rodízios, Buffet, Carnes Especiais, Feijoada e mais!", "restaurant");
insert into category (name, description, path) values ("Diversão", "Cinema, viagens, diversão muito e mais!", "diversion");

insert into category_role values (1,1);
insert into category_role values (1,2);
insert into category_role values (2,1);
insert into category_role values (2,2);

commit;
