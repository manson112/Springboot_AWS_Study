#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=Springboot_AWS_Study

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fla Springboot_AWS_Study | grep jar | awk '{print $1}')

echo "현재 구동중인 애플리케이션 PID: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
	echo "There's no running application"
else
	echo "> kill -15 $CURRENT_PID"
	kill -15 "$CURRENT_PID"
	sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
      -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-real.properties \
      -Dspring.profiles.active=real \
      $JAR_NAME > $REPOSITORY/nohub.out 2>&1 &