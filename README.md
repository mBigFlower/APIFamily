# APIFamily

这是一个集合了各种实用性三方API的工程

其实就是无聊的时候弄一个小玩意儿。 本意是给我爸妈他们用的，是不是很孝顺 -。-

# 目录 #

- [手机号归属地](#phone2Place)
- [身份证号查询](#id)
- [油价查询](#oil)
- [健康菜谱](#cook)

## 手机号归属地 Phone2PlaceActivity##
<span id = "phone2Place"></span>
我用了个免费的api。所以查询结果有的不对。。。 免费的就这样吧。我会继续找更好的

eg: 1383653**** 本是一个鸡西的号码，查出来却是牡丹江~

## 油价查询 OilActivity ##
<span id = "oil"></span>
每秒钟只允许10次的并发查询，，， 免费的果然有缺陷

## 身份证号查询 IdActivity ##
<span id = "id"></span>
通过身份证号，查询性别，地址，生日

## 健康菜谱 CookActivity##
<span id = "cook"></span>
这里我们用到了图片加载库Fresco，**在apilibrary里的MyApplication中有个初始化！**

聚合里面的天狗菜谱，加载图片各种挂，，有点不想用了。。。

这个正在进行中，接口有点混乱
