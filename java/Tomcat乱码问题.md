# Tomcat输出日志乱码问题

tomcat升级版本后，出现日志乱码问题

参照[Tomcat8.5升级日志](https://tomcat.apache.org/tomcat-8.5-doc/changelog.html)可以看到如下两点

1、为兼容jdk9，Tomcat 8.5.23升级过程中增加了logging.properties文件，将Catalina输入配置托管在该配置文件中

2、Tomcat 8.5.36升级过程中，为了解决[62788](https://bz.apache.org/bugzilla/show_bug.cgi?id=62788)问题，Tomcat修改了默认字符集为UTF-8

由于Windows系统默认使用的是GBK字符集，所以Tomcat 8.5.36及以上版本在Windows下日志输出就会就会出现乱码问题

### 解决方法：

修改 ~tomcat/conf/logging.properties 文件，替换/增加
```` properties
java.util.logging.ConsoleHandler.level = FINE
java.util.logging.ConsoleHandler.formatter = org.apache.juli.OneLineFormatter
java.util.logging.ConsoleHandler.encoding = GBK
````

将日志输出时使用的字符集调整为GBK，之后重启Tomcat，问题解决！

**同理Tomcat 9.0版本出现日志乱码问题也可以使用如上方法解决**
