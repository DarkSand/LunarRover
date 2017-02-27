package com.mjw.lunarrover

import scala.actors.Actor

/**
  * 控制中心
  */
object ControlCenter extends Actor {
  /**
    * 预测当前月球车位置
    *
    * @param nowy 当前y坐标
    * @param v    当前速度
    * @return 预测的当前月球车y坐标
    */
  def forecast(nowy: Double, v: Double): Double = {
    return nowy + v * 2
  }

  /**
    * 启动控制中心以接收消息
    */
  def act(): Unit = {
    while (true) {
      receive {
        case MessageMove(lunarId, start, end, v, date) => {
          printf("%s 【月球车-%s 开始移动】 接收坐标 %s:%s 预测坐标 %s:%s 目标坐标 %s:%s 速度:%s \n", date, lunarId, start.x, start.y, start.x, forecast(start.y, v), end.x, end.y, v)
        }
        case MessageTurn(lunarId, angle, date) => {
          printf("%s 【月球车-%s 开始偏转】 顺时针偏转角度%s \n", date, lunarId, angle)
        }
        case MessageEnd(lunarId, start, end, v, date) => {
          printf("%s 【月球车-%s 到达指定坐标】 接收坐标 %s:%s 预测坐标 %s:%s 目标坐标 %s:%s 速度:%s \n", date, lunarId, start.x, start.y, start.x, forecast(start.y, v), end.x, end.y, v)
        }
      }
    }
  }
}
