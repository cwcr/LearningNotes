# Rabbitmq 常用命令

``` sh
rabbitmqctl add_user admin admin #新建用户
rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*" #开放用户访问权限
rabbitmqctl set_user_tags admin administrator #为用户添加角色
rabbitmqctl list_users #列出所有用户
rabbitmqctl list_permissions -p / #列出指定用户权限情况
```