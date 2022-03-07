docker 安装 redis 命令

安装redis
```docker
## 不持久化
docker run --name redis6  -p 6379:6379 -d redis:6.2.6

## 不持久化
docker run --name redis6 -v /docker/redis/redis-6.2.6/conf/redis.conf:/usr/local/etc/redis/redis.conf -p 6379:6379 -d redis:6.2.6 redis-server /usr/local/etc/redis/redis.conf

## 持久化
docker run --name redis6 -v /docker/redis/redis-6.2.6/conf/redis.conf:/usr/local/etc/redis/redis.conf -p 6379:6379 -d redis:6.2.6 redis-server /usr/local/etc/redis/redis.conf --appendonly yes 

```
默认密码： 123456

