![](imgs/LoadSir.jpg)

中文 | [English](README-en.md)

LoadSir
---

[![](https://img.shields.io/badge/%20%20Android%20Arsenal%20%20-%20%20LoadSir%20%20-blue.svg)](https://android-arsenal.com/details/1/6227)
[![](https://img.shields.io/badge/%20%20aar%20size-26KB-green.svg)](https://bintray.com/kingja/maven/loadsir#files/com%2Fkingja%2Floadsir%2Floadsir%2F1.2.0)
:point_right:[![](https://img.shields.io/github/release/KingJA/LoadSir.svg)](https://github.com/KingJA/LoadSir/releases):point_left:

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

下载 Demo
---
![](imgs/qcode_1.3.8.png)
###### (密码：9517)

流程图
---
<div align="center"><img src="imgs/LoadSir_flow.jpg"/></div>

LoadSir的功能及特点
---
* :star:支持Activity，Fragment，Fragment(v4)，View状态回调
* :star:适配多个Fragment切换，及Fragment+ViewPager切换，不会布局叠加或者布局错乱
* :star:利用泛型转换输入信号和输出状态，可根据网络返回体的状态码或者数据返回自动适配状态页，实现全局自动状态切换
* :star:无需修改布局文件
* :star:只加载唯一一个状态视图，不会预加载全部视图
* :star:不需要设置枚举或者常量状态值，直接用状态页类类型(xxx.class)作为状态码
* :star:可对单个状态页单独设置点击事件，根据返回boolean值覆盖或者结合OnReloadListener使用，如网络错误可跳转设置页
* :star:无预设页面，低耦合，开发者随心配置
* :star:可保留标题栏(Toolbar,titile view等)
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
compile 'com.kingja.loadsir:loadsir:1.3.8'
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
Ps:
[1]要注册RelativeLayout或ConstraintLayout的子View，如果该子View被其它子View约束，建议在子View外层再包一层布局，参考
acitivy_view.xm和activity_constraintlayout.xml
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

    //填充布局
    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }
    //当前Callback的点击事件，如果返回true则覆盖注册时的onReloa()，如果返回false则两者都执行，先执行onReloadEvent()。
    @Override
    protected boolean onReloadEvent(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello buddy! :p", Toast.LENGTH_SHORT).show();
        (view.findViewById(R.id.iv_gift)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "It's your gift! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

    //是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    //将Callback添加到当前视图时的回调，View为当前Callback的布局View
    @Override
    public void onAttach(Context context, View view) {
        super.onAttach(context, view);
    }

    //将Callback从当前视图删除时的回调，View为当前Callback的布局View
    @Override
    public void onDetach() {
        super.onDetach(context, view);
    }

}
```
### 动态修改Callback

```java
loadService = LoadSir.getDefault().register(...);
loadService.setCallBack(EmptyCallback.class, new Transport() {
   @Override
   public void order(Context context, View view) {
       TextView mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
       mTvEmpty.setText("fine, no data. You must fill it!");
   }
});
```

### LoadSir自带便携式Callback

```java
ProgressCallback loadingCallback = new ProgressCallback.Builder()
        .setTitle("Loading", R.style.Hint_Title)
        .build();

HintCallback hintCallback = new HintCallback.Builder()
        .setTitle("Error", R.style.Hint_Title)
        .setSubTitle("Sorry, buddy, I will try it again.")
        .setHintImg(R.drawable.error)
        .build();

LoadSir loadSir = new LoadSir.Builder()
        .addCallback(loadingCallback)
        .addCallback(hintCallback)
        .setDefaultCallback(ProgressCallback.class)
        .build();
```

在使用过程中，遇到问题可以先去[FAQ](docs/FAQ.md)和Issues看看有没解决方案，如果没有的话，请给我提Issue吧。


### :bulb: About placeholder effect
placeholder效果状态页类似[ShimmerRecyclerView](https://github.com/sharish/ShimmerRecyclerView)的效果. LoadSir只用了一个
自定义状态页PlaceHolderCallback就完成类似的效果，是不是很棒 :ghost:

## Docs
* :point_right: [常见问题](docs/FAQ-cn.md)
* 📌 [更新日志](docs/changelog.md)
* [最佳实践](docs/BestPractice-cn.md)
* [下个版本开发计划](docs/NextVersion.md)

## 代码混淆

```xml
-dontwarn com.kingja.loadsir.**
-keep class com.kingja.loadsir.** {*;}
```

## Contact Me
Any questions,Welcome to contact me.
* Email:kingjavip@gmail.com
* QQ群(818251143)

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
