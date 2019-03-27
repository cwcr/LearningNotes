# Nginx 文件上传报错问题

## 问题描述

应用在使用Nginx做负载均衡后，原有的文件上传功能出现问题，大部分文件无法上传。

由于该现象是在负载均后出现的问题，所以猜测是由于Nginx带来的问题。

## 问题定位

为确认问题源头，按以下步骤来重现并定位问题。

* 尝试在负载均衡节点来做文件上传-上传失败
* 尝试摆脱负载均衡，单独访问其中一个节点来做文件上传-上传成功
* 基于以上两点，查看 ~nginx_path/logs/error.log 可以看到如下内容

``` log
client intended to send too large body: 9760144 bytes,
```

以上，可以确定问题出在nginx上。由于一次传输的请求太大导致nginx无法转发导致的。

此问题是由于nginx为了过滤危险请求，增加转发的效率，限定了可转发请求的大小，默认的转发请求大小为1M，所有超过规定请求大小的请求体都会被拦截

## 问题解决

可以通过调整nginx的配置文件 ~nginx_path/conf/nginx.conf 来解决此问题

```conf
# nginx 提供了 client_max_body_size 参数来定义报文大小
# 该参数有三个级别
# http{} 级控制了nginx可收到的所有请求最大报文大小（建议配置）
# server{} 控制着负载均衡中单个节点可收到的最大报文大小
# location{} 则是限制匹配某一个url时可以收到的最大报文大小
http{
    client_max_body_size 512M
    serverA{
        # client_max_body_size 200M
        locationA{
            # client_max_body_size 10M
        }
    }
}

# 另外当涉及到文件上传时，为了控制请求的大小，可以尝试开启gzip来优化对应请求
gzip on
gzip_min_length  1k
gzip_buffers  416k
gzip_http_version 1.0
gzip_comp_level 4
gzip_types  text/plain application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png
gzip_vary on
```

之后重启Nginx

``` sh
service nginx restart
```