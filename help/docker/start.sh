## 停止容器
docker stop spring-boot-plus2
## 删除容器
docker rm spring-boot-plus2
## 删除镜像
docker rmi spring-boot-plus2:v2.x
## 创建镜像命令
docker build -t spring-boot-plus2:v2.x .
## 启动命令 (挂载时区 和 目录)
docker run --name spring-boot-plus2 -v /etc/localtime:/etc/localtime -v /docker/spring-boot-plus2/server:/data -p 9049:9049 -d -it spring-boot-plus2:v2.x /bin/bash
## 查看启动状态,输出实时日志
docker logs -f spring-boot-plus2