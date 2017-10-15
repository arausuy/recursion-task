package io.arausuy.gl.exercise.one

import org.scalatest.{Matchers, WordSpec}

class LCSSpec extends WordSpec with Matchers {

  "LCS" must {

    "Return an empty string on no match - empty strings as input" in {
      LCS.lcs("", "") shouldBe ""
    }

    "Return an empty string on no match - left empty strings as input" in {
      LCS.lcs("", "AHAHSH") shouldBe ""
    }

    "Return an empty string on no match - right empty strings as input" in {
      LCS.lcs("AHHASG", "") shouldBe ""
    }

    "Find the longest common discontiguous string" in {
      LCS.lcs("AYUTFUYSDSOHJOSD", "AHAHSVCBDJEJHVSF") shouldBe "ASDJS"
    }

    "Return whole string if they are equal" in {
      LCS.lcs("YUSUF", "YUSUF") shouldBe "YUSUF"
    }

    //Example from exercise
    "Give expected input" in {
      LCS.lcs("AABACDA", "DACBBCAD") shouldBe "ABCA"
    }

    "Give expected input with arguments should yield a different result because of bias" in {
      LCS.lcs("DACBBCAD", "AABACDA") shouldBe "ABAD"
    }
  }


}
