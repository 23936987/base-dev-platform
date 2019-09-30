echo off
set base_path=%cd%

call java -jar %base_path%\delforeign.jar %base_path%\model\  %base_path%\sql\ 
 