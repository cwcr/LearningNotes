# vscode 实现自定义md样式

>* 参考文档[github](https://github.com/raycon/vscode-markdown-style)

## 实现首行缩进

找到vs-code安装目录，修改其中的默认css

```path
D:\Microsoft VS Code\resources\app\extensions\markdown-language-features\media\markdown.css
```

新增 css

```css
p{
    text-indent: 2em; /*首行缩进*/
}
```

以上来实现首行缩进
