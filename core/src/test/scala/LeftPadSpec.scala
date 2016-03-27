package left.cats

import org.scalacheck._
import org.scalacheck.Prop.forAll

object LeftPadSpec extends Properties("left-pad") {
  import LeftPad._

  implicit val arbInt: Arbitrary[Int] = Arbitrary(Gen.choose(-100, 100))

  property("string") = forAll{(a: String, c: Char, n: Int) => 
    LeftPad[String,Char].leftPad(a)(n,c) == (if(n < a.length) a else (a.reverse ++ (c.toString * n)).take(n).reverse)
  }
}

