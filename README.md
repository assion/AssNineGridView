# AssNineGridView
仿微信朋友圈展示图片的九宫格图片展示控件，支持点击图片全屏预览大图（可自定义）。
## 写在前面
这是一个九宫格控件，本来是很久之前就写好了，现在才开源出来，也是看了很多优秀的框架才整理出来的，希望能够帮助到大家。
该项目是参考了优秀框架：https://github.com/jeasonlzy/NineGridView 修改而成， 调整了个部分源码，使九宫格展示更为合理，喜欢原作的可以去使用。
也希望大家下载体验本项目，个人能力也有限，希望一起学习一起进步。
更多详情，可以参考我的CSDN博客地址：
* [AssNineGridView：仿QQ空间，微信朋友圈展示图片的九宫格图片展示控件](https://blog.csdn.net/weixin_40509481/article/details/82774002) 
## 演示
### 九宫格只显示1张图
![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo01.jpg)
### 九宫格只显示2张图
![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo03.jpg)
### 九宫格只显示4张图
![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo02.jpg)
### 九宫格显示9张以上
![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo04.jpg)
### 普通
![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo07.jpg)  ![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo05.jpg)  ![](https://github.com/assion/AssNineGridView/blob/master/app/src/main/res/mipmap-xxhdpi/demo06.jpg)
## 使用方法
* 方法一
* 方法二
## 项目功能
* 当获取的图片数量超过最大显示的图片数量时，最后一张图片上会显示剩余数量（类似于QQ的动态效果）
* 使用Adapter模式设置图片
* 默认增加了图片点击全屏预览效果，并附带预览动画
* 使用接口加载图片,支持任意的图片加载框架,如 Glide,ImageLoader,Fresco,xUtils3,Picasso 等
* 整合了PhotoView图片预览
* 使用接口抽出图片的加载方式，可以方便的将Glide替换成自己喜欢的ImageLoader等
* 使用代码简单，只需要几行代码
* 其他功能增加中......
## 自定义参数设置
|属性名称|参数含义|
|:---|:---|
|angv_singleImgRatio|只显示一张图片时图片宽高比|
|angv_gridSpace|网格显示图片时，图片之间的间距，默认3dp|
|angv_maxSize|最多显示图片的数量，默认最大9张|

