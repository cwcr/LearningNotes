# ELK+FileBeat使用docker安装记录

## docker安装记录

- 以下均使用docker完成安装，使用sebp/elk 镜像
- [官方镜像地址](https://hub.docker.com/r/sebp/elk/)
- [官方文档地址](https://elk-docker.readthedocs.io/)
- [github地址](https://github.com/spujadas/elk-docker)
- 本次安装时对应的elk版本为683

## 1.1. ELK

### 1.1.1. 介绍

 >- [ELK官方网站](https://www.elastic.co/cn/what-is/elk-stack)
 >- ELK 是Elastic公司提供的一套完整的日志收集以及展示的解决方案，是三个产品的首字母缩写，分别是ElasticSearch、Logstash 和 Kibana
 >- Elasticsearch是实时全文搜索和分析引擎，提供搜集、分析、存储数据三大功能
 >- Logstash是一个用来搜集、分析、过滤日志的工具
 >- Kibana是一个基于Web的图形界面，用于搜索、分析和可视化存储在 Elasticsearch指标中的日志数据

![ELK](.image/ELK.png)

- Logstash从各个Docker容器中提取日志信息
- Logstash将日志转发到ElasticSearch进行索引和保存
- Kibana负责分析和可视化日志信息

由于Logstash在数据收集上并不出色，而且作为Agent，其性能并不达标。基于此，Elastic发布了beats系列轻量级采集组件。

这里我们要实践的Beat组件是Filebeat，Filebeat是构建于beats之上的，应用于日志收集场景的实现，用来替代 Logstash Forwarder 的下一代 Logstash 收集器，是为了更快速稳定轻量低耗地进行收集工作，它可以很方便地与 Logstash 还有直接与 Elasticsearch 进行对接。

### 1.1.2. 安装

本此安装使用 [sebp/elk镜像](https://elk-docker.readthedocs.io/)

### 1.1.3. 硬件要求

- 内存 4G，其中Elasticsearch的最小启动内存即为2G
- mmap计数的限制等于262,144或更多 <span id="mmap" />

### 1.1.4. 安装过程

```sh
sysctl vm.max_map_count # 查看mmap计数器大小
sysctl vm.max_map_count=262144 # 若计数器小于 262144 则执行这句，调整计数器大小
```

```sh
docker pull sebp/elk # 拉取 sebp/elk 镜像
docker run -it -d --name elk-test -p 5601:5601 -p 9200:9200 -p 5044:5044 sebp/elk # 运行镜像
```

```sh
# 如果服务硬件配置是在无法达到4G需求时，可以增加参数来限制ElasticSearch启动时占用的内存
docker run -it -d --name elk-test -p 5601:5601 -p 9200:9200 -p 5044:5044 -e ES_MIN_MEM=512m -e ES_MAX_MEM=1024m sebp/elk
```

### 1.1.5. 安装完成

尝试访问 ip:5601 即可看到Elasticsearch界面

### 1.1.6. 额外支持启动变量 <span id="extend-en-var"/>

可以使用以下环境变量来覆盖用于启动服务的默认值：

>- TZ：容器的时区（请参阅有效时区列表），例如Asia/Shanghai（默认值为Etc/UTC，即UTC）。
>- ES_HEAP_SIZE：Elasticsearch堆大小（默认为最小256MB，最大1G） 指定堆大小（例如2g）会将最小值和最大值都设置为提供的值。要分别设置最小值和最大值，请使用ES_JAVA_OPTS
>- ES_JAVA_OPTS：对于Elasticsearch其他Java选项（默认值：""）例如，要将最小和最大堆大小设置为512MB和2G，请将此环境变量设置为-Xms512m -Xmx2g。
>- ES_CONNECT_RETRY：等待秒数为Elasticsearch开始Logstash和/或Kibana前将上升（默认值：30）
>- ES_PROTOCOL：协议使用ping通Elasticsearch的JSON接口URL（默认值：http）请注意，此变量仅用于在启动服务时测试Elasticsearch是否已启动。它不用于更新Logstash和Kibana的配置文件中的Elasticsearch的URL。
>- CLUSTER_NAME：Elasticsearch集群的名称（默认值：如果Elasticsearch不需要用户身份验证，则在容器启动时自动解析）。Elasticsearch集群的名称用于设置容器在运行时显示的Elasticsearch日志文件的名称。默认情况下，集群名称是在启动时CLUSTER_NAME通过匿名查询Elasticsearch的REST API 自动解析的（并填充）。但是，当Elasticsearch需要用户身份验证时（例如，在运行X-Pack时的默认情况下），该查询将失败并且容器将停止，因为它假定Elasticsearch无法正常运行。因此，CLUSTER_NAME环境变量可用于指定群集的名称并绕过（失败的）自动解析。
>- LS_HEAP_SIZE：Logstash堆大小（默认值："500m"）
>- LS_OPTS：Logstash选项（默认值："--auto-reload"在带有标签es231_l231_k450和的图像中es232_l232_k450，""位于latest；请参见重大更改）
>- NODE_OPTIONS：节点选项Kibana（默认："--max-old-space-size=250"）
>- MAX_MAP_COUNT：mmap计数限制（默认值：系统默认值） –此设置取决于系统：并非所有系统都允许在容器内设置此限制，您可能需要在启动容器之前从主机进行设置（[mmap条件](#mmap)）。
>- MAX_OPEN_FILES：最大打开文件数（默认：系统默认值； Elasticsearch需要该数量至少等于65536）
>- KIBANA_CONNECT_RETRY：等待的秒数Kibana运行后挂机脚本之前（见前钩和后钩）（默认值：30）
>- ES_HEAP_DISABLE和LS_HEAP_DISABLE：HeapDumpOnOutOfMemoryError如果非零，则分别对Elasticsearch和Logstash 禁用（默认值：HeapDumpOnOutOfMemoryError启用）。

### 1.1.7. 常见报错

1、访问html页面的时候，看到

```html
Kibana server is not ready yet
```

查看日志看到

```log
{"type":"log","@timestamp":"2019-12-30T10:52:33Z","tags":["warning","elasticsearch","data"],"pid":309,"message":"Unable to revive connection: http://localhost:9200/"}
{"type":"log","@timestamp":"2019-12-30T10:52:33Z","tags":["license","warning","xpack"],"pid":309,"message":"License information from the X-Pack plugin could not be obtained from Elasticsearch for the [data] cluster. Error: No Living connections"}
{"type":"log","@timestamp":"2019-12-30T10:52:33Z","tags":["warning","elasticsearch","data"],"pid":309,"message":"No living connections"}
```

尝试 ``` curl ip:9200 ``` 看端口是否是通的，同时检查防火墙是否有对9200端口做限制

如果9200端口没有问题，那很有可能是 docker容器中，Kibana服务启动失败了，执行
```docker exec -it elk-test ps -ef|grep kibana``` 查看docker进程，应当可以看到如下输出，如没有，可以尝试更新镜像重新执行。

``` sh
kibana     308     1  0  2019 ?        00:13:53 /opt/kibana/bin/../node/bin/node
```

如上述方案均不能处理此问题，则大概率是Kibana启动时，内存不足导致的，可以通过类似以下的启动脚本，来限制启动Elasticsearch和Logstash的堆内存大小，具体参数含义查看[额外支持启动变量](#extend-en-var)

```sh
docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it \
    -e ES_HEAP_SIZE="1g" -e LS_HEAP_SIZE="1g" --name elk sebp/elk
```

2、mmap不达标

```sh
max virtual memory areas vm.max_map_count [65530] likely too low, increase to at least [262144]
```

如查看docker日志，发现上述提示，则说明主机的mmap不足以启动Elasticsearch，这是版本5后的比较重大的更新。需要调整mmap才能触发启动。

## Filebeat

### 介绍

Beats 是安装在服务器上的数据中转代理。可以将数据直接传输到 Elasticsearch 或传输到 Logstash 中。

常用的类型有：

>- Packetbeat：网络数据包分析器，提供有关您的应用程序服务器之间交换的事务的信息。
>- Filebeat：从您的服务器发送日志文件。
>- Metricbeat：是一个服务器监视代理程序，它定期从服务器上运行的操作系统和服务收集指标。
>- Winlogbeat：提供Windows事件日志。

### 安装

创建一个```fileBeat.yml```配置文件,用来指定输入和输出

```yml
output:
  logstash:
    enabled: true # 使用logstash作为输出
    hosts:
      - elk-ip:5044 # elk-ip及端口
    timeout: 15 # 超时时间
    ssl:
      certificate_authorities:
          - /etc/pki/tls/certs/logstash-beats.crt # sebp/elk 默认开启了ssl证书身份认证，参考下文中 ssl证书部分

filebeat:
  inputs:
    -
      paths: # 获取输入日志的路径
        - /var/log/syslog
        - /var/log/auth.log
      document_type: syslog # 指定日志的类型
```

- ssl证书
  - 可以从[ELK的源代码库](https://github.com/spujadas/elk-docker)中获取，复制logstash-beats.crt文件（其中包含证书颁发机构的证书-或服务器证书）
  - 用于限制对ELK服务的访问仅限于授权的主机/网络
