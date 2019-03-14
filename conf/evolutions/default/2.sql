# --- !Ups
insert into department(name) values ('Administration');
insert into department(name) values ('Finance');
insert into department(name) values ('Human Resources');
insert into department(name) values ('Advertising');
insert into department(name) values ('Internship');

insert into project(name,description) values ('Web Development','Describing Creating a website mumbo jumbo....');
insert into project(name,description) values ('Main Program Development','Describing Developing a program mumbo jumbp.....');
insert into project(name,description) values ('Summer Recruiting','Describing Recruitment mumbo jumbo.....');
insert into project(name,description) values ('Training Sessions','Describing Training sessions mumbo jumbo.......');

insert into address(id,nr,street,town,city,eircode) values (1,'1','Johnsonville','Mayberry','Dublin','EI2334X2');


insert into employee(id,fname,sname,position,email,dob,password,aid,department_id) values ('admin','Dave','Gold', 'Web Developer','dave@aquatech.com','1990-02-01','password',1,1);
insert into employee(id,fname,sname,position,email,dob,password,department_id) values ('e233fs','Jack','Sparrow', 'Finance Manager','jack@aquatech.com','1997-09-08','password',2);
insert into employee(id,fname,sname,position,email,dob,password,department_id) values ('23dde3','Orange','Orangutang','Junior Accountant','orange@aquatech.com','1994-12-08','password',2);
insert into employee(id,fname,sname,position,email,dob,password,department_id) values ('uttt43','Morgan','Freeman', 'Recruiter','morgan@aquatech.com','1980-08-22','password',3);
insert into employee(id,fname,sname,position,email,dob,password,department_id) values ('234ffr','Duce','Goldberg', 'Campaign Manager','duce@aquatech.com','1992-07-23','password',4);
insert into employee(id,fname,sname,position,email,dob,password,department_id) values ('t2355','Ham','Chickenson', 'Trainee','ham@aquatech.com','1999-05-07','password',5);