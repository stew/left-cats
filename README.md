left cats
=========

[![Join the chat at https://gitter.im/stew/left-cats](https://badges.gitter.im/stew/left-cats.svg)](https://gitter.im/stew/left-cats?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/stew/left-cats.svg?branch=master)](https://travis-ci.org/stew/left-cats)
[![codecov.io](http://codecov.io/github/stew/left-cats/coverage.svg?branch=master)](http://codecov.io/github/stew/left-cats?branch=master)

A scala implementation of the [left-pad javascript library](https://www.npmjs.com/package/left-pad)

I was inspired by [this article](http://arstechnica.com/information-technology/2016/03/rage-quit-coder-unpublished-17-lines-of-javascript-and-broke-the-internet/) to port this to Scala.

This implementation will let you pad arbitrary F[A] structures with As
as long as you have implicit cats.Foldable and cats.Alternative instances.

example usage:

    scala> import left.cats._
    import left.cats._

    scala> LeftPad[String,Char].leftPad("asdf")(10, '-')
    res0: String = ------asdf

with syntax:

    scala> import LeftPad.ops._
    import LeftPad.ops._

    scala> "asdf".leftPad(10, '-')
    res1: String = ------asdf

Of course, it builds in scalaJS as well.
