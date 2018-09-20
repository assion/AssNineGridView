# AssNineGridView [![](https://jitpack.io/v/assion/AssNineGridView.svg)](https://jitpack.io/#assion/AssNineGridView)
仿微信朋友圈展示图片的九宫格图片展示控件，支持点击图片全屏预览大图（可自定义）。
## 写在前面
这是一个九宫格控件，本来是很久之前就写好了，现在才开源出来，也是看了很多优秀的框架才整理出来的，希望能够帮助到大家。
该项目是参考了优秀框架：https://github.com/jeasonlzy/NineGridView 修改而成， 调整了个部分源码，使九宫格展示更为合理，喜欢原作的可以去使用。
也希望大家下载体验本项目，个人能力也有限，希望一起学习一起进步。
更多详情，可以参考我的CSDN博客地址：
* [AssNineGridView：仿QQ空间，微信朋友圈展示图片的九宫格图片展示控件](https://blog.csdn.net/weixin_40509481/article/details/82774002) 
## 效果图
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
## 使用方法
### 1 导入
* 方法一

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency
```
	dependencies {
	        implementation 'com.github.assion:AssNineGridView:v1.0.1'
	}

```
* 方法二

下载本项目，将项目中的lib直接导入即可
```
implementation project(':lib_assninegridview')
```
### 2 初始化
* 在Application中初始化AssNineGridView的图片加载器
```
 AssNineGridView.setImageLoader(new GlideImageLoader());
```
* 自定义自己的图片加载器，支持任意的图片加载框架,如 Glide,ImageLoader,Fresco,Picasso 等
```
public class GlideImageLoader implements AssNineGridView.ImageLoader{

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
```
### 3 功能实现
* xml添加
```
 <com.assionhonty.lib.assninegridview.AssNineGridView
            android:id="@+id/angv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginBottom="8dp"
            app:angv_gridSpace="3dp"
            app:angv_maxSize="9"
            app:angv_singleImgRatio="1" />
```
* java代码（demo中用的recyclerview加载列表）
ImageInfo是库中提供的数据Bean，需要两个url，分别表示小图和大图的url，没有大图或者小图，则都赋给相同的Url即可，AssNineGridViewClickAdapter是库中提供的默认实现了点击预览的Adapter
```
List<ImageInfo> imageInfos = getImageInfos(position);        
holder.angv.setAdapter(new AssNineGridViewClickAdapter(DemoActivity.this, imageInfos));

```
AssNineGridViewClickAdapter：库中自带的适配器，点击预览大图 
```
private List<ImageInfo> getImageInfos(int position) {
            List<ImageInfo> imageInfos = new ArrayList<>();
            List<String> images = mDatas.get(position).getImages();
            for (String url : images){
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(url);
                imageInfo.setThumbnailUrl(url);
                imageInfos.add(imageInfo);
            }
            return imageInfos;
        }
```
如果不想使用预览效果，可以自己继承 AssNineGridViewAdapter 实现其中 onImageItemClick 方法即可。
```
  List<ImageInfo> imageInfos = getImageInfos(position);
  MyAssAdapter assAdapter = new MyAssAdapter(DemoActivity.this, imageInfos);
  assAdapter.onImageItemClick(DemoActivity.this,  holder.angv, position, imageInfos);
  holder.angv.setAdapter(assAdapter);
```
 MyAssAdapter是自定义的适配器，继承AssNineGridViewAdapter
```
private class MyAssAdapter extends AssNineGridViewAdapter{
            private Context mContext;
             MyAssAdapter(Context context, List<ImageInfo> imageInfo) {
                super(context, imageInfo);
                 mContext = context;
            }

            @Override
            public void onImageItemClick(Context context, AssNineGridView angv, int index, List<ImageInfo> imageInfo) {
                super.onImageItemClick(context, angv, index, imageInfo);
                //执行点击事件
                Toast.makeText(mContext, "条目"+index+":自定义点击效果", Toast.LENGTH_SHORT).show();
            }

        }
```
## 关于
若遇到使用上的问题，请先翻看Issues,或者直接发邮件给我assionhonty@126.com
## 联系我
* gitHub [https://github.com/assion]
* CSDN博客：[https://blog.csdn.net/weixin_40509481]
* QQ 857516910
