# App的介绍
## App的功能
1.该App的主要功能是对当天的天气和一些温馨提示和对近7天的天气情况进行一个呈现
## 主要功能的实现
2.首先使用网络请求得到当天的天气信息和温馨提示，然后再用cardview将温馨提示呈现出来，对近7天的天气情况的呈现则使用的是recyclerview.在界面的跳转方面，我直接编写了不同的跳转动画
# App的展示
<img src="https://user-images.githubusercontent.com/119687323/216045753-273328cd-5802-4685-9ded-76eb6cb8ed8f.gif" width="100px">  <img src="https://user-images.githubusercontent.com/119687323/216048419-1ad6ba0e-6cc4-45a2-9ba5-917288dde3a5.jpg" width="100px"> <img src="https://user-images.githubusercontent.com/119687323/216048801-a0b72a52-ec52-454c-9879-cc11c9fc80a6.jpg" width="100px"> 
# 技术亮点
使用了framelayout与scrollview的结合实现了背景；新建了ainm文件实现了界面跳转之间的动画；使用了cardview美化了ui
#心得
学会了一些新控件的使用；安卓动画的使用；OKhttp和glide的使用，我想，如果我没有进入红岩，我不会学到这么多，感谢红岩，感谢学长们。
#待提升
1.使用viewmodel进行activity与fragment的通信
2.使用线程池进行网络请求的并发
