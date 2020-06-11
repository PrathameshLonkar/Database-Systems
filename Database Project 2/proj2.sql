drop table purchases;
drop table supply;
drop table suppliers;
drop table products;
drop table logs;
drop table customers;
drop table employees;
drop sequence pur; 
drop sequence sup;
drop sequence logf;
drop package proj2;
--drop trigger trigger1;
--drop trigger trigger2;
--drop trigger trigger3;
--drop trigger trigger4;

create table employees
(eid char(3) primary key,
ename varchar2(15),
telephone# char(12));

create table customers
(cid char(4) primary key,
cname varchar2(15),
telephone# char(12),
visits_made number(4),
last_visit_date date);

create table products
(pid char(4) primary key,
pname varchar2(15),
qoh number(5),
qoh_threshold number(4),
original_price number(6,2),
discnt_rate number(3,2) check(discnt_rate between 0 and 0.8));

create table purchases
(pur# number(6) primary key,
eid char(3) references employees(eid),
pid char(4) references products(pid),
cid char(4) references customers(cid),
qty number(5),
ptime date,
total_price number(7,2));

create table suppliers
(sid char(2) primary key,
sname varchar2(15) not null unique,
city varchar2(15),
telephone# char(12));

create table supply
(sup# number(4) primary key,
pid char(4) references products(pid),
sid char(2) references suppliers(sid),
sdate date,
quantity number(5));

create table logs
(log# number(5) primary key,
who varchar2(12) not null,
otime date not null,
table_name varchar2(20) not null,
operation varchar2(6) not null,
key_value varchar2(6)); 

create sequence pur
increment by 1
start with 100011
minvalue 000000
maxvalue 1000000
nocycle
cache 20;

create sequence sup
increment by 1
start with 5
minvalue 0000
maxvalue 1000
nocycle
cache 20;

create sequence logf
increment by 1
start with 0
minvalue 00000
maxvalue 10000
nocycle
cache 20;
insert into employees values ('e01', 'Peter', '666-555-1234');
insert into employees values ('e02', 'David', '777-555-2341');
insert into employees values ('e03', 'Susan', '888-555-3412');
insert into employees values ('e04', 'Anne', '666-555-4123');
insert into employees values ('e05', 'Mike', '444-555-4231');
insert into customers values ('c001', 'Kathy', '666-555-4567', 3, '12-MAR-20');
insert into customers values ('c002', 'John', '888-555-7456', 1, '08-MAR-20');
insert into customers values ('c003', 'Chris', '666-555-6745', 3, '18-FEB-20');
insert into customers values ('c004', 'Mike', '999-555-5674', 1, '20-MAR-20');
insert into customers values ('c005', 'Mike', '777-555-4657', 2, '30-JAN-20');
insert into customers values ('c006', 'Connie', '777-555-7654', 2, '16-MAR-20');
insert into customers values ('c007', 'Katie', '888-555-6574', 1, '12-MAR-20');
insert into customers values ('c008', 'Joe', '666-555-5746', 1, '18-MAR-20');
insert into products values ('p001', 'stapler', 60, 20, 9.99, 0.1);
insert into products values ('p002', 'TV', 6, 5, 249, 0.15);
insert into products values ('p003', 'camera', 20, 5, 148, 0.2);
insert into products values ('p004', 'pencil', 100, 10, 0.99, 0.0);
insert into products values ('p005', 'chair', 10, 8, 12.98, 0.3);
insert into products values ('p006', 'lamp', 10, 6, 19.95, 0.1);
insert into products values ('p007', 'tablet', 50, 10, 149, 0.2);
insert into products values ('p008', 'computer', 5, 3, 499, 0.3);
insert into products values ('p009', 'powerbank', 20, 5, 49.95, 0.1);
insert into purchases values (100001, 'e01', 'p002', 'c001', 1, to_date('12-JAN2020 10:34:30', 'DD-MON-YYYY HH24:MI:SS'), 211.65);
insert into purchases values (100002, 'e01', 'p003', 'c001', 1, to_date('20-FEB2020 11:23:36', 'DD-MON-YYYY HH24:MI:SS'), 118.40);
insert into purchases values (100003, 'e02', 'p004', 'c002', 5, to_date('08-MAR2020 09:30:50', 'DD-MON-YYYY HH24:MI:SS'), 4.95);
insert into purchases values (100004, 'e01', 'p005', 'c003', 2, to_date('23-FEB2020 16:23:35', 'DD-MON-YYYY HH24:MI:SS'), 18.17);
insert into purchases values (100005, 'e04', 'p007', 'c004', 1, to_date('20-MAR2020 13:38:55', 'DD-MON-YYYY HH24:MI:SS'), 119.20);
insert into purchases values (100006, 'e03', 'p008', 'c001', 1, to_date('12-MAR2020 15:22:10', 'DD-MON-YYYY HH24:MI:SS'), 349.30);
insert into purchases values (100007, 'e03', 'p006', 'c003', 2, to_date('10-FEB2020 17:12:20', 'DD-MON-YYYY HH24:MI:SS'), 35.91);
insert into purchases values (100008, 'e03', 'p006', 'c005', 1, to_date('16-JAN2020 12:22:15', 'DD-MON-YYYY HH24:MI:SS'), 17.96);
insert into purchases values (100009, 'e03', 'p001', 'c007', 1, to_date('12-MAR2020 14:44:23', 'DD-MON-YYYY HH24:MI:SS'), 8.99);
insert into purchases values (100010, 'e04', 'p002', 'c006', 1, to_date('20-JAN2020 17:32:37', 'DD-MON-YYYY HH24:MI:SS'), 211.65);
insert into suppliers values ('s1', 'Tom' , 'arizona' , '666-333-555');
insert into suppliers values ('s2', 'Dan' , 'florida' , '666-333-556');
insert into suppliers values ('s3', 'Nancy' , 'colorado' , '666-333-557');
insert into suppliers values ('s4', 'Harry' , 'kansas' , '666-333-558');
insert into supply values('0', 'p001', 's1', '12-JAN-2020', '10');
insert into supply values('1', 'p002', 's2', '22-JAN-2020', '20');
insert into supply values('2', 'p003', 's3', '12-MAR-2020', '30');
insert into supply values('3', 'p004', 's4', '17-AUG-2020', '40');
insert into supply values('4', 'p002', 's1', '12-FEB-2020', '3');

create package proj2 as
procedure insert_in_purchase(p_id in products.pid%type,e_id in employees.eid%type,c_id in customers.cid%type,qty_no in purchases.qty%type, message out varchar2,message1 out varchar2);
procedure insert_into_supply(p_id in products.pid%type, s_id in supply.sid%type, s_date in supply.sdate%type, qt in supply.quantity%type);
procedure insert_into_log(w in logs.who%type, o_time in logs.otime%type, tablename in logs.table_name%type,op in logs.operation%type, keyvalue in logs.key_value%type);
procedure insert_in_products(p_id in products.pid%type,p_name in products.pname%type,qoh_no in products.qoh%type,qoh_thre in products.qoh_threshold%type, original_p in
products.original_price%type,discount in products.discnt_rate%type,message out varchar2);

procedure show_products(rc out sys_refcursor);
procedure show_purchases(rc out sys_refcursor);

procedure show_customers(rc out sys_refcursor);
procedure show_employees(rc out sys_refcursor);
procedure show_supply(rc out sys_refcursor);
procedure show_suppliers(rc out sys_refcursor);
procedure show_logs(rc out sys_refcursor);
procedure show_monthly_sale(rc out sys_refcursor, id in products.pid%type,message out varchar2);
procedure show_EmployeeInfo(rc out sys_refcursor, id in employees.eid%type,message out varchar2);
procedure show_CustomerInfo(rc out sys_refcursor, id in customers.cid%type,message out varchar2);
procedure show_PurchaseInfo(rc out sys_refcursor, id in purchases.cid%type,message out varchar2);

end proj2;
/
show errors

create or replace package body proj2 as


procedure insert_in_purchase(p_id in products.pid%type,e_id in employees.eid%type,c_id in customers.cid%type,qty_no in purchases.qty%type, message out varchar2, message1 out varchar2) is
price products.original_price%type;
quantity products.qoh%type;
quant_threshold products.qoh_threshold%type;
quantity1 products.qoh%type;
quant_threshold1 products.qoh_threshold%type;
new_sid suppliers.sid%type;
M number default 10;
new_supply supply.quantity%type;
n_pid products.pid%type;
n_cid customers.cid%type;
n_eid employees.eid%type;
new_supply1 supply.quantity%type;
begin
select count(employees.eid) into n_eid from employees where employees.eid=e_id;
select count(customers.cid) into n_cid from customers where customers.cid=c_id;
select count(products.pid) into n_pid from products where products.pid=p_id;
select original_price, qoh,qoh_threshold into price, quantity, quant_threshold from products where pid=p_id; 
if(qty_no > quantity) then
message:='invalid size of quantity';
else
	if(n_pid<1) then
	message:='Invalid Pid';
	else
		if(n_eid<1) then 
		message:='Invalid Eid';
		else
			if(n_cid<1) then
			message:='Invalid Cid';
			else
    
			    insert into purchases values (pur.nextval, e_id, p_id, c_id, qty_no, sysdate,(qty_no * price));
			    update products set products.qoh=products.qoh-qty_no where products.pid=p_id;
			    select qoh,qoh_threshold into quantity1, quant_threshold1 from products where pid=p_id;
			    if(quantity1<quant_threshold1) then
				    new_supply := (M+quantity1+5); 
				    message:= 'quantity is less than the threshold';
				    select supply.sid into new_sid from supply where supply.pid=p_id order by supply.sid asc fetch first 1 rows only;
				    insert into supply values (sup.nextval,p_id,new_sid,sysdate,new_supply);
				    select products.qoh into new_supply1 from products where products.pid=p_id;
				    message1:= 'The new quantity ordered is:' ||new_supply1; 
			    end if;
			end if;
		end if;
    end if;
end if;
end insert_in_purchase;




procedure insert_in_products(p_id in products.pid%type,p_name in products.pname%type,qoh_no in products.qoh%type,qoh_thre in products.qoh_threshold%type, original_p in 
products.original_price%type,discount in products.discnt_rate%type,message out varchar2) is
cnt_pid number;
begin
select count(products.pid) into cnt_pid from products where products.pid=p_id;
if(cnt_pid>0)then
message:= 'Product Id already Exists';
else
insert into products values (p_id, p_name, qoh_no, qoh_thre, original_p, discount);
end if;
end insert_in_products;



procedure show_monthly_sale(rc out sys_refcursor, id in products.pid%type,message out varchar2) is
valid_pid products.pid%type;
begin
select count(products.pid) into valid_pid from products where products.pid = id;
if(valid_pid<1)then
message:='Invalid Pid';
else
open rc for
	select products.pid, products.pname, to_char(purchases.ptime, 'Mon-YYYY') as time, sum(purchases.qty) as qty, sum(purchases.total_price) as 
total_price,(sum(purchases.total_price)/sum(purchases.qty)) as avg
	from products, purchases
	where products.pid=purchases.pid AND products.pid=id
	group by to_char(purchases.ptime,'Mon-YYYY'),products.pid, products.pname;
	end if;
end show_monthly_sale;


procedure show_EmployeeInfo(rc out sys_refcursor, id in employees.eid%type,message out varchar2) is
cnt_emp number;
begin
select count(employees.eid) into cnt_emp from employees where employees.eid=id;
if(cnt_emp<1) then
message:= 'Invalid Eid';
    else
	open rc for
    select * from employees where employees.eid=id;
end if;
end show_EmployeeInfo;

procedure show_CustomerInfo(rc out sys_refcursor, id in customers.cid%type,message out varchar2) is
cnt_cus number;
begin
select count(customers.cid) into cnt_cus from customers where customers.cid=id;
if(cnt_cus<1) then
message:= 'Invalid Cid';
	else
    open rc for
    select * from customers where customers.cid=id;
	
end if;
end show_CustomerInfo;

procedure show_PurchaseInfo(rc out sys_refcursor, id in purchases.cid%type,message out varchar2) is
cnt_pur number;
begin
select count(purchases.cid) into cnt_pur from purchases where purchases.cid=id;
if(cnt_pur<1) then
message:= 'Invalid Purchase Info';
	else
    open rc for
    select * from purchases where purchases.cid=id;
	
end if;
end show_PurchaseInfo;

procedure insert_into_supply(p_id in products.pid%type, s_id in supply.sid%type, s_date in supply.sdate%type,qt in supply.quantity%type) is
begin
insert into supply values (sup.nextval, p_id, s_id, s_date, qt);
end insert_into_supply;

procedure insert_into_log(w in logs.who%type, o_time in logs.otime%type, tablename in logs.table_name%type, op in logs.operation%type, keyvalue in logs.key_value%type) is
begin
insert into logs values (logf.nextval, w, o_time, tablename, op, keyvalue);
end insert_into_log;

procedure show_products(rc out sys_refcursor) is 
begin
open rc for
    select * from products;
    end show_products; 
procedure show_purchases(rc out sys_refcursor) is
begin
open rc for
    select * from purchases;
    end show_purchases;
procedure show_employees(rc out sys_refcursor) is
begin
open rc for
    select * from employees;
    end show_employees;
procedure show_customers(rc out sys_refcursor) is
begin
open rc for
    select * from customers;
    end show_customers;
procedure show_suppliers(rc out sys_refcursor) is
begin
open rc for
    select * from suppliers;
    end show_suppliers;
procedure show_supply(rc out sys_refcursor) is
begin
open rc for
    select * from supply;
    end show_supply;
procedure show_logs(rc out sys_refcursor) is
begin
open rc for
    select * from logs;
    end show_logs;
end proj2;
/
show errors
create or replace trigger trigger1
after
insert
on purchases
for each row
declare
n_cid char(4);
begin
n_cid := :new.cid;
insert into logs values (logf.nextval,user, sysdate, 'purchases','insert', :NEW.pur#);
update customers set customers.visits_made=customers.visits_made+1 , customers.last_visit_date=sysdate where customers.cid=n_cid;
end trigger1;
/
show errors
/
create or replace trigger trigger4
after
insert
on supply
for each row
declare 
n_pid char(4);
n_qty number(5);
begin
n_pid := :new.pid;
n_qty := :new.quantity;
insert into logs  values (logf.nextval,user, sysdate, 'supply','insert', :NEW.sup#);
update products set products.qoh=products.qoh+n_qty where products.pid=n_pid;

end trigger4;
/
show errors

/
create or replace trigger trigger2
after
update
of qoh on  products
for each row
begin
insert into logs values (logf.nextval,user, sysdate, 'products','update', :NEW.pid);
end trigger2;
/
show errors
/
create or replace trigger trigger3
after
update
of visits_made on customers
for each row
begin
insert into logs values (logf.nextval,user, sysdate, 'customers','update', :NEW.cid);
end trigger3;
/
show errors

