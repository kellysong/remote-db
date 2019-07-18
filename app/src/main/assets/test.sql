
-- mysql测试

CREATE DATABASE IF NOT EXISTS test_db;
USE test_db;

drop table if exists test_table;
-- 测试表,本次测试表字段统一使用下划线分割字段
create table test_table (
  id bigint primary key auto_increment,
  test_id1 int,
  test_id2 smallint,
  test_id3 bigint,
  test_money1 float,
  test_money2 double,
  test_name varchar(32),
  head_base64 text,
  test_age1 int,
   test_age2 smallint,
   test_age3 bigint,
   price1 float,
   price2 double,
  test_img blob,
  birth_date date,
  create_time timestamp,
  test_my_remark varchar(255)
) ;

select * from test_table;
