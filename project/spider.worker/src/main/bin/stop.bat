@echo off

rem Author WangYinPing
rem DateTime 2013-04-15

set "logDir=E:\"
set "mainClass=SpiderWorker"
set "pkgName=com.lenovo.spider.worker.main."
set "mainClassCmd=%pkgName%%mainClass%"
set "startRecord=start-%mainClass%.log"

if not exist %logDir% md %logDir%

for /f "tokens=1,2 delims= " %%i in ('%javaHomePath%\bin\jps -l') do (
    if %%j==%mainClassCmd% taskkill /pid %%i /f /t > "%logDir%%stopRecord%" 2>&1
)
echo stop.bat executed finish >> "%logDir%%stopRecord%"
exit 0
