CREATE TABLE t_test
(
	id BIGINT(20) not null comment '主键',
	code VARCHAR(100)  comment '编码',
	name VARCHAR(100)  comment '名称',
	table_name VARCHAR(100)  comment '表名称',
	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
ALTER TABLE sys_biz_object  COMMENT '测试类定义表#测试#项目中所有业务类都需要在此申明';