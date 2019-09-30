<#import "/include/base.ftl" as base>
<#import "/include/basic.ftl" as basic>
<#import "/include/tree.ftl" as tree>


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