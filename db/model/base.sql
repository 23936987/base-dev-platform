
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
alter table t_test_base  comment '测试基础场景表#测试基础#这是一个测试基础场景的示例';

drop table if exists t_test_crud;
create table t_test_crud (
	id varchar(36) not null comment '主键',
	code 		varchar(100) 	comment '编码',
	name 		varchar(100) 	comment '名称', 
	birth_date date comment '生日',
	times time comment '时刻',
	/*场景字段*/
	 update_key varchar(50) not null comment '更改键',
	 create_time datetime default now()  comment '创建时间',
	 modify_time datetime default now() comment '修改时间',
	 create_user bigint(20) comment '创建人',
	 modify_user bigint(20) comment '修改人',
	 create_user_name varchar(100) comment '创建人名称',
	 modify_user_name varchar(100) comment '修改人名称',
	 seq int(10) comment '排序',
	 remark varchar(500) comment '备注',
	state int(2) comment '2#状态#0 无效,1 生效,2 删除#state_type',

	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
alter table t_test_crud  comment '测试CRUD场景表#测试CRUD#测试CRUD场景表';

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
	types 		int(2) 			comment '2#类型#1 普通 2、其它表 #dictType',
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

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
	id varchar(36) not null comment '主键',
    phone_no 		varchar(100) not null 		comment '手机号',
    name 			varchar(100) not null 		comment '名称',
    password 			VARCHAR(100) not null 		COMMENT '密码',
    avatar 			text not null 				COMMENT '头像',
    area_code 			VARCHAR(20) not null 		COMMENT '区域编码',
    gender 			int(1)  					COMMENT '2#性别#1 男 2 女#sex',
    locked 			int(1) not null 			COMMENT '2#锁定#0 否 1 是#yesOrNo',
    fail_times 	int(10)  					COMMENT '失败次数',
    email 			varchar(100) 				comment '邮箱',
    admin 		int(1) not null default 0   COMMENT '2#管理员#0 否 1 是#yesOrNo',
	 update_key varchar(50) not null comment '更改键',
	 create_time datetime default now()  comment '创建时间',
	 modify_time datetime default now() comment '修改时间',
	 create_user bigint(20) comment '创建人',
	 modify_user bigint(20) comment '修改人',
	 create_user_name varchar(100) comment '创建人名称',
	 modify_user_name varchar(100) comment '修改人名称',
	 seq int(10) comment '排序',
	 remark varchar(500) comment '备注',
	state int(2) comment '2#状态#0 无效,1 生效,2 删除#state_type',
	/*约束*/
	primary key(id)
) DEFAULT CHARSET=utf8;
ALTER TABLE sys_user  COMMENT '系统用户表';