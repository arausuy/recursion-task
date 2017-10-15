package io.arausuy.gl.exercise.one

import scala.collection.immutable.Stream.Empty

object LCS extends App {

  def lcs(l: String, r: String): String = {
    def recurse(l2: Stream[Char], r2: Stream[Char]): Stream[Char] = {
      (l2, r2) match {
        case (Empty, _) => Stream.empty[Char]
        case (_, Empty) => Stream.empty[Char]
        case (lh #:: ltail, rh #:: rtail) if lh == rh => lh #:: recurse(ltail, rtail)
        case (lh #:: ltail, rh #:: rtail) =>
          (recurse(ltail, r2), recurse(l2, rtail)) match {
            case (le, ri) if le.size >= ri.size => le
            case (_, ri) => ri
          }
      }
    }
    recurse(l.toStream, r.toStream).mkString
  }
}
