package lecture.cp

import jp.kobe_u.copris._, dsl._

object DemoGcp {
  val nodes = Seq(0,1,2,3,4)
  val edges = Seq((0,1),(1,2),(2,3),(1,3),(3,4),(0,4))

  def gcp1() = {
    init
    /* 整数変数: n1, n2, n3, n4, n5 の定義とCSPへの追加 */
    for (node <- nodes) {
      int(Var("n" + node), 1, 3)
    }

    /* 制約の定義とCSPへの追加 */
    for ((u,v) <- edges) {
      add(Var("n" + u) =/=  Var("n" + v)) // =/= は等号否定を意味する．
    }

    dump("csp/demo-gcp1.csp")
    init
  }

  def gcp2() = {
    init
    /* 整数変数: n1, n2, n3, n4, n5 の定義とCSPへの追加
    *  および
    *  それらの変数の列 ns の定義
    * */
    val ns = for (node <- nodes) yield int(Var("n" + node), 1, 3)

    /* 制約の定義とCSPへの追加 */
    for ((u,v) <- edges) {
      add(ns(u) =/=  ns(v)) // =/= は等号否定を意味する．
    }

    dump("csp/demo-gcp2.csp")
    init
  }

}
