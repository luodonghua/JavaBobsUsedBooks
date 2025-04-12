-- sqlplus sys/Welcome123_@localhost/xepdb1 as sysdba
create tablespace demotbs datafile '/opt/oracle/oradata/demotbs01.dbf' size 10M autoextend on;
create user demo identified by Welcome123_ default tablespace demotbs;
alter user demo quota unlimited on demotbs;
grant create session, resource to demo;
grant create job to demo;
grant execute on dbms_scheduler to demo;
grant create materialized view to demo;
