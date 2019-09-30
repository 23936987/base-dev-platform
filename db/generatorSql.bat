echo off 
set base_path=%cd%

call java -jar %base_path%\generatorSql.jar  %base_path%\ftl %base_path%\model