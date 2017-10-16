package io.arausuy.gl.exercise.two

import scala.collection.immutable.Stream.Empty

object IntegersToTotal extends App {

  def run(bagOfInts: Seq[Int], total: Int): String = {
    attemptToCalculateToTotal(bagOfInts, total) match {
      case Right(r) => decorate(r)
      case Left(_) => "No solution found"
    }
  }

  def attemptToCalculateToTotal(bagOfInts: Seq[Int], total: Int): Either[Unit, Seq[Operator]] = {

    def recurse(ints: Stream[Int], equation: Seq[Operator]): Either[Unit, Seq[Operator]] = {
      val totalSoFar = calculate(equation)
      if (totalSoFar == total) {
        Right(equation)
      } else if (totalSoFar < 0) {
        Left()
      } else {
        ints match {
          case Empty => Left()
          case h #:: tail =>
            List(recurse(tail, equation :+ Add(h)),
              recurse(tail, equation :+ Subtract(h)),
              recurse(tail, equation :+ Multiply(h)))
              .find { r =>
                r.isRight
              } match {
              case Some(r) => r
              case None =>
                recurse(ints.filterNot(f => f == h), equation)
            }
        }
      }
    }

    if (!bagOfInts.exists(f => f < 0)) {
      recurse(bagOfInts.toStream, Seq.empty[Operator])
    } else {
      Left(())
    }
  }

  private def calculate(expression: Seq[Operator]): Int = expression.foldLeft(0) { (i, o) =>
    o.operate(i)
  }

  def decorate(expression: Seq[Operator]): String = {
    val initBrackets = "(" * expression.size

    expression match {
      case Nil => "No answer available"
      case h :: Nil => s"(${h.value})"
      case h :: tail => tail.foldLeft(s"$initBrackets${h.value}") { (x, y) => s"$x ${y.toString})"}
    }
  }
  
  
  if (args.length == 2) {
    val bag = args(0).split(",").map(f => f.toInt)
    val total = args(1).toInt

    println(s"Solution found : ${run(bag, total)}")
  } else {
    println("Please supply a csv of Ints and a total")
  }

}
