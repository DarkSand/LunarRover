package com.mjw.lunarrover

import scala.io.Source
import scala.util.parsing.json.JSON

/**
  * 程序入口
  */
object Main {

  def main(args: Array[String]): Unit = {

    //启动控制中心接收消息
    ControlCenter.start

    //从文本读取月球车数据，解析为json对象，并生成月球车对象，启动月球车
    val jsonStr = Source.fromFile("src/pointInfo.txt").mkString
    val json = JSON.parseFull(jsonStr)
    json match {
      case Some(map: Map[String, Double]) =>
        map.keys.foreach(key =>
          (new LunarRover(key, Point(0, map(key)))).start //生成月球车对象，启动月球车
        )
    }
  }
}
