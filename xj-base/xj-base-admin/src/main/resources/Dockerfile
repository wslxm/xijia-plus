## 依赖环境
FROM openjdk:8-jdk-alpine
## 项目jar包名 (bulid 构建项目时指定)
ARG JAR_NAME=""
## jvm参数 (run 启动时指定)
ENV JAVA_OPTS=""
## 拷贝/复制文件
ADD ${JAR_NAME}.jar /data/${JAR_NAME}.jar
## 指定容器目录挂载到 /var/lib/docker/volumes/随机名
VOLUME /data
## run容器后的执行命令
ENTRYPOINT java ${JAVA_OPTS} -jar /data/${JAR_NAME}.jar