package left.cats

import cats.{Alternative,Foldable}

abstract class LeftPad[F, A] {
  /**
   * Pad an F on the left with As as needed to return an F of at least
   * length `length`.
   *
   * {{{
   * scala> LeftPad[String, Char].leftPad("asdf")(10, '-')
   * res0: String = ------asdf
   * }}}
   * Inspired by: https://www.npmjs.com/package/left-pad
   */
  def leftPad(a: F)(length: Int, pad: A): F
}

object LeftPad extends LeftPadInstances {
  def apply[F, A](implicit L: LeftPad[F, A]): LeftPad[F,A] = L

  // Simulacrum doesn't support MPTCs yet, but if it did it would look like:

  trait Ops[F, A] {
    def typeClassInstance: LeftPad[F, A]
    def self: F
    def leftPad(length: Int, pad: A): F = typeClassInstance.leftPad(self)(length, pad)
  }

  trait ToLeftPadOps {
    implicit def toLeftPadOps[F, A](target: F)(implicit tc: LeftPad[F, A]): Ops[F, A] = new Ops[F, A] {
      val self = target
      val typeClassInstance = tc
    }
  }

  object ops extends ToLeftPadOps

}

sealed abstract class LeftPadInstances extends LeftPadInstances1 {
  /**
   * The reason for the season
   */
  implicit val stringLeftPad: LeftPad[String, Char] = new LeftPad[String, Char] {
    def leftPad(a: String)(length: Int, pad: Char): String =
      (pad.toString * (length - a.length)) + a
  }
}

sealed abstract class LeftPadInstances1 {
  implicit def fromAlternative[F[_], A](implicit Fa: Alternative[F], Fo: Foldable[F]): LeftPad[F[A], A] = new LeftPad[F[A], A] {
    override def leftPad(fa: F[A])(padTo: Int, pad: A): F[A] = {

      def length(fa: F[A]): Int =
        Fo.foldLeft(fa, 0)((i,_) => i+1)

      @scala.annotation.tailrec def go(n: Int, acc: F[A]): F[A] =
        if(n <= 0) acc
        else go(n-1, Fa.combineK(Fa.pure(pad), acc))

      go(padTo - length(fa), fa)
    }
  }
}
