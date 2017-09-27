![](imgs/LoadSir.jpg)

English | [中文](README-cn.md)

What's LoadSir?
---

[![](https://img.shields.io/badge/%20%20Android%20Arsenal%20%20-%20%20LoadSir%20%20-blue.svg)](https://android-arsenal.com/details/1/6227)
[![](https://img.shields.io/badge/%20%20aar%20size-26KB-green.svg)](https://bintray.com/kingja/maven/loadsir#files/com%2Fkingja%2Floadsir%2Floadsir%2F1.2.0)
[![](https://img.shields.io/github/release/KingJA/LoadSir.svg)](https://github.com/KingJA/LoadSir/releases)


***LoadSir*** is a lightweight, good expandability Android library used for displaying different pages like **loading**,
**error**, **empty**, **timeout** or even your **custom page** when you load a page (such as do net job). LoadSir is very different from
other similar libraries. I mean... ***better***.

Preview
---
| **in Activity**|**in View**|**in Fragment**|
|:---:|:----:|:----:|
|![](imgs/normal_activity.gif)|![](imgs/view_activity.gif)|![](imgs/single_fragment.gif)|

| **Placeholder**|**Multi-Fragment**|**ViewPager+Fragment**|
|:---:|:----:|:----:|
|![](imgs/placeholder_activity.gif)|![](imgs/muitl_fragment.gif)|![](imgs/viewpage_fragment.gif)|

Feature
---
* :star: support for Activity, Fragment, Fragment(v4), View
* :star: support for Multi-Fragment, Fragment+ViewPager
* :star: convert http result structure into a Callback
* :star: don't need to modify the layout
* :star: only load one layout once
* :star: don't need to set enum or constant for status code
* :star: set your own onClick logic in custom Callback
* :star: no preloaded load page
* allow to customize your own load page
* set the retry onClick listener
* set the default load page
* add multi load pages
* thread-safety

How does LoadSir works?
---
<div align="center"><img src="imgs/LoadSir_flow.jpg"/></div>

🚀 Getting started
---

LoadSir only needs 3 steps to finish its task: **Config** -> **Register** -> **Display**

### Download

```groovy
compile 'com.kingja.loadsir:loadsir:1.2.0'
```

### Step 1: Config
There are two ways to set the config. Add your custom pages and set the default page.

* ###### Global Config
Set config with singleton pattern, you can do it in your Application. No matter where you do this job, you could get the
unique LoadSir everywhere.

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
* ###### Single Config
If you want to create another specific LoadSir, you can set config like this.

```java
LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // retry logic
            }
        });
```
### Step 2: Register

Tell LoadSir which "layout" you want be replaced with LoadLayout.

* ###### Register in Activity
```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content);
    // Your can change the callback on sub thread directly.
    LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            // retry logic
        }
    });
}}
```

* ###### Register in View
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
        // retry logic
    }
});
```

* ###### Register in Fragment
Use it in Fragment is a bit different from the other two, follow the template code.
```java
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
        savedInstanceState) {
    //step 1：obtain root view
    rootView = View.inflate(getActivity(), R.layout.fragment_a_content, null);
    //step 2：obtain the LoadService
    LoadService loadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            // retry logic
        }
    });
    //step 3：return the LoadLayout from LoadService
    return loadService.getLoadLayout();
}
```

### Step 3: Display

* ###### Direct Display
```java
protected void loadNet() {
        // do net job...
        // callback
        loadService.showSuccess();//successful case
        loadService.showCallback(EmptyCallback.class);//other case
    }
```
* ###### Convertor Display (recommended)
If you want LoadSir to do callback automatically, you can pass a Convertor when you register.

```java
LoadService loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
    @Override
    public void onReload(View v) {
         // retry logic
    }}, new Convertor<HttpResult>() {
    @Override
    public Class<? extends Callback> map(HttpResult httpResult) {
        Class<? extends Callback> resultCode = SuccessCallback.class;
        switch (httpResult.getResultCode()) {
            case SUCCESS_CODE:
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
Pass a HttpResult, now you start up a robot LoadSir.
```java
loadService.showWithConvertor(httpResult);
```

### Customize
You can customize your own load page like loading, empty, error, timeout, etc. Provide the layout and fill the retry
logic (if necessarily).

```java
public class CustomCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    protected boolean onRetry(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello buddy! :p", Toast.LENGTH_SHORT).show();
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

### :bulb: About placeholder effect
The effect of placeholder is just like the library [ShimmerRecyclerView](https://github.com/sharish/ShimmerRecyclerView)
works. LoadSir do the similar job only through a PlaceHolderCallback, just a custom ***Callback***. That feeling was
amazing. :ghost:

## Docs
* :point_right: [FAQ](docs/FAQ.md)
* 📌 [Versions](docs/changelog.md)
* [Best Practice](docs/BestPractice.md)
* [Next Version](docs/NextVersion.md)

## ProGuard

```xml
-dontwarn com.kingja.loadsir.**
-keep class com.kingja.loadsir.** {*;}
```
## Contact Me
Any questions: Welcome to contact me.
* Email: kingjavip@gmail.com

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
