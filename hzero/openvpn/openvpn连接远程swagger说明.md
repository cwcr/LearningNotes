# 使用openvpn连接远程Swagger

## 本地软件安装

* [github地址](https://github.com/OpenVPN/openvpn)
* [最新版软件获取地址](https://openvpn.net/index.php/download/community-downloads.html)
  
请自行下载并安装系统对应版本

## 启动openvpn

联系openvpn服务器搭建者获取对应的 *.ovpn文件

windows系统将获取到的配置文件放在 ~安装路径/config/ 文件夹下
linux 系统将获取到的配置文件默认需要放在 /etc/openvpn/ 文件夹下

启动vpn(需要管理员权限，否则容易出现，权限不够无法初始化网卡的问题)

选择对应配置文件-文件名的网络进行连接。

连接完成后执行

```cmd
ping 10.8.0.1
```

可以ping通说明连接成功

## 修改本地hosts文件为以下内容

```hosts
172.16.0.9 redis.hcbm.org
172.16.0.8 db.hcbm.org
172.16.0.6 eureka.hcbm.org
172.16.0.6 gateway.hcbm.org
172.16.0.6 config.hcbm.org
172.16.0.6 register.hcbm.org
```

## 连接swagger出现的问题

* windows需关闭本地防火墙(供首次联通测试用，连通性测试通过可重新开启)
* 登陆swagger服务器，对刚刚openvpn分配到的ip做 ping通测试
* 本地修改application.yml及*DataManager.java,修改服务名防止在swagger上注册相同服务导致网关混乱
* 关闭本地除WLAN和openvpn以外其他的网络服务
  * windows下可以在 ``` 控制面板\网络和 Internet\网络连接```下关闭
  * linux下可以执行```ifdown <网卡名称>```的方式关闭
  * 需停用SS/SSR等使用Socks协议做转发的本地服务
* 本地启动服务后，进入eureka环境，找到对应的服务点击后面的连接，服务器的连接应为 http://10.8.0.xxx:{port}/info
* 如在关闭网卡后eureka依然不能注册为10.8.0.x地址，可以在本地修改application.yml文件，使用以下四种方法进行针对性调整(只使用过最后一个)
  
 ```yml
spring:
  cloud:
    inetutils:
      ignored-interfaces: # 忽略docker0网卡以及所有以veth开头的网卡
        - docker0 #docker 网卡
        - veth.* #虚拟机网卡
        - eth0 #linux本地网卡
        - WLAN* #windows下的wifi网卡
 ```

 ```yml
 spring:
  cloud:
    inetutils:
      preferred-networks: # ip前缀匹配
        - 10.8
 ```

 ```yml
 spring:
  cloud:
    inetutils:
      use-only-site-local-interfaces: true # 强制使用站点本地地址
 ```

 ```yml
 eureka:
  instance:
    prefer-ip-address: true
    ip-address: 10.8.0.x # 手动指定ip
 ```