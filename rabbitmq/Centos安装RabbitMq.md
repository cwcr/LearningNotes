# 在Centos7环境下安装RabbitMq

---
**安装环境**

本次安装基于以下环境安装
* 阿里云
* CentOS 7.3

---
**安装过程**
1、由于RabbitMq是基于Erlang开发的，所以需要先安装Erlang环境
``` sh
# Centos官方镜像尚未更新版本，Erlang版本较老，所以这里使用wget+npm方式安装Erlang环境
# 可以访问 https://packages.erlang-solutions.com/erlang/ 获取最新程序包
wget http://packages.erlang-solutions.com/site/esl/esl-erlang/FLAVOUR_1_general/esl-erlang_16.b.3-1~centos~6_amd64.rpm
#安装相关依赖
sudo yum install libGL libGLU wxBase wxGTK wxGTK-gl libiodbc unixODBC
#安装erlang
rpm -Uvh esl-erlang_16.b.3-1_centos_6_amd64.rpm
# 安装完成后执行以下命令测试
erl
```
![erlang命令行界面](.image/erlang_shell.png)

出现如上界面，则说明erlang安装成功
``` erlang
halt() . #退出
```

2、安装Elixir
由于镜像中的Elixir版本太旧，所以通过源码编译的方式安装
``` sh
# 安装git
sudo yum install git
# 克隆源码
git clone https://github.com/elixir-lang/elixir.git
# 编译
cd elixir
make clean test
# 编译完成后会看到类似如下学信息
##############################################################
## Finished in 59.4 seconds (31.4s on load, 27.9s on tests) ##
##############################################################
# 测试是否安装成功
iex
```
![iex命令行界面](.image/iex_shell.png)

出现如上界面，说明iex安装成功
```  iex
System.halt #退出
```


3、安装RabbitMq
```sh
#下载官方rpm包 https://www.rabbitmq.com/releases/rabbitmq-server/
wget https://www.rabbitmq.com/releases/rabbitmq-server/v3.6.15/rabbitmq-server-3.6.15-1.el7.noarch.rpm
# 导入asc签名
rpm --import https://www.rabbitmq.com/rabbitmq-signing-key-public.asc
# 安装依赖
sudo yum install socat
# 安装RabbitMq
yum install rabbitmq-server-3.6.1-1.noarch.rpm
# 复制配置文件，方便后续修改相关配置
cp /usr/share/doc/rabbitmq-server-3.6.15/rabbitmq.config.example  /etc/rabbitmq/rabbitmq.config # 该步可以省略，不影响启动
# 启动RabbitMq
systemctl restart rabbitmq-server
# 查看rabbitMq是否启动成功并查看服务器状态
rabbitmqctl status
# 设置rabbitMq 自启动
#######################################
### systemctl enable rabbitmq-server ##
#######################################
```

至此RabbitMq安装完成

---

**配置其他ip访问**

由于RabbitMq，提供默认用户Guest，处于安全因素考虑，Guest用户只能使用localhost访问，同时建议修改Guest用户密码。

所以，如果需要使用外部ip来访问rabbitmq,则需要创建一个新的账号，并为新账号提供远程ip访问的权限。[命令参考](./rabbitmq常用命令.md#newUser)