package left.cats

import org.scalacheck._
import org.scalacheck.Prop.forAll

object LeftPadSpec extends Properties("left-pad") {
  import LeftPad._

  implicit val arbInt: Arbitrary[Int] = Arbitrary(Gen.choose(-100, 100))

  property("string") = forAll { (a: String, c: Char, n: Int) =>
    LeftPad[String,Char].leftPad(a)(n,c) == (if(n < a.length) a else (a.reverse ++ (c.toString * n)).take(n).reverse)
  }

  import cats._
  import cats.implicits._

  property("alternative foldable") = forAll { (a: Vector[Char], c: Char, n: Int) =>
    LeftPad[Vector[Char],Char].leftPad(a)(n,c).foldMap(_.toString) == LeftPad[String,Char].leftPad(a.mkString(""))(n,c)
  }

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

