# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD jackadvanced-0.0.1-SNAPSHOT.jar /jackadvanced.jar
# 运行过程中创建一个jackadvanced-docker.jar文件
RUN bash -c 'touch /jackadvanced.jar'
# 声明服务运行在8080端口
EXPOSE 8085
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/jackadvanced.jar"]
# 指定维护者的名字
MAINTAINER linhong





