create table information
(
    info_uuid varchar(36) not null
        primary key,
    `key`     varchar(36) not null,
    value     text        null
)
    engine = InnoDB;

create table role
(
    role_uuid varchar(36) not null
        primary key,
    role_name varchar(48) not null,
    nickname  varchar(48) not null
)
    engine = InnoDB;

create table type
(
    type_uuid       varchar(36) not null
        primary key,
    outpatient_type varchar(48) not null,
    description     text        not null
)
    engine = InnoDB;

create table user
(
    uuid      varchar(36)  not null
        primary key,
    user_name varchar(50)  not null,
    password  varchar(60)  not null,
    phone     varchar(11)  not null,
    email     varchar(254) null,
    role      varchar(36)  not null,
    constraint user_role_role_uuid_fk
        foreign key (role) references role (role_uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

create table doctor
(
    doctor_uuid varchar(36)  not null
        primary key,
    user        varchar(36)  not null,
    type        varchar(36)  not null,
    real_name   varchar(255) not null,
    college     varchar(255) not null,
    date_birth  date         not null,
    expert      tinyint(1)   not null comment '是否是专家',
    attending   varchar(36)  null,
    description text         not null,
    constraint doctor_type_type_uuid_fk
        foreign key (type) references type (type_uuid)
            on update cascade on delete cascade,
    constraint doctor_user_uuid_fk
        foreign key (user) references user (uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

create table registration
(
    registration_uuid varchar(36)                         not null
        primary key,
    user              varchar(36)                         not null,
    doctor            varchar(36)                         not null,
    type              varchar(36)                         not null,
    price             double                              not null,
    time              timestamp                           not null,
    real_name         varchar(255)                        not null,
    age               tinyint unsigned                    not null,
    gender            tinyint   default 0                 not null comment '性别，0代表男',
    created_at        timestamp default CURRENT_TIMESTAMP not null,
    is_final          tinyint   default 0                 not null,
    constraint registration_doctor_doctor_uuid_fk
        foreign key (doctor) references doctor (doctor_uuid)
            on update cascade on delete cascade,
    constraint registration_type_type_uuid_fk
        foreign key (type) references type (type_uuid)
            on update cascade on delete cascade,
    constraint registration_user_uuid_fk
        foreign key (user) references user (uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

create table token
(
    token_uuid varchar(36)                         not null
        primary key,
    user_uuid  varchar(36)                         not null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    expired_at timestamp                           not null,
    constraint token_user_uuid_fk
        foreign key (user_uuid) references user (uuid)
            on update cascade on delete cascade
)
    engine = InnoDB;

