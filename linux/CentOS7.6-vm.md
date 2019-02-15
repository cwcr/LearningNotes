# vm操作CentOS记录
硬件配置
 * 8G/4核/100G(可扩展)
 * CentOS7.6
 * install os minest
 * swap 8G
----
## 镜像添加
http://mirrors.aliyun.com/repo/Centos-7.repo

## 相关配置
1、安装python36,pip36
2、安装openJDK-1.8.0-191
3、安装docker

### python-pip3相关
* pip3 install request

### docker 相关
* 添加国内daemon镜像 https://registry.docker-cn.com
* docker run hello-world
* [Docker-rebbitmq](https://hub.docker.com/_/rabbitmq)
  * Database-dir: /var/lib/rabbitmq/mnesia/rabbit@my-rabbit
  * home dir       : /var/lib/rabbitmq
  * config file(s) : /etc/rabbitmq/rabbitmq.conf

### 开放端口
``` sh rabbitmq用
firewall-cmd --zone=public --add-port=5627/tcp --permanent
firewall-cmd --zone=public --add-port=15627/tcp --permanent
```