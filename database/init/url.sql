drop table if exists url;

create table url (
                           origin_url varchar(255) not null ,
                           short_url varchar(255) not null ,
                           url_id bigint not null auto_increment,
                           primary key (url_id)
) engine=InnoDB;