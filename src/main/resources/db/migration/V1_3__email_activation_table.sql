create table email_validation_code
(
    id              bigint not null auto_increment,
    is_deleted      bit default b'0',
    validation_code varchar(255),
    user_id         bigint,
    primary key (id)
) engine = InnoDB
