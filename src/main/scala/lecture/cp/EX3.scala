package lecture.cp

import jp.kobe_u.copris._, dsl._

import kobe_cs18a.tfpl._


object p501_LifeGame {

  def main(args: Array[String]) = {

    val ins =
      Seq(
        Seq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        Seq(0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0),
        Seq(0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0),
        Seq(0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0),
        Seq(0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0),
        Seq(0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0),
        Seq(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      )

    makeCsp(args(0).toInt, ins)
  }

  def makeCsp(n: Int, lastStates: Seq[Seq[Int]]) = {
    init

    val rowsize = lastStates.size
    val colsize = lastStates.head.size

    val xs: Seq[Seq[Seq[Var]]] =
      for (i <- 0 until rowsize) yield
        for (j <- 0 until colsize) yield
          for (k <- 0 until n) yield {
            // println(s"x_${i}_${j}_${k}")
            int(Var(s"x_${i}_${j}_${k}"), 0, 1)
          }

    /* ライフゲームの制約 */
    ???

    /* 盤面の端の制約 */
    ???

    /* 最後の状態の制約 */
    ???

    dump(EX2.getName(this, Seq(n)))
    init
  }

}

