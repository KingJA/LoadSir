<div align="center"><img src="res/rxbus.png"/></div>

LoadSir
---
中文 | [English](README-en.md)

`LoadSir`是一个高效易用，低碳环保，扩展性良好的加载反馈页展示框架，在加载网络或其他数据时候，根据需求切换状态页面，如加载中，加载失败，无数据，网络超时，加载成功等常用页面。可添加自定义状态页面，如占位图，登录失效等。可配合网络加载框架，结合返回状态码，错误码，数据封装使用。

Preview
---
| **in Activity**|**in View**|**in Fragment**|
|:---:|:----:|:----:|
|![](imgs/normal_activity.gif)|![](imgs/view_activity.gif)|![](imgs/single_fragment.gif)|

| **Placeholder**|**Muitl-Fragment**|**ViewPage+Fragment**|
|:---:|:----:|:----:|
|![](imgs/placeholder_activity.gif)|![](imgs/muitl_fragment.gif)|![](imgs/viewpage_fragment.gif)|


使用场景
---
下面为大家常见的加载反馈页面：

| **loading**|**error**|**timeout**|
|:---:|:----:|:----:|
|![](http://upload-images.jianshu.io/upload_images/1411177-4ff5baaeac614661.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|![](http://upload-images.jianshu.io/upload_images/1411177-be3ab1f823af479a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|![](http://upload-images.jianshu.io/upload_images/1411177-2eba000af858ab29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|

| **empty**|**empty**|**custom**|
|:---:|:----:|:----:|
|![](http://upload-images.jianshu.io/upload_images/1411177-41c45decb8b31c97.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|![](http://upload-images.jianshu.io/upload_images/1411177-a17c7015f0d00172.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|![](http://upload-images.jianshu.io/upload_images/1411177-8a37cfb2ad80027f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)|

面对这么多状态页面，你是不是还在用include的方式，setVisibility(View.VISIBLE/GONE)，这种方式即不方便控制，也造成了视图层级冗余。如果有一种工具，能把这些事都做了就好了。恰好，  `LoadSir` 把这些事做了，接下来我们就来了解一下它。

LoadSir的功能及特点
---
* :star:支持Activity，Fragment，Fragment(v4)，View状态回调
* :star:适配多个Fragment切换，及Fragment+ViewPager切换，不会状态叠加或者状态错乱
* :star:利用泛型转换输入信号和输出状态，可根据网络返回体的状态码或者数据返回自动适配状态页，实现全局自动状态切换
* :star:只加载唯一一个状态视图，不会预加载全部视图
* :star:不需要设置枚举或者常量状态值，直接用状态页类类型(xxx.class)作为状态码
* :star:可对单个状态页单独设置点击事件，根据返回boolean值覆盖或者结合OnReloadListener使用
* :star:低耦合，无预设页面，开发者随心配置
* 可设置重新加载点击事件(OnReloadListener)
* 可自定义状态页(继承Callback类)
* 可在子线程直接切换状态
* 可设置初始状态页(常用进度页作为初始状态)
* 可扩展状态页面，在配置中添加自定义状态页
* 可全局单例配置，也可以单独配置

开始使用LoadSir
---
## Dependencies

```groovy
compile 'com.kingja.loadsir:loadsir:1.1.1'
```

## <<配置>>

###### 全局配置方式
全局配置方式，使用的是单例模式，即获取的配置都是一样的。可在Application中配置，添加状态页，设置初始化状态页。
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
```
###### 单独配置方式
如果你即想保留全局配置，又想在某个特殊页面加点不同的配置，可采用该方式。

```java
LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
            }
        });
```
## <<注册>>

###### 在Activity中使用

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content);
    // Your can change the callback on sub thread directly.
    LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            // 重新加载逻辑
        }
    });
}}
```

###### 在View 中使用
```java
ImageView imageView = (ImageView) findViewById(R.id.iv_img);
LoadSir loadSir = new LoadSir.Builder()
        .addCallback(new TimeoutCallback())
        .setDefaultCallback(LoadingCallback.class)
        .build();
loadService = loadSir.register(imageView, new Callback.OnReloadListener() {
    @Override
    public void onReload(View v) {
        loadService.showCallback(LoadingCallback.class);
        // 重新加载逻辑
    }
});
```
###### 在Fragment 中使用
由于Fragment添加到Activitiy方式多样，比较特别，所以在Fragment注册方式不同于上面两种，大家先看模板代码：
```java
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
        savedInstanceState) {
    //第一步：获取布局View
    rootView = View.inflate(getActivity(), R.layout.fragment_a_content, null);
    //第二步：注册布局View
    LoadService loadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            // 重新加载逻辑
        }
    });
    //第三步：返回LoadSir生成的LoadLayout
    return loadService.getLoadLayout();
}
```

## <<回调>>

###### 直接回调
```java
protected void loadNet() {
        // 进行网络访问...
        // 进行回调
        loadService.showSuccess();//成功回调
        loadService.showCallback(EmptyCallback.class);//其他回调
    }
```
###### 转换器回调 (推荐使用)
如果你不想再每次回调都要手动进行的话，可以选择注册的时候加入转换器，可根据返回的数据，适配对应的回调。

```java
LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
    @Override
    public void onReload(View v) {
            // 重新加载逻辑
    }}, new Convertor<HttpResult>() {
    @Override
    public Class<? extends Callback> map(HttpResult httpResult) {
        Class<? extends Callback> resultCode = SuccessCallback.class;
        switch (httpResult.getResultCode()) {
            case SUCCESS_CODE://成功回调
                if (httpResult.getData().size() == 0) {
                    resultCode = EmptyCallback.class;
                }else{
                    resultCode = SuccessCallback.class;
                }
                break;
            case ERROR_CODE:
                resultCode = ErrorCallback.class;
                break;
        }
        return resultCode;
    }
});
```
回调的时候直接传入转换器指定类型的数据。
```java
loadService.showWithConvertor(httpResult);
```
## Changelog

**v1.1.1**
- Initial release .

## Contact me
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* email:kingjavip@gmail.com

## License

    Copyright 2017 KingJA

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.