 # PDF.js #

## [github地址](https://github.com/mozilla/pdf.js) ##

---

## 简介： ##
pdf.js是一个使用HTML5构建的可移植文档格式（PDF）的在线查看器。

pdf.js由社区驱动，并得到Mozilla Labs的支持。目的是创建一个通用的，基于Web标准的平台，用于解析和呈现PDF。

## 浏览器支持情况 ##
火狐浏览器默认支持

谷歌浏览器提供了插件支持，([谷歌商店](https://chrome.google.com/webstore/detail/pdf-viewer/oemmndcbldboiebfnladdacbdfmadadm)安装)

safari ，IE和IE Edge情况未知，如有需要可通过引用js的方式添加对相应功能的支持（尝试使用ie8，支持不佳）

## 应用 ##
1、pdf.js提供了viewer.html页面，通过传输file=pdf文件的路径的参数即可触发在线预览效果。

2、由于项目需要实现word文本变量动态替换的效果，所以需要自己实现

使用H5的canvas特性来实现的pdf在线预览效果，每一个canvas加载一页pdf。直至pdf所有页加载结束。

文件的接收则使用Base64字符串的格式。最主要的一点是由于部分浏览器做response做了监控，会默认劫持含有文件流的response，然后对文件流进行解析并输出，所以使用Base64字符串能解决文件在线预览的效果而不触发下载。方便对pdf文本域做进一步的解析。

对转换的pdf文件进行解析，获取其中文本匹配为 `{}` 的内容，将其转换为下划线 `___`,同时对转换的下划线做高亮处理。

