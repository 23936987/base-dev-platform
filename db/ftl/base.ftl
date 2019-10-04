<#import "/include/base.ftl" as base>
<#import "/include/basic.ftl" as basic>
<#import "/include/tree.ftl" as tree>

drop table if exists ${测试基础场景表.en};
create table ${测试基础场景表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${状态.en} int(2) comment '2#状态#0 无效,1 生效,2 删除#stateType',
	${创建时间.en} datetime default now()  comment '创建时间',
	${修改时间.en} datetime default now() comment '修改时间',
	${生日.en} date comment '生日',
	${时刻.en} time comment '时刻',
	/*场景字段*/

	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${测试基础场景表.en}  comment '测试基础场景表#测试基础#这是一个测试基础场景的示例';

drop table if exists ${测试CRUD场景表.en};
create table ${测试CRUD场景表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${生日.en} date comment '生日',
	${时刻.en} time comment '时刻',
	/*场景字段*/
	<@basic.code />

	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${测试CRUD场景表.en}  comment '测试CRUD场景表#测试CRUD#测试CRUD场景表';

drop table if exists ${中英对照表.en};
create table ${中英对照表.en} ( 
	<@base.code/>
	${英文名称.en}  varchar(100) comment '英文名称',
	${中文名称.en}  varchar(100) comment '中文名称',
	/*场景字段*/
	/*约束*/ 
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${中英对照表.en}  comment '中英对照表';

drop table if exists ${数据字典表.en};
create table ${数据字典表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${类型.en} 		int(2) 			comment '2#类型#1 普通 2、其它表 #dictType',
	${表名称.en} 	varchar(20) 	comment '表名称',
	${值字段.en} 		varchar(20) 	comment '值字段',
	${名称字段.en} 		varchar(20) 	comment '名称字段',
	/*basic场景字段*/ 
	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${数据字典表.en}  comment '数据字典表';

drop table if exists ${数据字典明细表.en};
create table ${数据字典明细表.en} (
	<@base.code/>
	${编码.en} 		varchar(100) 	comment '编码',
	${名称.en} 		varchar(100) 	comment '名称',
	${数据字典主键.en}  varchar(36) 		comment '1#数据字典主键#${主键.en}#${数据字典表.en}#${名称.en}',
	/*basic场景字段*/ 
	foreign key(${数据字典主键.en}) references ${数据字典表.en}(${主键.en}),
	primary key(${主键.en})
)DEFAULT CHARSET=utf8;
alter table ${数据字典明细表.en}  comment '数据字典明细表';


drop table if exists ${参数配置表.en};
create table ${参数配置表.en}
(
<@base.code/>
	${参数编码.en} 	varchar(50) comment '参数编码',
	${参数值.en} 	text 		comment '参数值',
	/*basic场景字段*/ 
	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
alter table ${参数配置表.en}  comment '参数配置表';

DROP TABLE IF EXISTS ${系统用户表.en};
CREATE TABLE ${系统用户表.en}
(
    <@base.code/>
    ${手机号.en} 		varchar(100) not null 		comment '手机号',
    ${名称.en} 			varchar(100) not null 		comment '名称',
    ${密码.en} 			VARCHAR(100) not null 		COMMENT '密码',
    ${头像.en} 			text not null 				COMMENT '头像',
    ${区域编码.en} 			VARCHAR(20) not null 		COMMENT '区域编码',
    ${性别.en} 			int(1)  					COMMENT '2#性别#1 男 2 女#sex',
    ${锁定.en} 			int(1) not null 			COMMENT '2#锁定#0 否 1 是#yesOrNo',
    ${失败次数.en} 	int(10)  					COMMENT '失败次数',
    ${邮箱.en} 			varchar(100) 				comment '邮箱',
    ${管理员.en} 		int(1) not null default 0   COMMENT '2#管理员#0 否 1 是#yesOrNo',
    <@basic.code />
	/*约束*/
	primary key(${主键.en})
) DEFAULT CHARSET=utf8;
ALTER TABLE ${系统用户表.en}  COMMENT '系统用户表';