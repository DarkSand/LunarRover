package com.mjw.lunarrover

import java.util.Date

/**
  * 月球车向控制中心发送的消息
  *
  * @param lunarId 月球车ID
  * @param start   起始点
  * @param end     目标点
  * @param v       速度
  * @param time    当前时间
  */
case class MessageMove(lunarId: String, start: Point, end: Point, v: Double, time: Date)

/**
  * 月球车向控制中心发送的消息
  *
  * @param lunarId 月球车Id
  * @param angle   偏转角度
  * @param time    当前时间
  */
case class MessageTurn(lunarId: String, angle: Double, time: Date)

/**
  * 月球车向控制中心发送的消息(代表到达目标地点)
  *
  * @param lunarId
  * @param time
  */
case class MessageEnd(lunarId: String, start: Point, end: Point, v: Double, time: Date)
