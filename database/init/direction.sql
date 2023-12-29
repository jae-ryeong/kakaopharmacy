drop table if exists direction;

create table direction (
                           distance float(53) not null ,
                           latitude float(53) not null ,
                           longitude float(53) not null ,
                           direction_id bigint not null auto_increment,
                           primary key (direction_id)
) engine=InnoDB;

INSERT INTO direction(distance, longitude, latitude)  VALUES (201.4864213 , 126.9911577, 37.4911126);