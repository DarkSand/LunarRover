package com.mjw.lunarrover

import java.util.Date

import scala.actors.Actor
import scala.util.Random
import scala.util.control._

/**
  * 月球车对象
  *
  * @param idx      月球车id
  * @param toPointx 月球车目标坐标
  */
class LunarRover(idx: String, toPointx: Point) extends Actor {

  /**
    * 月球车Id
    */
  private var id = idx

  /**
    * 当前位置
    */
  private var nowPoint = Point(0, 0)

  /**
    * 目标位置
    */
  private var toPoint = toPointx

  /**
    * 行进速度
    */
  private var v: Double = 1

  /**
    * 探测是否遇到障碍(随机生成)
    */
  private def detect: Boolean = {
    val r = Random
    return r.nextBoolean()
  }

  /**
    * 根据速度进行移动（延y轴正向行进）
    */
  private def moveY: Unit = {
    nowPoint = Point(nowPoint.x, nowPoint.y + v / 2)
    sendMessageMove()
  }

  /**
    * 根据速度进行移动（延x轴正向行进）
    */
  private def moveXPlus: Unit = {
    nowPoint = Point(nowPoint.x + v / 2, nowPoint.y)
    sendMessageMove()
  }

  /**
    * 根据速度进行移动（延x轴正向行进）
    */
  private def moveXMinus: Unit = {
    nowPoint = Point(nowPoint.x - v / 2, nowPoint.y)
    sendMessageMove()
  }

  /**
    * 绕过障碍物
    */
  private def turn: Unit = {
    sendMessageTurn //顺时针转动90度
    moveXPlus //延x轴正向移动
    sendMessageTurn //顺时针转动90度
    sendMessageTurn //顺时针转动90度
    sendMessageTurn //顺时针转动90度
    moveY //延y轴正向移动
    sendMessageTurn //顺时针转动90度
    sendMessageTurn //顺时针转动90度
    sendMessageTurn //顺时针转动90度
    moveXMinus //延x轴负向移动
    sendMessageTurn //顺时针转动90度
  }

  /**
    * 获取当前时间
    *
    * @return
    */
  private def getNowTime(): Date = {
    return new java.util.Date
  }

  /**
    * 向控制中心发送移动消息
    *
    */
  private def sendMessageMove(): Unit = {
    Thread.sleep(500)
    val message = new MessageMove(id, nowPoint, toPoint, v, getNowTime())
    ControlCenter ! message
  }

  /**
    * 向控制中心发送偏转消息
    *
    */
  private def sendMessageTurn(): Unit = {
    Thread.sleep(500) //顺时针转动90度
    val message = new MessageTurn(id, 90, getNowTime())
    ControlCenter ! message
  }

  /**
    * 向控制中心发送偏转消息
    *
    */
  private def sendMessageEnd(): Unit = {
    val message = new MessageEnd(id, nowPoint, toPoint, v, getNowTime())
    ControlCenter ! message
  }

  /**
    * 根据路线开始向目标移动
    */
  override def act: Unit = {
    // 创建 Breaks 对象
    val loop = new Breaks
    while (true) {
      //如果到达目标点则停止移动并发送消息到控制台
      if (nowPoint.y >= toPoint.y - v * 2) {
        sendMessageEnd
        loop.break
      }
      moveY //向y轴正向移动
      //探测前方是否有障碍物
      if (detect) {
        turn //绕过障碍物
      }
    }
  }
}
