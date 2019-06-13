# 解决hzero值集无法获取的问题

## 缓存击穿

### 概念

缓存击穿指的是使用不存在的key对缓存进行大量的高并发查询，这导致缓存无法命中，每次请求都要击穿到后端数据库系统进行查询，使数据库压力过大，甚至使数据库服务被压死。

### 应对方案

将传入的大量无效key对应空值缓存起来，当再次接收到同样的查询请求时，可以命中缓存并返回空值，不会透传到数据库，避免缓存击穿。

## 问题解决

Hzero值集定义在设计的时候做了防缓存击穿机制，当开发设计的过程中出现值集已经定义，但是无法获取到值集内容的情况时，请尝试查询并删除缓存中对应的防击穿key

* hpfm:lov:lov_fail_fast:{code}:{telentId}:{lang}
* hpfm:lov:values_fail_fast:{code}:{telentId}:{lang}

redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:lov_fail_fast*" 5f562fdd65f292ef18e2c3329f3e767bac1745e8| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL
redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:lov_fail_fast*" 2d35bc89f82ee93f1d925c62dd3fab3f5410e67f| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL
redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:lov_fail_fast*" b858c1c306fdfb71ac3f7444bd4c297296bf4905| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL
redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:values_fail_fast*" 5f562fdd65f292ef18e2c3329f3e767bac1745e8| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL
redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:values_fail_fast*" 2d35bc89f82ee93f1d925c62dd3fab3f5410e67f| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL
redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD keys "hpfm:lov:values_fail_fast*" b858c1c306fdfb71ac3f7444bd4c297296bf4905| xargs -L 5000 redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD DEL

删除权限集

redis-cli -h 172.16.0.9 -p 6379 -a c4QvYJu6yixiv6HD del "hgwh:permissions:hcbm-mdata:post"
