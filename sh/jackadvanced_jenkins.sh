#!/usr/bin/env bash
app_name='jackadvanced-docker-jenkins'
docker stop ${app_name}
echo '----stop container----'
docker rm ${app_name}
echo '----rm container----'
docker run -p 8085:8085 --name ${app_name} \
-v /etc/localtime:/etc/localtime \
-v /mydata/app/${app_name}/logs:/var/logs \
-d jackadvanced/jackadvanced
echo '----start container----'

