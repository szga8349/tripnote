#!/bin/bash
#Author WangYinPing
#DateTime 2012-10-16

#open the comment below,will use self defined JAVA_HOME
#JAVA_HOME=

logDir=/data/tuyou/spider/log/
mainClass=SpiderWorker
pkgName=com.lenovo.spider.worker.main.
mainClassCmd=${pkgName}${mainClass}
startRecord=start-${mainClass}.log

if [ ! -d "$logDir" ];then mkdir -p "$logDir" fi

#Start application in background

#check JAVA_HOME
javaHomePath=$JAVA_HOME
javaHomePathLength=${#javaHomePath}
if [ "$javaHomePathLength" -lt "3" ]; then
    echo "JAVA_HOME not configured" >> "${logDir}${startRecord}"
    exit 1
fi

#handle JAVA_HOME
position=$(($javaHomePathLength - 1))
subStr=${javaHomePath:position}
if [ "$subStr"x != "/"x ]; then
    javaHomePath=${javaHomePath}"/"
fi

#check process exist
pidStr=$(ps -ef | grep $mainClassCmd | grep -v grep | awk '{print $2}')
if [ -n "$pidStr" ];then
	echo "$mainClassCmd process already exists" >> "${logDir}${startRecord}"
	exit 2
fi


#add dump file
opts="-XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=${logDir}${mainClass}Err.log -XX:HeapDumpPath=${logDir}${mainClass}.dump"

cd "$(dirname "$0")"
cd ..
#export jars to classpath
curDir=$(pwd)
libDir=${curDir}'/lib/*.jar'
jarpath=""
for file in $(ls ${libDir});do
    temp="${bindir}${file}:"
    jarpath=${jarpath}${temp}
done
export CLASSPATH=$jarpath$CLASSPATH
echo $CLASSPATH >> "${logDir}${startRecord}"


nohup ${javaHomePath}bin/java ${opts} $startAgentCmd >> ${logDir}"oneMainJavaRecord.log" 2>&1 &

#Shell run completes tips
echo "start.sh executed finish" >> "${logDir}${startRecord}"

exit 0