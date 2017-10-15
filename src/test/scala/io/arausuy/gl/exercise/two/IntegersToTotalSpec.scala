package io.arausuy.gl.exercise.two

import org.scalatest.{Matchers, WordSpec}


class IntegersToTotalSpec extends WordSpec with Matchers {

  "Integers to total" must {
    "calculate that it can get to 3 with easy inputs" in {
      IntegersToTotal.attemptToCalculateToTotal(Seq(1,2), 3) shouldBe Right(Seq(Add(1), Add(2)))
    }

    "not accept negative inputs" in {
      IntegersToTotal.attemptToCalculateToTotal(Seq(-1), 3) shouldBe  Left()
    }

    "calculate that it can get this with easy inputs" in {
      IntegersToTotal.attemptToCalculateToTotal(Seq(2, 3, 5, 6), 42) shouldBe Right(List(Add(2), Add(5), Multiply(6)))
    }

    "decorated string for a full answer should be correct" in {
      val q = IntegersToTotal.attemptToCalculateToTotal(Seq(2, 3, 5, 6), 42)
      IntegersToTotal.decorate(q.right.get) shouldBe "(((2 + 5) x 6)"
    }

    "decorated string for a single value answer should be correct" in {
      val q = IntegersToTotal.attemptToCalculateToTotal(Seq(6), 6)
      IntegersToTotal.decorate(q.right.get) shouldBe "(6)"
    }

  }


}
