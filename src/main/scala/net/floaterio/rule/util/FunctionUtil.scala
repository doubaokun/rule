package net.floaterio.rule.util

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/04
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */

object FunctionUtil {
//  implicit def toComposableFunction1[T1, R](func:T1 => R) =
//    new ComposableFunction1[T1, R]{ val f = func }

}

//trait ComposableFunction1[-T1, +R]  {
//  val f: T1 => R
//  def >>[A](g:R => A):T1 => A = f andThen g
//  def <<[A](g:A => T1):A => R = f compose g
//
//  // applyの別名
//  def <*>(v:T1) = f apply(v)
//}

class OptionalFunctionCombiner[A, B](f1: A => Option[B]) {

  def >>>[C](f2: B => Option[C]): A => Option[C] = {
    f1 andThen { op =>
      op.map { b =>
        f2.apply(b)
      }.getOrElse(None)
    }
  }

  def >>[C](f2: B => C): A => Option[C] = {
    f1 andThen { op =>
      op.map { b =>
        f2.apply(b)
      }
    }
  }
}



