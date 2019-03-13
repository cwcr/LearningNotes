# 在离线环境下更新glibc由于版本不同导致的问题

* 由于glibc-2.17和glibc2.27不兼容，强制npm安装后导致ls/npm等命令失效，所幸cd/sln命令还可以使用。
``` sh
sln /usr/lib64/libdl-2.17.so /usr/lib64/libdl.so.2 
sln /usr/lib64/ld-2.17.so /usr/lib64/ld-linux-x86-64.so.2 
sln /usr/lib64/libc-2.17.so /lib64/libc.so.6
sln /usr/lib64/libm-2.17.so /lib64/libm.so.6
```
映射以上文件后，将批命令关联glibc-2.17的so文件后，命令恢复。
注：在做了软映射后，谨慎升级glibc,否则容易再次出现此问题。
