# Kuberentes 相关内容介绍

Kubernetes 是Google退出的，一个基于容器(docker)的管理平台，简称k8s。

一个K8S系统，通常称为一个K8S集群(Cluster),一个集群主要包含两个部分

* 一个Master节点(主节点)，Master节点主要复制管理和控制。Master节点包括API Server、Scheduler、Controller manager、etcd。
  * API Server是整个系统的对外接口，供客户端和其他组件调用。
  * Scheduler负责对集群内部的资源进行调度。
  * Controller manager负责管理控制器。
  * etcd 用于保存集群中所有的网络配置和对象的状态信息
* 一个Node节点(计算节点)，Node节点主要复制工作负载。包括Pod，Service，Docker、kubelet、kube-proxy、Fluentd、kube-dns（可选）。
  * Pod Kubernetes最基本的操作单元。一个Pod代表着集群中运行的一个进程，它内部封装了一个或多个紧密相关的容器。
  * Service 一组Pod的集合，包含一组提供相同服务的Pod的对外访问接口。
  * Docker 用来创建容器。
  * Kubelet 负责监视指派所在Node上的Pod,包括创建、修改、监控、删除等操作
  * Kube-proxy 负责为Pod对象提供代理服务。
  * Fluentd 负责日志收集，存储与查询。