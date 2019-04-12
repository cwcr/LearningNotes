# 自建GitLab

**注：** 以下安装基于

* 阿里云
* CentOS 7.3
* [gitlab硬件要求](https://docs.gitlab.com/ce/install/requirements.html)

---

## 安装gitlab

安装准备：

由于本次安装使用的是阿里云学生优惠机，单核/2G/40G,勉强达到硬件要求，首先调整swap分区为2G

基于[阿里云社区](https://yq.aliyun.com/articles/52098)介绍，阿里云ECS实例默认是不开启swap分区，所以需要按照以下步骤逐步创建分区。请在安装gitlab之前先开启swap分区，否则会出现阿里云访问过慢的问题，另外可能出现[gitlab-unicorn无法启动报502的问题](https://docs.gitlab.com/ee/administration/operations/unicorn.html)

``` sh
cat /proc/swaps #查看系统是否存在swap分区
mkdir /data
dd if=/dev/zero of=/data/swap bs=512 count=4194308 # 此文件的大小是count的大小乘以bs大小，上面命令的大小是4194308*512，即2GB
mkswap /data/swap #将新文件转换为swap分区
# 查看并修改swappiness,建议修改为30或60
sysctl -a|grep swappiness
sysctl -w vm.swappiness=60
# 同时修改 /etc/sysctl.conf 中的属性达到永久修改的效果
swapon /data/swap
# 至此swap分区创建结束
```

1、更新系统，修补系统漏洞，增加系统安全性

``` sh
yum update
```

2、安装http及ssh工具

``` sh
yum install -y curl policycoreutils-python openssh-server
systemctl enable sshd
systemctl start sshd
```

3、开放防火墙http权限(无firewall可跳过此步)

```sh
firewall-cmd --permanent --add-service=http
systemctl restart firewalld
```

此步开启的是默认http端口(80)端口，如更换过gitlab启动端口的，可以按照以下步骤开放端口权限

``` sh
firewall-cmd --zone=public --add-port=端口/tcp --permanent
 ```

4、安装邮件服务(postfix)如无需要，可跳过

``` sh
yum install postfix
systemctl enable postfix
systemctl start postfix
```

5、准备工作做完后就可以开始正式安装gitlab了 -> [官方教程](https://about.gitlab.com/install/#centos-7)

添加镜像

``` sh
curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.rpm.sh | sudo bash

```

执行安装

``` sh
yum install gitlab-ce #不含域名安装

# 含有域名安装
# EXTERNAL_URL="http://域名" yum install gitlab-ce 
```

_注：以上为gitlab 社区版安装方式，如需要企业版，可将其中的gitlab-ce 更换为gitlab-ee。同时需要订阅企业许可_

至此，安装就已经完成了，可以通过域名+ip的方式访问gitlab了。

第一次访问的时候，需要设置root用户的密码。设置好后请妥善保存

如密码忘记，可以访问[gutlab-security](https://docs.gitlab.com/ce/security/reset_root_password.html)按步骤修改root密码

---

## 汉化GitLab
[中文社区-gitlab-ee](https://gitlab.com/larryli/gitlab)

[中文社区-gitlab-ce](https://gitlab.com/xhang/gitlab)

1、查看gitlab版本号

``` sh
cat /opt/gitlab/embedded/service/gitlab-rails/VERSION 
```

2、gitlab-ce和gitlab-ee汉化方式相同，需要拉取对应版本号的汉化包。请按照上面地址自行拉取。

``` sh
wget https://gitlab.com/xhang/gitlab/-/archive/11-7-stable-zh/gitlab-11-7-stable-zh.tar.gz
tar -zxvf gitlab-11-7-stable-zh.tar.gz
```

2、查看汉化包的版本

``` cat gitlab-11-7-stable-zh/VERSION ```
``` cat /opt/gitlab/embedded/service/gitlab-rails/VERSION ```

二者比对，版本号应当一致，之后才能继续汉化

3、备份，防止汉化失败数据丢失
```cp -r /opt/gitlab/embedded/service/gitlab-rails/* /home/test/bak ```

4、执行汉化

``` sh
\cp -rf gitlab-11-7-stable-zh/* /opt/gitlab/embedded/service/gitlab-rails/
```

对于大部分linux系统来说 执行``` alias ```后可以看到 ```alias cp='cp -i' ```说明```cp```被```cp -i```所替代 ```\cp```与```cp```的区别在于取消了别名效果，在复制的过程中强制覆盖

5、重新配置gitlab
```gitlab-ctl reconfigure```

6、重新启动gitlab
```gitlab-ctl restart ```

至此，汉化工作到此结束。

---

## gitlab 相关命令

* gitlab-ctl reconfig 使新配置生效
* gitlab-ctl restart 启动/重启gitlab
* gitlab-ctl status 查看服务状态
* gitlab-ctl tail unicorn 查看指定服务的日志，unicorn可替换为其他服务
