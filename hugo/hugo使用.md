# hugo使用+hzero文档安装

>* [hugo中文站](https://www.gohugo.org/)

## Hugo

是一个用Go编写的静态网站生成器。Hugo一般只需要几秒钟就可以生成一个静态网站。

Hugo 把用户提供的数据文件，i18n包，配置，布局模板，静态文件，以及用Markdown编写的内容生成一个完整的包含多语言的静态文件

## Centos安装

hugo可以不完全依托于Go语言环境，在windows上可以直接下载一个可执行文件hugo.exe。linux上也可如此。可以进入Gtihub查看[相关版本](https://github.com/gohugoio/hugo/releases)，下方介绍使用的是0.41bane

```sh
wget https://github.com/gohugoio/hugo/releases/download/v0.41/hugo_0.41_Linux-64bit.tar.g
tar -zxvf ./hugo_0.41_Linux-64bit.tar.gz
# 此时解压出来的 hugo即是可执行文件，可以直接使用全路径运行，另外也可以将hugo注册到环境变量中，全局执行
```

## 安装Hzero文档

[hzero官方文档地址](https://code.choerodon.com.cn/hzero-hzero/hzero-website)

1、下载Hzero官方文档
2、修改config.toml文件，主要修改其中的域名指向，改为文档所在服务的域名/ip
3、执行以下编译脚本

``` sh
# gulp-cli/grunt-cli
# 具体执行内容可以参考  gulpfile.js及Gruntfile.js
gulp && grunt index
# 生成静态文件
hugo
gulp html
```

4、将静态文件托管到nginx上，之后即可访问静态网站
