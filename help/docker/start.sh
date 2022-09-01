## 停止容器
docker stop xijia-plus
## 删除容器
docker rm xijia-plus
## 删除镜像
docker rmi xijia-plus:v2.x
## 创建镜像命令
docker build -t xijia-plus:v2.x .
## 启动命令 (挂载时区 和 目录)
docker run --name xijia-plus -v /etc/localtime:/etc/localtime -v /docker/spring-boot-plus2/server:/data -p 9049:9049 -d -it xijia-plus:v2.x /bin/bash
## 查看启动状态,输出实时日志
docker logs -f xijia-plus