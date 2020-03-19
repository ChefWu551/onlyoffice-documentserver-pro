# onlyoffice documentserver 中文优化
> ### 逐渐更新中。。。
## 优化点
> #### onlyoffice 界面的汉化
> #### onlyoffice 字体及重命名
> #### onlyoffice 中文字号编辑
> #### onlyoffice spell checker 去除
## 结果图展示
![Image text](resources/image/效果图.png)
## 正文
### 1. 用法介绍
#### 1.1. 快速体验
注意：目前提供打包好的镜像只是个效果镜像，不提供真正实际使用场景

`docker pull registry.cn-shanghai.aliyuncs.com/wuyuefeng/onlyoffice-documentserver-pro:5.4.2`

`docker run -p 80:80 -p 443:443 -itd --name registry.cn-shanghai.aliyuncs.com/wuyuefeng/onlyoffice-documentserver-pro:5.4.2 onlyoffice-documentserver-pro`

#### 1.2. 手动修改
自己build镜像，修改字体、字号、bug、配置文件，目前提供的web-apps是修改过的文件，已经增加了字号，若还想自己编辑这些功能，请参考链接：https://www.douban.com/note/754817950/
+ 关于字体，提供的winfont的字体是修改名称后的字体
+ 关于字号，提供的默认字号是修改过的默认字号
+ bug主要是自己中文翻译的问题，已经处理
+ 配置文件，根据自己需求修改的，也参考官网提供的资料

## 参考资料
> 1. 官方：https://github.com/ONLYOFFICE/DocumentServer
> 2. 官方demo: https://api.onlyoffice.com
> 3. https://github.com/hqzhen/DemoOfOnlyoffice 
