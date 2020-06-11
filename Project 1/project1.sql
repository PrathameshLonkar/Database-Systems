create table employees (eid char(3) primary key, ename varchar2(15), telephone# char(12));

create table customers (cid char(4) primary key, cname varchar2(15), telephone# char(12), visits_made number(4), last_visit_date date);

create table products (pid char(4) primary key, pname varchar2(15), qoh number(5), qoh_threshold number(4), original_price number(6,2), discnt_rate number(3,2));

create table purchases(pur# number(6) primary key,eid char(3) references employees(eid),pid char(4) references products(pid),cid char(4) references customers(cid),qty number(5), ptime date,total_price number(7,2));

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
insert into purchases values (100011, 'e02', 'p004', 'c006', 10, to_date('16-MAR-2020 16:54:40', 'DD-MON-YYYY HH24:MI:SS'), 9.90);
insert into purchases values (100012, 'e02', 'p008', 'c003', 2, to_date('18-FEB2020 15:56:38', 'DD-MON-YYYY HH24:MI:SS'), 698.60);
insert into purchases values (100013, 'e04', 'p006', 'c005', 2, to_date('30-JAN2020 10:38:25', 'DD-MON-YYYY HH24:MI:SS'), 35.91);
insert into purchases values (100014, 'e03', 'p009', 'c008', 3, to_date('18-MAR2020 10:54:06', 'DD-MON-YYYY HH24:MI:SS'), 134.84);


select cname, telephone# from customers where visits_made >= 3 and telephone# like '666%';
select customers.cname, customers.telephone# from customers left join purchases on purchases.cid = customers.cid where purchases.total_price>=100 and purchases.qty>=1 and purchases.ptime between (select sysdate-25 from dual) and (select sysdate from dual);
select products.pid, products.pname from products left join purchases on purchases.pid=products.pid where products.original_price*(1-products.discnt_rate)<10 and purchases.eid ='e01';
select p.pur#, p.pid, p.cid, p.eid from purchases p where p.eid in (select e.eid from employees e inner join customers c on substr(e.telephone#,1,3)=substr(c.telephone#,1,3)) and p.pid not in (select p1.pid from products p1 where  p1.pname='TV');
SELECT pur#, to_char(ptime,'Mon/dd/yyyy,day, hh24:mi:ss ') as ptime from purchases order by ptime;
select e.eid from employees e inner join customers c on substr(e.telephone#,1,3)=substr(c.telephone#,1,3) group by e.eid;
select cname from customers where (customers.cid) != (select cid from purchases where pid = 'p007');
select eid, ename from employees where (employees.eid) != all(select purchases.eid from purchases left join products on purchases.pid=products.pid where products.original_price<200);
select distinct p1.cid from purchases p1 inner join (select p.pid from products p where p.original_price>=200) x on x.pid=p1.pid;
select distinct e.eid, e.ename from employees e inner join purchases p on e.eid=p.eid where p.cid in (select cid from customers where visits_made>=3);
select p1.pid, p1.pname,p1.qoh, p1.qoh_threshold, p1.original_price, p1.discnt_rate  from products p1 inner join purchases p on p1.pid=p.pid where p.cid='c001' and p.pid not in (select pid from purchases where cid ='c006');
select distinct p.cid from purchases p where p.pid in (select pid from purchases where cid='c006');
select c.cname from customers c where c.cid in (select p.cid from purchases p left join products p1 on p.pid = p1.pid where ((p1.original_price*p.qty)-p.total_price)>100);
select c.cname from customers c where c.cid in (select p.cid from purchases p where p.total_price >=(select max(total_price/qty) from purchases));
select * from products p1 where p1.pid in (select p.pid from purchases p group by p.pid having count(*)>=2);
select p.pur#, p.total_price from purchases p where p.total_price >= any (select total_price from purchases where cid='c006');
select c.cid, c.cname, x.count from customers c inner join (select p.cid, count(p.pid) as count from purchases p group by p.cid order by count desc) x on x.cid=c.cid;
select c.cname,x.cid , x.count,x.total  from customers c inner join (select p.cid, count(p.cid) as count, sum(total_price) as total  from purchases p group by p.cid order by count desc) x on x.cid=c.cid where x.count in (select * from (select count(cid) as count from purchases group by cid order by count desc ) FETCH FIRST 1 ROWS ONLY);
select p1.pname, x.count from products p1 inner join (select * from (select p.pid, sum(qty) as count from purchases p group by p.pid order by count desc)  FETCH FIRST 1 ROWS ONLY) x on x.pid=p1.pid;
select b.cid, a.total from (select cid,visits_made from customers c where c.cid in (select cid from (select p.cid, count(p.cid) as count, sum(total_price) as total  from purchases p group by p.cid order by total desc) x FETCH FIRST 2 ROWS ONLY)) b inner join (select x.cid,x.total from (select p.cid, count(p.cid) as count, sum(total_price) as total  from purchases p group by p.cid order by total desc) x FETCH FIRST 2 ROWS ONLY) a on b.cid=a.cid ;

