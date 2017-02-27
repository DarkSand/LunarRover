# LunarRover
睿码科技笔试题

程序模拟5个月球车和1个控制中心

为了简化设计

假设月球车可以校正坐标系

例如

常规行进路线为

![image](https://github.com/DarkSand/mzituSpider/blob/master/pic/pic_1.png)

移动之前进行校正坐标系，将目标点的横坐标与当前月球车横坐标进行对齐设置为0

![image](https://github.com/DarkSand/mzituSpider/blob/master/pic/pic_2.png)

校正之后的坐标系如图所示

![image](https://github.com/DarkSand/mzituSpider/blob/master/pic/pic_3.png)

并且假设每个障碍物都可以通过下图的方式绕开

![image](https://github.com/DarkSand/mzituSpider/blob/master/pic/pic_4.png)

程序入口在Main.scala

月球车初始信息保存在pointInfo.txt,里面是json字符串

格式为
{
  "月球车Id": 目标点Y坐标
}

例如
{
  "lunar1": 10000,
  "lunar2": 11000,
  "lunar3": 12000,
  "lunar4": 13000,
  "lunar5": 14000
}

为了保证程序的运行时间，请将目标点Y坐标尽可能设置大一些
月球车速度为米/秒 默认为1
