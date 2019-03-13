# --- !Ups
insert into department(name) values ('Administration');
insert into address(nr,street,town,city,eircode) values ('1','Johnsonville','Mayberry','Dublin','EI2334X2');
insert into employee(id, fname,sname,position,email,dob,password,aid,department_id) values ('admin','Dave','Gold', 'Web Developer','dave@aquatech.com','1990-02-01','admin',1,1);