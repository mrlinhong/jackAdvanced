#!/usr/bin/env bash
# 可以作为通用脚本来使用的模板脚本，只需改变其中的一些参数即可，具体执行流程为：停止旧服务->删除旧容器->删除旧镜像->打包新镜像->运行新镜像。
# 定义应用组名
group_name='jackadvanced'
# 定义应用名称
app_name='jackadvanced-docker-jenkins'
# 定义应用版本
app_version='latest'
# 定义应用环境
profile_active='prod'
echo '----copy jar----'
docker stop ${app_name}
echo '----stop container----'
docker rm ${app_name}
echo '----rm container----'
docker rmi ${group_name}/${group_name}:${app_version}
echo '----rm image----'
# 打包编译docker镜像
docker build -t ${group_name}/${group_name}:${app_version} .
echo '----build image----'
docker run -p 8085:8085 --name ${app_name} \
--link mysql5.7:db \
-e 'spring.profiles.active'=${profile_active} \
-e TZ="Asia/Shanghai" \
-v /etc/localtime:/etc/localtime \
-v /mydata/app/${app_name}/logs:/var/logs \
-d ${group_name}/${group_name}
echo '----start container----'


