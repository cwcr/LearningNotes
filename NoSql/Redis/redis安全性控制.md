# 建议的Redis安全配置
以下所有配置都通过修改redis.conf 添加

## Redis添加密码
``` conf
requirepass zP0GHiGo563Y2mwg7YxksE6I7w31Dshl
```
之后重启Redis

由于Redis查询速度非常快，外部用户在访问Redis的时候可以在1秒中尝试超过150k个密码；所以密码长度要设置的尽量长

## Redis危险命令控制
修改redis.conf 
``` conf
# 禁用flushall命令 如需要AOF备份，该命令不能禁用
rename-command FLUSHALL ""
# 禁用flushdb命令
rename-command FLUSHDB ""
# 禁用keys命令
rename-command KEYS ""
```

## Redis安全IP访问控制
指定IP访问
``` conf
# 所有ip
# bind 0.0.0.0 

# 指定IP可以访问
# 当访问地址不含127.0.0.1的时候 redis-cli 不能使用
# bind 127.0.0.1 192.160.0.1
bind 127.0.0.1
```