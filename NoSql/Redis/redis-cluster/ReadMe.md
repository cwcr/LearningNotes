# Docker 搭建Redis集群

## 启动Redis

本脚本会启动6个redis，3主3从构成cluster集群
安装前请确保服务器上安装了docker,docker-compose工具

``` sh
sh start.sh
```

## 构建集群

这里同样使用了另一个镜像inem0o/redis-trib，注意修改ip为本机内网ip

``` sh
docker run --rm -it inem0o/redis-trib create --replicas 1 127.0.0.1:8001 127.0.0.1:8002 127.0.0.1:8003 127.0.0.1:8004 127.0.0.1:8005 127.0.0.1:8006
```

至此 Redis-cluster集群构建完毕
使用redis-cli 链接任意一个节点,执行 cluster info 可以看到一下结果，说明集群构建成功

``` sh
127.0.0.1:8003> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:3
cluster_stats_messages_ping_sent:39
cluster_stats_messages_pong_sent:37
cluster_stats_messages_meet_sent:4
cluster_stats_messages_sent:80
cluster_stats_messages_ping_received:34
cluster_stats_messages_pong_received:43
cluster_stats_messages_meet_received:3
cluster_stats_messages_received:80
```

## 设置密码

挨个节点设置密码

``` sh
redis-cli -h 127.0.0.1 -p 8001 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8001 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8001 -a `password` config rewrite

redis-cli -h 127.0.0.1 -p 8002 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8002 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8002 -a `password` config rewrite

redis-cli -h 127.0.0.1 -p 8003 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8003 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8003 -a `password` config rewrite

redis-cli -h 127.0.0.1 -p 8004 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8004 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8004 -a `password` config rewrite

redis-cli -h 127.0.0.1 -p 8005 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8005 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8005 -a `password` config rewrite

redis-cli -h 127.0.0.1 -p 8006 config set masterauth `password`
redis-cli -h 127.0.0.1 -p 8006 config set requirepass `password`
redis-cli -h 127.0.0.1 -p 8006 -a `password` config rewrite
```
