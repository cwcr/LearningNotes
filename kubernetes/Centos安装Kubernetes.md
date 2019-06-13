# Centos安装Kubernetes

---

## 参考文档

[官方文档](https://kubernetes.io/zh/docs/setup/independent/install-kubeadm/)

[猪齿鱼参考](http://choerodon.io/zh/docs/installation-configuration/steps/kubernetes/)

## 系统版本及配置

* Centos7.6
* 4核8G

### 准备工作

* 一台或多台运行着下列系统的机器:
  * Ubuntu 16.04+
  * Debian 9
  * CentOS 7
  * RHEL 7
  * Fedora 25/26 (尽力服务)
  * HypriotOS v1.0.1+
  * Container Linux (针对1800.6.0 版本测试)
* 每台机器 2 GB 或更多的 RAM (如果少于这个数字将会影响您应用的运行内存)
* 2 CPU 核心或更多
* 集群中的所有机器的网络彼此均能相互连接(公网和内网都可以)
* [节点之中不可以有重复的主机名，MAC 地址，product_uuid。](#only_mac)
* 开启主机上的相关端口，[默认端口需求](#port_check)。
* 为了保证 kubelet 正确运行，需要禁用 Swap 交换分区。

#### 确保每个节点上 MAC 地址和 product_uuid 的唯一性。<span id="only_mac"></span>

* 网络接口的 MAC 地址可以使用下列命令获取：ip link 或是 ifconfig -a
* 下列命令可以用来获取 product_uuid sudo cat /sys/class/dmi/id/product_uuid
  
一般来讲，硬件设备会拥有独一无二的地址，但是有些虚拟机可能会雷同。Kubernetes 使用这些值来唯一确定集群中的节点。如果这些值在集群中不唯一，可能会导致安装

#### 端口检查 <span id="port_check"></span>

默认端口需求及功能如下

##### Master 节点

| 规则 | 方向 | 端口 | 作用 | 使用者 |
| --- | --- | --- | --- | ---|
| TCP | Inbound | 6443 | Kubernetes API server | All |
| TCP | Inbound | 2379-2380 | etcd server client API | kube-apiserver, etcd |
| TCP | Inbound | 10250 | Kubelet API | Self, Control plane |
| TCP | Inbound | 10251 | kube-scheduler | Self |
| TCP | Inbound | 10252 | kube-controller-manage | Self |

##### worker 节点

| 规则 | 方向 | 端口 | 作用 | 使用者 |
| --- | --- | --- | --- | ---|
| TCP | Inbound | 10250 | Kubelet API | Self, Control plane |
| TCP | Inbound | 30000-32767 | NodePort Services** | All |
