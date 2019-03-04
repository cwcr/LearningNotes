#MySQL常用控制SQL

---
1、用户的创建与授权
``` SQL
 insert into mysql.user(Host,User,Password) values("localhost","sonar",password("sonar")); -- 创建用户
 -- grant 权限 on 数据库.* to 用户名@登录主机 identified by "密码";　
grant all privileges on testDB.* to test@localhost identified by '1234';
flush privileges; -- 刷新系统权限表
```