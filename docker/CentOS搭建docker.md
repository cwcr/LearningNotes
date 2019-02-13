# CentOS搭建docker

[参考文档](https://yeasy.gitbooks.io/docker_practice/install/centos.html)
硬件配置
 * CentOS 7.6
 * 8G Memory
 * 

---

## 使用yum安装

准备工作,查询并卸载旧版本的docker
``` sh
#查询本机存在的docker
rpm qa|grep docker

#卸载旧版本的docker
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine
```

1、安装依赖
``` sh
yum install -y yum-utils device-mapper-persistent-data lvm2
```

2、添加docker源
``` sh
sudo yum-config-manager --add-repo https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo

# 官方源
# https://download.docker.com/linux/centos/docker-ce.repo

# 测试版本的docker
# yum-config-manager --enable docker-ce-test

# 每日构建版本的docker
# yum-config-manager --enable docker-ce-nightly

yum makecache fast
```

3、安装docker
``` sh
yum install docker-ce
```

4、启动docker并设置为自启动
``` sh
systemctl enable docker
systemctl start docker
```

5、测试docker
```sh
docker run hello-world
```

**成功**
```info
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
d1725b59e92d: Pull complete
Digest: sha256:0add3ace90ecb4adbf7777e9aacf18357296e799f81cabc9fde470971e499788
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

**失败一**
```info
Unable to find image 'hello-world:latest' locally
docker: Error response from daemon: Get https://registry-1.docker.io/v2/: net/http: request canceled while waiting for connection (Client.Timeout exceeded while awaiting headers).
See 'docker run --help'
```
出现以上信息，说明无法常速访问docker-Hub，通过配置[docker镜像加速器](https://yeasy.gitbooks.io/docker_practice/install/mirror.html)来解决这个问题

> 配置CentOS镜像
> ``` vim /etc/docker/daemon.json ```
> 添加以下内容
> ``` json
> {
>   "registry-mirrors": [
>     "https://registry.docker-cn.com"
>   ]
> }
> ```
> 也可替换为 ``` https://j0andt2p.mirror.aliyuncs.com/ ```(阿里云docker-加速)
> 请注意保持json格式，否则会出现docker无法启动的问题
> 重启docker
> ``` sh
> systemctl daemon-reload
> systemctl restart docker
> ```
> 至此镜像配置完毕，重新执行```docker run hello-world```