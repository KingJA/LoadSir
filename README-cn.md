![](imgs/LoadSir.jpg)

中文 | [English](README-en.md)

LoadSir
---

[![](https://img.shields.io/badge/%20%20Android%20Arsenal%20%20-%20%20LoadSir%20%20-blue.svg)](https://android-arsenal.com/details/1/6227)
[![](https://img.shields.io/badge/%20%20aar%20size-26KB-green.svg)](https://bintray.com/kingja/maven/loadsir#files/com%2Fkingja%2Floadsir%2Floadsir%2F1.2.0)

`LoadSir`是一个高效易用，低碳环保，扩展性良好的加载反馈页管理框架，在加载网络或其他数据时候，根据需求切换状态页面，
可添加自定义状态页面，如加载中，加载失败，无数据，网络超时，如占位图，登录失效等常用页面。可配合网络加载框架，结合返回
状态码，错误码，数据进行状态页自动切换，封装使用效果更佳。

使用场景
---
| **in Activity**|**in View**|**in Fragment**|
|:---:|:----:|:----:|
|![](imgs/normal_activity.gif)|![](imgs/view_activity.gif)|![](imgs/single_fragment.gif)|

| **Placeholder**|**Muitl-Fragment**|**ViewPage+Fragment**|
|:---:|:----:|:----:|
|![](imgs/placeholder_activity.gif)|![](imgs/muitl_fragment.gif)|![](imgs/viewpage_fragment.gif)|

流程图
---

<a href="imgs/LoadSir_flow.jpg" class="github-corner" aria-label="View source on Github"><svg width="80" height="80" viewBox="0 0 250 250" style="fill:#151513; color:#fff; position: absolute; top: 0; border: 0; right: 0;" aria-hidden="true"><path d="M0,0 L115,115 L130,115 L142,142 L250,250 L250,0 Z"></path><path d="M128.3,109.0 C113.8,99.7 119.0,89.6 119.0,89.6 C122.0,82.7 120.5,78.6 120.5,78.6 C119.2,72.0 123.4,76.3 123.4,76.3 C127.3,80.9 125.5,87.3 125.5,87.3 C122.9,97.6 130.6,101.9 134.4,103.2" fill="currentColor" style="transform-origin: 130px 106px;" class="octo-arm"></path><path d="M115.0,115.0 C114.9,115.1 118.7,116.5 119.8,115.4 L133.7,101.6 C136.9,99.2 139.9,98.4 142.2,98.6 C133.8,88.0 127.5,74.4 143.8,58.0 C148.5,53.4 154.0,51.2 159.7,51.0 C160.3,49.4 163.2,43.6 171.4,40.1 C171.4,40.1 176.1,42.5 178.8,56.2 C183.1,58.6 187.2,61.8 190.9,65.4 C194.5,69.0 197.7,73.2 200.1,77.6 C213.8,80.2 216.3,84.9 216.3,84.9 C212.7,93.1 206.9,96.0 205.4,96.6 C205.1,102.4 203.0,107.8 198.3,112.5 C181.9,128.9 168.3,122.5 157.7,114.1 C157.9,116.9 156.7,120.9 152.7,124.9 L141.0,136.5 C139.8,137.7 141.6,141.9 141.8,141.8 Z" fill="currentColor" class="octo-body"></path></svg></a><style>.github-corner:hover .octo-arm{animation:octocat-wave 560ms ease-in-out}@keyframes octocat-wave{0%,100%{transform:rotate(0)}20%,60%{transform:rotate(-25deg)}40%,80%{transform:rotate(10deg)}}@media (max-width:500px){.github-corner:hover .octo-arm{animation:none}.github-corner .octo-arm{animation:octocat-wave 560ms ease-in-out}}</style>


LoadSir的功能及特点
---
* :star:支持Activity，Fragment，Fragment(v4)，View状态回调
* :star:适配多个Fragment切换，及Fragment+ViewPager切换，不会布局叠加或者布局错乱
* :star:利用泛型转换输入信号和输出状态，可根据网络返回体的状态码或者数据返回自动适配状态页，实现全局自动状态切换
* :star:只加载唯一一个状态视图，不会预加载全部视图
* :star:不需要设置枚举或者常量状态值，直接用状态页类类型(xxx.class)作为状态码
* :star:可对单个状态页单独设置点击事件，根据返回boolean值覆盖或者结合OnReloadListener使用，如网络错误可跳转设置页
* :star:无预设页面，低耦合，开发者随心配置
* 可设置重新加载点击事件(OnReloadListener)
* 可自定义状态页(继承Callback类)
* 可在子线程直接切换状态
* 可设置初始状态页(常用进度页作为初始状态)
* 可扩展状态页面，在配置中添加自定义状态页
* 可全局单例配置，也可以单独配置



开始使用LoadSir
---

LoadSir的使用，只需要简单的三步

### 添加依赖

```groovy
compile 'com.kingja.loadsir:loadsir:1.2.0'
```

### 第一步：配置

###### 全局配置方式
全局配置方式，使用的是单例模式，即获取的配置都是一样的。可在Application中配置，添加状态页，设置默认状态页

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
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
### 第二步：注册

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

### 第三步： 回调

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
如果你不想再每次回调都要手动进行的话，可以选择注册的时候加入转换器，可根据返回的数据，适配对应的状态页。

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
回调的时候直接传入转换器指定的数据类型。
```java
loadService.showWithConvertor(httpResult);
```

### 自定义回调页
LoadSir为了完全解耦，没有预设任何状态页，需要自己实现，开发者自定义自己的回调页面，比如加载中，没数据，错误，超时等常用页面，
设置布局及自定义点击逻辑

```java
public class CustomCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    protected boolean onRetry(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello mother fuck! :p", Toast.LENGTH_SHORT).show();
        (view.findViewById(R.id.iv_gift)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "It's your gift! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
```
## Changelog

[V1.2.0](docs/changelog.md)

## Contact Me
Any questions,Welcome to contact me.
* [Blog](http://www.jianshu.com/u/8a1a8ed656e8)
* Email:kingjavip@gmail.com

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