package lecture.cp

import jp.kobe_u.copris._, dsl._

import kobe_cs18a.tfpl._


object test {
  def main(args: Array[String]) = {
    testAlldiff
    testWsum
    // 追加されたテストメソッドを runMain で実行する場合はここに追記する
  }

  def testAlldiff = {
    init

    val xs: Seq[Seq[Var]] =
      for (i <- 0 until 3) yield
        for (j <- 0 until 3) yield int(Var(s"x_${i}_${j}"), 1, 20)

    val vars = xs.flatMap(_.toSeq).map(x => x - 1)

    add(Alldifferent(vars))

    find

    println(solution)

    init
  }

  def testWsum = {
    init

    val x = int(Var(s"x"), 1, 10)
    val y = int(Var(s"y"), 1, 10)
    val z = int(Var(s"z"), 1, 10)

    add(Weightedsum(Seq((3,x),(4,y),(7,z)), "eq", Num(20)))

    find

    println(solution)

    init
  }

  // 適宜テストメソッドを追記してよい
  // def test1 = {
  //
  // }
}

object p201_gcp1 {
  def main(args: Array[String]) = {
    val fileName = args(0)
    val insName = fileName.split('/')(1).split('.')(0)
    val nofColor = args(1).toInt

    val (nodes, edges) = EXS.makeGcpIns(fileName)

    makeCsp(nodes, edges, nofColor, insName)
  }

  def makeCsp(nodes: Seq[Int], edges: Seq[(Int, Int)], nofColor: Int, insName: String) = {
    init

    val ns = for (node <- nodes) yield int(Var("n" + node), 1, nofColor)

    /* 制約を記述する */
    ???

    dump(EX2.getName(this, Seq(insName, nofColor)))
    init
  }

}

object p202_gcp2 {
  def main(args: Array[String]) = {
    val fileName = args(0)
    val insName = fileName.split('/')(1).split('.')(0)
    val nofColor = args(1).toInt

    val (nodes, edges) = EXS.makeGcpIns(fileName)

    makeCsp(nodes, edges, nofColor, insName)
  }

  def makeCsp(nodes: Seq[Int], edges: Seq[(Int, Int)], nofColor: Int, insName: String) = {
    init

    val ns = for (node <- nodes) yield int(Var("n" + node), 1, nofColor)

    /* 制約を記述する */
    ???

    dump(EX2.getName(this, Seq(insName, nofColor)))
    init

  }

}

object p203_gcp3 {
  def main(args: Array[String]) = {
    val fileName = args(0)
    val insName = fileName.split('/')(1).split('.')(0)
    val nofColor = args(1).toInt

    val (nodes, edges) = EXS.makeGcpBwIns(fileName)

    makeCsp(nodes, edges, nofColor, insName)
  }

  def makeCsp(nodes: Seq[Int], edges: Seq[(Int, Int, Int)], nofColor: Int, insName: String) = {
    init

    val ns = for (node <- nodes) yield int(Var("n" + node), 1, nofColor)

    /* 制約を記述する */
    ???

    dump(EX2.getName(this, Seq(insName, nofColor)))
    init
  }

}

object p301_sudoku {
  def main(args: Array[String]) = {
    val fileName = args(0)
    val insName = fileName.split('/')(1).split('.')(0)

    val mondai = Sudoku.readFromFile(fileName)

    makeCsp(insName, mondai)
  }

  def makeCsp(insName: String, mondai: Seq[Seq[Int]]) = {
    init
    val rowsize = mondai.size
    val colsize = mondai.head.size

    val xs =
      for (i <- 0 until rowsize) yield
        for (j <- 0 until colsize) yield int(Var(s"x_${i}_${j}"), 1, 9)

    /* 列の制約 */
    ???

    /* 行の制約 */
    ???

    /* ブロックの制約 */
    ???

    /* ヒント数字の制約 */
    ???

    dump(EX2.getName(this, Seq(insName)))
    init

  }

}

object p302_mahoujin_3 {
  def main(args: Array[String]) = {
    makeCsp()
  }

  def makeCsp() = {
    init

    val n = 3
    val total = n * (n * n + 1) / 2

    val xs: Seq[Seq[Var]] =
      for (i <- 0 until n) yield
        for (j <- 0 until n) yield int(Var(s"x_${i}_${j}"), 1, n * n)


    /* 全部異なる制約 */
    ???

    /* 列の制約 */
    ???

    /* 行の制約 */
    ???

    dump(EX2.getName(this, Seq()))
    init
  }


}

object p312_mahoujin_n {
  def main(args: Array[String]) = {
    makeCsp(args(0).toInt)
  }

  def makeCsp(n: Int) = {
    init

    val total = n * (n * n + 1) / 2

    val xs: Seq[Seq[Var]] =
      for (i <- 0 until n) yield
        for (j <- 0 until n) yield int(Var(s"x_${i}_${j}"), 1, n * n)

    /* 全部異なる制約 */
    ???

    /* 列の制約 */
    ???

    /* 行の制約 */
    ???

    dump(EX2.getName(this, Seq(n)))
    init

  }

}

object p303_4queen {
  def main(args: Array[String]) = {
    makeCsp()
  }

  def makeCsp() = {
    init
    val n = 4
    val qs = for (i <- 0 until n) yield int(Var("q" + i), 0, n - 1)

    /* 列の効きの制約 */
    ???

    /* 斜めの効きの制約 */
    ???

    dump(EX2.getName(this, Seq.empty))
    init
  }

}

object p313_nqueen {

  def main(args: Array[String]) = {
    makeCsp(args(0).toInt)
  }

  def makeCsp(n: Int) = {
    init

    val qs = for (i <- 0 until n) yield int(Var("q" + i), 0, n - 1)

    /* 列の効きの制約 */
    ???

    /* 斜めの効きの制約 */
    ???

    dump(EX2.getName(this, Seq(n)))
    init
  }

}

object p401_saizeriya_pfix {
  def main(args: Array[String]) = {

    val fileName = args(0)
    val insName = fileName.split('/')(1).split('.')(0)

    val (menu, price, calory) = kobe_cs18a.tfpl.EXS.readFromSaiFile(fileName)

    makeCsp(menu, price, calory, insName)
  }

  def makeCsp(menu: Seq[String], price: Seq[Int], calory: Seq[Int], insName: String) = {
    init

    val size = menu.size;

    val xs = for (i <- 0 until size) yield int(Var("no" + menu(i)), 0, 1)

    /* 合計が1000円以下になる制約 */
    ???

    /* 最大化する変数のための制約 */
    val calsum = int(Var("calsum"), 0, calory.sum)

    ???

    /* 最大化することの記述 */
    maximize(calsum)

    dump(EX2.getName(this, Seq(insName)))
    init

  }

}

/* 以下は編集しない */
object EX2 {
  def getName(c: Any, is: Seq[Any]) = {
    val tail = if (is.nonEmpty) is.mkString("_", "_", ".csp") else ".csp"
    s"csp/${c.getClass.getName.split('.').last.replace("$", "")}" + tail
  }
}
