## 依赖环境
FROM openjdk:8-jdk-alpine
## 拷贝/复制文件
ADD xijia-plus-demo.jar /data/xijia-plus-demo.jar
## 指定容器目录挂载到 /var/lib/docker/volumes/随机名
VOLUME /data
## run容器后的执行命令
ENTRYPOINT ["java","-jar","-Xms256m","-Xmx512m","-Dspring.profiles.active=pro","/data/xijia-plus-demo.jar"]