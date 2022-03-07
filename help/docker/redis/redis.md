docker 安装 redis 命令


安装redis
```docker
docker run --name redis6 -v /docker/redis/redis-6.2.6/conf/redis.conf:/usr/local/etc/redis/redis.conf -p 6379:6379 redis:6.2.6
```
默认密码： 123456

