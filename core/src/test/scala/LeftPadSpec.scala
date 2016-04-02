package left.cats

import org.scalacheck._
import org.scalacheck.Prop.forAll

object LeftPadSpec extends Properties("left-pad") {
  import LeftPad._

  implicit val arbInt: Arbitrary[Int] = Arbitrary(Gen.choose(-100, 100))

  property("string") = forAll { (a: String, c: Char, n: Int) =>
    LeftPad[String, Char].leftPad(a)(n, c) == (if (n < a.length) a else (a.reverse ++ (c.toString * n)).take(n).reverse)
  }

  import cats._
  import cats.implicits._

  property("alternative foldable") = forAll { (a: Vector[Char], c: Char, n: Int) =>
    LeftPad[Vector[Char], Char].leftPad(a)(n, c).foldMap(_.toString) == LeftPad[String,Char].leftPad(a.mkString(""))(n, c)
  }

  property("alternative foldable: List[Int]") = forAll { (a: List[Int], i: Int, n: Int) =>
    LeftPad[List[Int], Int].leftPad(a)(n, i) == (if (n <= a.length) a else Stream.continually(i).take(n - a.length).toList ++ a)
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

  property("syntax consistency 1") = forAll { (w: Char, s: String, n: Int) =>
    import LeftPad.ops._
    LeftPad[String, Char].leftPad(s)(n, w) == s.leftPad(n, w)
  }

  property("syntax consistency 2") = forAll { (pad: Int, ns: List[Int], n: Int) =>
    import LeftPad.ops._
    LeftPad[List[Int], Int].leftPad(ns)(n, pad) == ns.leftPad(n, pad)
  }

}
