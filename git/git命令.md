# GIT 常用命令记录

* 分支命令
    * git checkout -b A_temp 从当前分支创建新的分支
    * git branch A_temp 如无A_temp分支，则尝试创建它
    * git checkout A_temp 切换到已有分支上
    * git merge A_temp 将指定分支合并到当前分支上
    * git cherry-pick 62ecb3 合并log_id 为 62ecb3的commit到当前分支上，忽略其他提交（适用于Hotfix）
    * git reset --hard HEAD~ 放弃本地merge,回滚到未merge的位置，**仅限于未提交的merge使用**
