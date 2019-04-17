# Docker常用命令

```shell
docker info # 查看docker信息
docker ps # 查看docker启动信息
docker images # 查看docker已加载镜像
docker image ls # 等同于docker images
docker image rm <image-name> # 移除镜像
docker image rmi <image-id> #根据image的id来移除镜像

# 使用镜像保存文件
docker save imageID > filename # 保存镜像到指定文件中
docker load < filename # 加载镜像文件

# 使用容器保存文件
docker export containID > filename # 保存容器到文件中
docker import filename [newname] # 加载容器文件
# 使用容器保存的文件不包含操作历史，文件相对较小

docker search imageNAME # 查询镜像
docker pull imageNAME:imageTAG # 拉取镜像
docker run -d -p imageNAME:imageTAG/imageID # 运行镜像

docker exec -it containID /bin/bash # 进入一个正在运行中的容器中进行操作
```