@echo off

rem Author WangYinPing
rem DateTime 2013-04-15

rem open the comment below,will use self defined JAVA_HOME
rem set "JAVA_HOME="

rem you'd better you \ dir path, because bat command is strange
set "logDir=E:\"
set "mainClass=SpiderWorker"
set "pkgName=com.lenovo.spider.worker.main."
set "mainClassCmd=%pkgName%%mainClass%"
set "startRecord=start-%mainClass%.log"

if not exist %logDir% md %logDir%

rem check JAVA_HOME
if not exist %JAVA_HOME% (
	echo "JAVA_HOME not configured" >> "%logDir%%startRecord%"
    exit 1 
)

rem handle JAVA_HOME
set "javaHomePath=%JAVA_HOME%"
set "lastChar=%javaHomePath:~-1%"
if not "%lastChar%"=="\" set javaHomePath=%javaHomePath%\
for %%x in ("%javaHomePath%") do set javaHomePath=%%~sx

rem check process exist
for /f "tokens=1,2 delims= " %%i in ('%javaHomePath%\bin\jps -l') do (
    if %%j==%mainClassCmd% (
    	echo "%mainClassCmd% already exists" >> "%logDir%%startRecord%" 2>&1
    	exit 2
    )
)

set "opts=-XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=%logDir%%mainClass%Err.log -XX:HeapDumpPath=%logDir%o%mainClass%.dump"

rem export jars to classpath
set binDir=%~dp0
pushd %binDir%
cd %binDir%
cd ..\lib\
set "libDir=%cd%"

setlocal enabledelayedexpansion
    set myClassPath=
	for /r %tmpPath% %%i in (*.jar) do set myClassPath=%%i;!myClassPath!
	set "CLASSPATH=%myClassPath%"
    echo %CLASSPATH% >> "%logDir%%startRecord%"
	start /b %javaHomePath%bin\javaw %opts% %mainClassCmd% >> %logDir%%mainClass%Java.log  2>&1
endlocal

echo start.bat executed finish >> "%logDir%%startRecord%"

popd

exit 0