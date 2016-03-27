package left.cats

import org.scalacheck._

object LeftPadSpec extends Properties("LeftPad") {
  import Prop._
  import LeftPad._

  property("zero") = forAll { (w: Char, s: String) =>
    LeftPad[String, Char].leftPad(s)(s.length, w) == s
  }

  property("shorter") = forAll { (w: Char, s: String) =>
    LeftPad[String, Char].leftPad(s)(s.length - 3, w) == s
  }

  property("longer") = forAll { (w: Char, s: String) =>
    LeftPad[String, Char].leftPad(s)(s.length + 3, w) == w.toString * 3 + s
  }

}
