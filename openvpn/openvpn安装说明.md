# openvpn快速安装

## 声明

本项目来源于[github](https://github.com/Nyr/openvpn-install)，用于hzero开发环境快速搭建openvpn服务

以下安装是基于dev tun模式安装
如需安装dev tap请自行下载并安装

tun和tap的区别可参考[stachflow](https://serverfault.com/questions/21157/should-i-use-tap-or-tun-for-openvpn)

## 建议

建议直接将openvpn安装在swagger所在服务器上

如果eureka服务于swagger服务在同一服务器上为最佳情况，如不在，需将eureka所在服务器作为一个客户端接入openvpn

请确保服务器上含有apt-get/yum服务，同事确保这些服务可以访问对应的组件库下载对应组件

``` sh
sudo sh openvpn-install.sh
#填写必要信息，以完成openvpn的安装
sudo sh iptables.sh #配置IP转发
```

**注意：** 对iptables的设置可能会与现有逻辑产生冲突，执行前请检查并评估

ovpn文件可在 ``` ~/``` 路径下找到

如需移除openvpn或增加用户，可以再次执行 ```openvpn-install.sh```

## 使用

[连接使用说明](./openvpn连接远程swagger说明.md)