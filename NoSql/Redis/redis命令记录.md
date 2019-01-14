# Redis命令总结

----

### [Redis命令参考-中文网](http://redisdoc.com/) ###

## 一、redis基础命令
1、进入命令行 <br/>
windows: cmd->redis-cli <br/>
linux: 进入redis/bin目录执行 `./redis-cli` <br/>
2、退出命令行<br/>
`exit`<br/>
`quit`<br/>
以上两个命令效果相同<br/>
3、关闭redis进程<br>
`shutdown`<br/>
4、启动redis服务<br/>
window: 1). 执行redis目录下的redis-server.exe;2)win+R->services.msc->找到redis服务->执行启动命令<br/>
linux: 进入redis/bin目录执行 `./redis-server`<br/>

## 二、redis常用命令
* `ping message` 测试连接的连通性,返回message
* `echo message` 打印消息,返回message
* `info` 打印当前数据库的配置信息
* `select dbnum` 选择数据库： redis提供了15个库，dbnum可以输入0~15的数字
* `flushdb` 清空当前db
* `flushall` 清空所有db
* `dbsize` 返回数据库使用空间大小
* `keys *` 取出当前db下所有的key集合，另外*的位置可以换成字符串匹配
* `exists [key...]` 判断当前key是否存在，返回0/1
* `del key`删除当前可用key及对应数据
* `expire key interval`给key设置TTL属性(过期时间)
* `persist key`移除key的TTL属性
* `move key dbnum`移动key对应的键值对到指定num的数据库 ERROR/0/1
* `rename key newkey`重命名key的名称为newkey
* `randomkey` 随机返回一个key
* `type key`返回key对应值的数据类型(Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)；另外有可能返回none)
* `monitor` 实时监听转储请求信息
---
## 三、核心命令
### `SET key value [EX seconds] [PX milliseconds] [NX|XX]`
将值 value 关联到 key 。注:（value的类型会识别为字符串类型）<br/>
如果 key 已经持有其他值， SET 就覆写旧值，无视类型。<br/>
对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。<br/>
###### 可选参数
* EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 `SETEX key second value` 。
* PX millisecond ：设置键的过期时间为 millisecond 毫秒。 `SET key value PX millisecond` 效果等同于 `PSETEX key millisecond value` 。
* NX ：只在键不存在时，才对键进行设置操作。 `SET key value NX` 效果等同于 `SETNX key value` 。
* XX ：只在键已经存在时，才对键进行设置操作。