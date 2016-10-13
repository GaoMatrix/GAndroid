## Retrofit2的处理
`http`目录中的`HttpCallback`,`HttpResponse`,`RetrofitClient`是作为标准的请求回调处理，
并将其放在GAndroidCommon的retrofit目录下面。
`HttpResponse`的结构如下：
```java
private int code;
private String message;
private T data;
```
而如果要处非标准的请求处理需要对`HttpCallback`,`HttpResponse`做特别的处理，例如当json的
返回结构如下时候：
```json
{
    error: false,
    results: [
        {
            _id: "57fd9af5421aa95dd78e8df1",
            createdAt: "2016-10-12T10:07:49.232Z",
            desc: "10-12",
            publishedAt: "2016-10-12T11:40:02.146Z",
            source: "chrome",
            type: "福利",
            url: "http://ww3.sinaimg.cn/large/610dc034jw1f8p9eahanlj20u011h42y.jpg",
            used: true,
            who: "daimajia"
        }
    ]
}
```
当json返回结构如上时候，上面的`HttpCallback`,`HttpResponse`,`RetrofitClient`这个框架结构
依然是可以使用的，只不过我们需要重新修改`HttpCallback`,`HttpResponse`使之和上面返回的json
结构对应`HttpCallbackMeizi`,`HttpResponseMeizi`，并不需要修改很多代码，只需要简单修改结构即可。



## 第三方框架的使用

### FlycoTabLayout
https://github.com/H07000223/FlycoTabLayout


### BGABanner-Android
引导界面滑动导航 + 大于等于1页时无限轮播 + 各种切换动画轮播效果
https://github.com/bingoogolapple/BGABanner-Android


### BGARefreshLayout-Android
多种下拉刷新效果、上拉加载更多、可配置自定义头部广告位
https://github.com/bingoogolapple/BGARefreshLayout-Android


### MaterialDrawer
The flexible, easy to use, all in one drawer library for your Android project. http://mikepenz.github.io/MaterialDrawer
https://github.com/mikepenz/MaterialDrawer


### recyclerview-animators
An Android Animation library which easily add itemanimator to RecyclerView items.
https://github.com/wasabeef/recyclerview-animators




