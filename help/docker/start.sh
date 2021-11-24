## 停止容器
docker stop xijia
## 删除容器
docker rm xijia
## 删除镜像
docker rmi xijia:v1
## 创建镜像命令
docker build -t xijia:v1 .
## 启动命令
docker run --name xijia -v /docker/xijia:/data -p 9049:9049 -d xijia:v1
## 查看启动状态,输出实时日志
docker logs -f xijia
