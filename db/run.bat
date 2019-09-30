
echo off
echo 生成SQL_js文件
call generatorSql 
echo 清理外键
call delforeign
pause