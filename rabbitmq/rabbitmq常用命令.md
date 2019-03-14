# Rabbitmq 常用命令

### 新建用户
<span id="newUser"></span>
``` sh
rabbitmqctl add_user admin admin #新建用户
rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*" #开放用户访问权限
rabbitmqctl set_user_tags admin administrator #为用户添加角色
#用户角色包含
#################################################
## 1、administrator 超级管理员
## 2、monitoring 监控者
## 3、policymaker 策略制定者
## 4、management 普通管理者
## 5、其他 无法登陆管理控制台，生产者和消费者
#################################################
rabbitmqctl list_users #列出所有用户
rabbitmqctl list_permissions -p / #列出指定用户权限情况
```