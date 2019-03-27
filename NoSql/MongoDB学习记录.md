# MongoDB学习使用记录(Python)

## MongoDB与结构性数据库概念比对

<!--
<table style="text-align: center;">
    <tr>
        <th>SQL概念</th><th>MongoDB概念</th><th>解释说明</th>
    </tr>
    <tr>
        <td>database</td><td>database</td><td>数据库</td>
    </tr>
    <tr>
        <td>table</td><td>collection</td><td>数据库表/集合</td>
    </tr>
    <tr>
        <td>row</td><td>document</td><td>数据库行记录/文档</td>
    </tr>
    <tr>
        <td>column</td><td>field</td><td>字段/域</td>
    </tr>
    <tr>
        <td>index</td><td>index</td><td>索引</td>
    </tr>
    <tr>
        <td>table joins</td><td>-</td><td>MongoDB不支持表连接</td>
    </tr>
    <tr>
        <td>primary key</td><td>primary key</td><td>主键。结构性数据库需要指定；MongoDB默认将 '_id' 设为主键</td>
    </tr>
</table>
-->

| SQL概念 | MongoDB概念| 解释说明 |
|---|---|---|---|
| database | database | 数据库 |
| table | collection | 数据库表/集合 |
| row | document | 数据库行记录/文档 |
| column | field | 字段/域 |
| index | index索引 |  |
| table | joins- | MongoDB不支持表连接 |
| primary key | primary key	主键。结构性数据库需要指定； | MongoDB默认将 '_id' 设为主键 |

## Python操作MongoDB数据库

* `pip install pymongo` 安装pymongo组件
* 创建数据库连接

``` py
    from pymongo import MongoClient￼

    # 基于IP+端口获取连接
    client = MongoClient('localhost',27017)￼
    # 获取名为 blog_database 的数据库,如数据库不存在则创建一个
    db = client.blog_database￼
    # 获取对应数据库下的名为 blog 的collection,如不存在则创建一个
    collection = db.blog
