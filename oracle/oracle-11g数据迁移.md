# Oracle数据库迁移方案

本迁移方案是基于Oracle的dblink特性进行的，请确保可以在两数据库之间创建dblink

---

## 预先说明

### 适用版本

以下内容在Oracle 11g中经过验证

### 指代说明

原数据库：现有数据库 (以下用OLD_ORACLE指代)

新数据库：准备迁移的目标数据库

## 迁移前准备

### 预先准备工作

需要建立原数据库与新数据库之间的dblink，新数据库可以通过dblink访问到原数据库并拥有SELECT ALL的权限

### 迁移脚本准备

迁移脚本应该包含以下三个内容。

执行以下SQL时，需依照实际情况替换Schema和dblink

#### 表迁移脚本

在原数据库(OLD_ORACLE)中执行

SELECT 'CREATE TABLE '||TABLE_NAME||' AS SELECT * FROM '|| TABLE_NAME ||'@dblink;' FROM DBA_TABLES where UPPER(OWNER) = UPPER('Schema');

以上sql执行后得到的结果保留，作为接下来的表迁移脚本

#### 序列迁移脚本

在原数据中执行

SELECT  'CREATE SEQUENCE ' ||SEQUENCE_NAME||' MINVALUE ' ||MIN_VALUE||' MAXVALUE ' ||MAX_VALUE||' START WITH ' ||LAST_NUMBER||' INCREMENT BY ' ||INCREMENT_BY||(CASE WHEN CACHE_SIZE= 0 THEN  ' NOCACHE'   ELSE   ' CACHE ' ||CACHE_SIZE END) || ';' FROM DBA_SEQUENCES WHERE UPPER(SEQUENCE_OWNER)= UPPER('Schema')  ;

以上sql执行后得到的结果复制出来，作为接下来的序列迁移脚本

#### 其他迁移脚本

主要为视图和存储过程，如果原数据库中包含这两部分内容，请将其摘取出来，按照需要调整创建SQL(主要修改可能存在的dblink和Schema)

### 验证脚本准备

SELECT T.TABLE_NAME,T.NUM_ROWS FROM USER_TABLES T;

## 开始迁移工作

### 迁移准备工作

迁移开始之前应该停用原数据库所对应的应用，确保没有新数据会进入原数据库后再开始数据迁移。

### 数据迁移

在新数据中运行2.2步准备的迁移脚本，完成数据迁移工作

### 数据验证

在原数据库及新数据库中分别执行2.3步中的验证脚本,导出两份Excel做Vlookup查看两数据库中条数的差异。

*若Excel表中条数无差异，则数据迁移工作完成。*

## 迁移后其他工作

* 原数据库及对应应用应继续停用并保持现场，方便以后出问题可及时恢复
* 启用新数据库及应用，及时进行验证
* 通知与原数据库对接的各个系统，完成相应的迁移工作
