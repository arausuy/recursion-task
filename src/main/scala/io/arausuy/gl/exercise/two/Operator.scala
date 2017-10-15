package io.arausuy.gl.exercise.two

sealed trait Operator {
  val value: Int

  def operate(leftHand: Int): Int
}

case class Multiply(value: Int) extends Operator {
  override def operate(leftHand: Int): Int = leftHand * value

  override def toString: String = s"x $value"
}

case class Add(value: Int) extends Operator {
  override def operate(leftHand: Int): Int = leftHand + value

  override def toString: String = s"+ $value"
}

case class Subtract(value: Int) extends Operator {
  override def operate(leftHand: Int): Int = leftHand - value

  override def toString: String = s"- $value"
}
