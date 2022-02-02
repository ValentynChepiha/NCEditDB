create table SALGRADE
(
    GRADE NUMBER(1) not null
        constraint SALGRADE_PK
            primary key,
    MINSAL FLOAT not null,
    HISAL FLOAT not null
)
/


create table DEPT
(
    DEPTNO NUMBER(2) not null
        constraint DEPT_PK
            primary key,
    DNAME VARCHAR2(15) not null
        constraint DEPT_NAEM_UK
            unique,
    LOC VARCHAR2(15) not null
)
/



create table EMP
(
	EMPNO NUMBER(4) not null
		constraint EMP_PK
			primary key,
	ENAME VARCHAR2(10) not null,
	JOB VARCHAR2(10) not null,
	MGR NUMBER(4),
	HIREDATE DATE not null,
	SAL FLOAT not null,
	COMM FLOAT,
	DEPTNO NUMBER(2)
		constraint EMP_DEPTNO_FK
			references DEPT
)
/

create or replace trigger BI_EMP
	before insert
	on EMP
	for each row
begin
  if :NEW."EMPNO" is null then
    select "EMP_SEQ".nextval into :NEW."EMPNO" from dual;
  end if;
end;
/

