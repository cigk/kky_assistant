#!/bin/bash

apkPath="app/build/outputs/apk/"
buildTime=`date +%Y%m%d%H%M`

create(){

	./gradlew assemble$1
	#2>/dev/null

	if [ $? -eq 0 ]; then
		echo  "$1 Build SUCCESS! "

		ApkName=$(basename `ls -t $apkPath | grep -i "$1" | grep -v -i "unaligned" | head -1`)
		ApkPath=$apkPath$ApkName

		curl -k -T ${ApkPath}  "https://jinlu:jinxiaolu@pan.kuaikuaiyu.com/remote.php/webdav/Android/Assistant/$1/${ApkName//.apk/_$buildTime.apk}"
	
	else
		echo  "$1 Build Error! "
		exit 1
	fi
}


create "Debug" 
create "Release" 
#create "Alpha"