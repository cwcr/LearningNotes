# hcbm-fe 前端编译问题处理

构建hcbm-fe项目时，出现如下问题 **（以下内容基于hcbm-fe-v2.1.0-RELEASE）**

``` sh
Failed to compile.

Module not found: Error: Can't resolve '@babel/runtime/helpers/esm/extends' in 'D:\hzero\cm\hcbm-fe\node_modules\history\esm'
```

---

首先检查本地代码，请尽量使用hcbm-fe项目中的tag来替代直接拉取的代码，如需直接拉取，请和项目组咨询当前最新分支。

然后检查本地镜像

``` sh
npm config get registry
# https://registry.npmjs.org/ #请尽量切换为此仓库
npm config set registry http://registry.enpmjs.org/ #镜像切换
```

接下来请按照以下步骤尝试解决此问题

## 方法1

删除项目路径下的 **node_modules** 文件夹及 **package-lock.json** , 然后尝试重新运行

```sh
npm install
npm run build
```

## 方法2

当方法1不起作用的时候请先执行

``` sh
npm cache clean
# 如清理缓存时报错 可以加上 --force 参数来执行强制清理
# npm cache clean --force
```

之后再尝试按照方法1再执行一次

## 方法3

如以上方法都不起作用时，请尝试修改package.json中的roadhog换为:’^2.5.0-beta.4’

然后删除项目路径下的**package-lock.json**

之后再尝试按照方法1再执行一次