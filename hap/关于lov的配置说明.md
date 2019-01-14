Lov是一个利用弹出框来提供数据的组件。
类似这样的
![提供数据](http://upload-images.jianshu.io/upload_images/3735242-5fcbd9e102646553.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

关于lov的配置涉及到两张表分别是sys_lov和sys_lov_item,其中sys_lov是对lov的头的配置
![lov头配置](http://upload-images.jianshu.io/upload_images/3735242-63c8ea286e13022e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Sys_lov_item是关于lov行的配置
![lov行配置l](http://upload-images.jianshu.io/upload_images/3735242-202e7d0e0560cb79.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Lov界面配置说明
关于lov头配置说明
![lov头配置说明](http://upload-images.jianshu.io/upload_images/3735242-bceae3d6440ce74e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

关于lov行配置说明
![lov行配置说明](http://upload-images.jianshu.io/upload_images/3735242-90acb479f45db363.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Lov查询字段说明
lov调用lovProvider接口中的getlov（）方法用于生成lov模板，此时需要三个参数
code:lov配置的唯一编码，根据这个参数生成唯一的lov模板
contextPath:项目的根路径
locale:就是语言环境
其中真正需要传入的是code，类似下面的user_lov,LOV_ROLE,LOV_RESOURCE,这几个code分别对应查询用户，查询角色，查询资源。
![lov参数code](http://upload-images.jianshu.io/upload_images/3735242-ad2515b8172836f0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

关于lov的动态查询参数
原始的lov传参的方式有：
方式一：
![lov传参](http://upload-images.jianshu.io/upload_images/3735242-9aa8a7432885a5e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

方式二：
![lov传参](http://upload-images.jianshu.io/upload_images/3735242-e5fd00add4f675f2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其本质都是传递了需要的那三个参数。
动态查询参数就是我们可以动态传递参数，
在lov的组件源码中我们定义了两个事件
分别是selsect和query事件。
![定义事件](http://upload-images.jianshu.io/upload_images/3735242-54eff16b20b42f80.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其中query事件是在进行参数格式转换的时候触发，参数就是数据转换的结果map
![触发query](http://upload-images.jianshu.io/upload_images/3735242-0628db3927fdb65d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们可以看到grid查询数据时原始数据是viewmodel中的数据和pagesize,page
![](http://upload-images.jianshu.io/upload_images/3735242-5188f8839cdee007.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后在kendlov中添加query事件
![添加query事件](http://upload-images.jianshu.io/upload_images/3735242-19a1bea6eefcac50.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

query事件没有触发之前e.param的值为：
![](http://upload-images.jianshu.io/upload_images/3735242-82fb43e5e4c0275c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Query事件触发之后e.param的值为：
![](http://upload-images.jianshu.io/upload_images/3735242-ac41baa8834dfc83.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这样我们就动态地增加了crrent_time和userId两个参数。
关于select事件是在我们在数据grid中双击的时候触发的
![](http://upload-images.jianshu.io/upload_images/3735242-d44b88ebd4409024.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

然后在kendlov中添加selec事件
![](http://upload-images.jianshu.io/upload_images/3735242-75b3842042b11ed1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Selec事件没有触发之前viemodel.data的值为
![](http://upload-images.jianshu.io/upload_images/3735242-6eec87ecf4fb55a7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Seletc事件触发之后
![](http://upload-images.jianshu.io/upload_images/3735242-1085f0abd08649cc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以看到我们动态地改变了viewmodel的内的值
**联动lov(****动态lov)**
联动lov就是lov提供的数据会根据传入的参数不同而改变，在例子中表现为
![](http://upload-images.jianshu.io/upload_images/3735242-1042329f013df8e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

动态lov输入框的数据源会根据动态lov下拉框的值得改变而改变
例如：
下拉框的值为user_lov，则动态lov的输入框数据就是用户列表
![](http://upload-images.jianshu.io/upload_images/3735242-74ebcb0ddee17bf9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

当下拉框的值为LOV_ROLE，则动态lov输入框的数据就是用户角色列表
![](http://upload-images.jianshu.io/upload_images/3735242-0f36745c576b6fed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在form中的实现原理：
在DropDownList中添加change事件，将下拉框的文本域和值域设为code并绑定在viewmodel上，当下拉框的值改变的时候，可以动态获取code值并作为kendolov的查询参数。
![](http://upload-images.jianshu.io/upload_images/3735242-51968504be07511d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这样lov在form中就实现了联动
Lov在grid中实现联动
![](http://upload-images.jianshu.io/upload_images/3735242-4bf8fdc27ce700aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实现原理：
![](http://upload-images.jianshu.io/upload_images/3735242-761c99ac8c2b8612.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3735242-76fd2a5f295acc5e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3735242-1c46720a22931bb0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

本质上都传递的code参数，只是在grid中使用了model,下拉框将当前的值赋给lovcode，而输入框将model内的lovcode覆盖当前的lovcode值，从而保证两个值得一致性，达到了lov的动态性。
