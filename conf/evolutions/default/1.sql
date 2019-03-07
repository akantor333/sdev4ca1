# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                            bigint auto_increment not null,
  nr                            varchar(255),
  street                        varchar(255),
  town                          varchar(255),
  city                          varchar(255),
  eircode                       varchar(255),
  employee_id                   varchar(255),
  constraint uq_address_employee_id unique (employee_id),
  constraint pk_address primary key (id)
);

create table department (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_department primary key (id)
);

create table employee (
  type                          varchar(31) not null,
  id                            varchar(255) not null,
  fname                         varchar(255),
  sname                         varchar(255),
  position                      varchar(255),
  email                         varchar(255),
  dob                           date,
  password                      varchar(255),
  department_id                 bigint,
  constraint pk_employee primary key (id)
);

create table employee_project (
  employee_id                   varchar(255) not null,
  project_id                    bigint not null,
  constraint pk_employee_project primary key (employee_id,project_id)
);

create table project (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_project primary key (id)
);

alter table address add constraint fk_address_employee_id foreign key (employee_id) references employee (id) on delete restrict on update restrict;

alter table employee add constraint fk_employee_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_employee_department_id on employee (department_id);

alter table employee_project add constraint fk_employee_project_employee foreign key (employee_id) references employee (id) on delete restrict on update restrict;
create index ix_employee_project_employee on employee_project (employee_id);

alter table employee_project add constraint fk_employee_project_project foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_employee_project_project on employee_project (project_id);


# --- !Downs

alter table address drop constraint if exists fk_address_employee_id;

alter table employee drop constraint if exists fk_employee_department_id;
drop index if exists ix_employee_department_id;

alter table employee_project drop constraint if exists fk_employee_project_employee;
drop index if exists ix_employee_project_employee;

alter table employee_project drop constraint if exists fk_employee_project_project;
drop index if exists ix_employee_project_project;

drop table if exists address;

drop table if exists department;

drop table if exists employee;

drop table if exists employee_project;

drop table if exists project;

