
drop table if exists t_test_base;
create table t_test_base (
	id varchar(36) not null comment '主键',
	code 		varchar(100) 	comment '编码',
	name 		varchar(100) 	comment '名称',
	state int(2) comment '2#状态#0 无效,1 生效,2 删除#stateType',
	create_time datetime default now()  comment '创建时间',
	modify_time datetime default now() comment '修改时间',
	birth_date date comment '生日',
	times time comment '时刻',
	/*场景字段*/

	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table t_test_base  comment '测试基础场景表#测试#这是一个测试基础场景的示例';


drop table if exists sys_contrast;
create table sys_contrast ( 
	id varchar(36) not null comment '主键',
	name_en  varchar(100) comment '英文名称',
	name_cn  varchar(100) comment '中文名称',
	/*场景字段*/ 

	/*约束*/ 
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table sys_contrast  comment '中英对照表';

drop table if exists sys_dict;
create table sys_dict (
	id varchar(36) not null comment '主键',
	code 		varchar(100) 	comment '编码',
	name 		varchar(100) 	comment '名称',
	types 		int(2) 			comment '2#类型#1 普通 2、其它表 #dict_type',
	table_name 	varchar(20) 	comment '表名称',
	value_field 		varchar(20) 	comment '值字段',
	name_field 		varchar(20) 	comment '名称字段',
	/*basic场景字段*/ 
	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table sys_dict  comment '数据字典表';

drop table if exists sys_dict_list;
create table sys_dict_list (
	id varchar(36) not null comment '主键',
	code 		varchar(100) 	comment '编码',
	name 		varchar(100) 	comment '名称',
	dict_id  varchar(36) 		comment '1#数据字典主键#id#sys_dict#name',
	/*basic场景字段*/ 
	foreign key(dict_id) references sys_dict(id),
	primary key(id)
)DEFAULT CHARSET=utf8;
alter table sys_dict_list  comment '数据字典明细表';


drop table if exists sys_parameter;
create table sys_parameter
(
	id varchar(36) not null comment '主键',
	param_code 	varchar(50) comment '参数编码',
	param_value 	text 		comment '参数值',
	/*basic场景字段*/ 
	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table sys_parameter  comment '参数配置表';