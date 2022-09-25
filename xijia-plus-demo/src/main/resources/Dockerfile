## Jenkins 自动构建镜像脚本,勿动
# cd xijia-plus-demo/src/main/resources
# # 删除镜像
# docker rmi xijia-plus:v2.x
# # 创建镜像
# docker build -t xijia-plus:v2.x .
## 依赖环境
FROM openjdk:8-jdk-alpine
## 拷贝/复制文件
ADD xijia-plus-demo.jar /data/xijia-plus-demo.jar
## 指定容器目录挂载到 /var/lib/docker/volumes/随机名
VOLUME /data
## run容器后的执行命令
ENTRYPOINT ["java","-jar","-Xms256m","-Xmx512m","-Dspring.profiles.active=pro","/data/xijia-plus-demo.jar"]