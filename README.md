<div align="center"><img src="res/rxbus.png"/></div>

# LoadSir

一个高效易用，扩展性良好的加载反馈页展示框架，在加载网络或其他数据时候，根据需求切换状态页面，如加载中，加载失败，无数据，
加载成功等页面。可添加自定义状态页面，如网络超时，登录失效等。可配合网络加载框架，结合返回状态码，错误码，数据封装使用。
建议在BaseActivity，BaseFragment中全局配置。

## Preview
![](res/rxbus2.gif)

## Feature
* 可设置重新加载点击事件(OnReloadListener)
* 可自定义状态页(继承Callback类)
* 可在子线程直接切换状态
* 可设置初始状态页面(常用进度页LoadingCallback作为初始状态)


(与其它库相比)

* 不需要设置枚举或者常量状态值，直接用状态页类类型作为状态码
* 可扩展状态页面，可在配置中添加自定义状态页
* 可对单个状态页单独设置点击事件，根据返回boolean值覆盖或者结合OnReloadListener使用。
* 支持Activity，Fragment,Fragment(v4),View域状态回调
* 适配多个Fragment切换，及Fragment+ViewPager切换
* 利用泛型转换输入信号和输出状态，如，根据网络返回体的状态码或者数据返回不同的状态页类类型，实现全局自动切换
* 可全局单例配置，也可以单独配置
* 为解耦，无预设页面，开发者随心所欲

## Dependencies

```groovy

```

## Usage

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