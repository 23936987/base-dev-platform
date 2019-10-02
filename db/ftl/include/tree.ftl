
<#macro code  isParent=false>

	
	${名称.en} varchar(500) comment '名称', 
	${名称路径.en} varchar(500) comment '名称路径', 
	${级别.en} int(2) comment '级别',
	${左值.en} int(11) comment '左值',
	${右值.en} int(11) comment '右值',
	${叶子.en} int(1) comment '2#叶子#0 否,1 是#yesOrNo', 

<#if isParent>
	${上级.en} BIGINT(20) comment '上级',
</#if>	 
</#macro>