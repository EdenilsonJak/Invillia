 create table pedido (
 		id bigint not null auto_increment,
 		endereco varchar(255),
 		dataorder date,
 		orderStatus varchar(255),
 		payment_id bigint,
 		primary key (id)
 		);

 create table orderitem (
       id bigint not null auto_increment,
        description varchar(255),
        qtd double precision,
        unit_value double precision,
        order_id bigint,
        primary key (id)
    );
    
    create table payment (
       id bigint not null auto_increment,
        momentpgto datetime,
        num_credito bigint,
        order_status varchar(255),
        order_id bigint,
        primary key (id)
    );
    
    create table store (
       id bigint not null auto_increment,
        endereco varchar(255),
        nome varchar(255),
        primary key (id)
    );
    
    alter table orderitem 
       add constraint FKpshbtgfq75shxt5l6rhsuaj2h 
       foreign key (order_id) 
       references pedido (id);
    
    alter table payment 
       add constraint FKffjmb2f9luqfak4lnk1rceb2b 
       foreign key (order_id) 
       references pedido (id);
    
    alter table pedido 
       add constraint FKmw9x2xx5dntfk0h4uuhhel2ep 
       foreign key (payment_id) 
       references payment (id);
    