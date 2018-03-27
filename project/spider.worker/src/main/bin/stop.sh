#!/bin/bash
#Author WangYinPing
#DateTime 2013-06-13

logDir=/data/tuyou/spider/log/
mainClass=SpiderWorker
pkgName=com.lenovo.spider.worker.main.
mainClassCmd=${pkgName}${mainClass}
startRecord=start-${mainClass}.log

for pid in $(ps -ef | grep $mainClassCmd | awk '{print $2}');do
    kill -9 $pid > "${logDir}${stopRecord}" 2>&1
done

echo "stop.sh executed finish" >> "${logDir}${stopRecord}"
exit 0
