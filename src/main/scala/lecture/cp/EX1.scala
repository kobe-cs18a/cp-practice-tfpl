package lecture.cp

import jp.kobe_u.copris._, dsl._

object p011_and {
  def main(args:Array[String]) = {
    init
    val p = bool(Bool("p"))
    val q = bool(Bool("q"))
    // add(p && q)
    dump("csp/p011_and.csp")
    init
  }
}

object p111_ae1 {
  def main(args: Array[String]) = {
    init
    val x = int(Var("x"), 1, 3)
    val y = int(Var("y"), 1, 3)
    // add(x + y === 3)
    dump("csp/p111_ae1.csp")
    init
  }
}

object p012_or {
  def main(args: Array[String]) = {
    init
    val p = bool(Bool("p"))
    val q = bool(Bool("q"))

    /* 制約を記述. ??? は消してください  */
    ???
    dump("csp/p012_or.csp")
    init
  }
}

object p013_xor {
  def main(args: Array[String]) = {
    init
    val p = bool(Bool("p"))
    val q = bool(Bool("q"))

    /* 制約を記述. ??? は消してください  */
    ???

    dump("csp/p013_xor.csp")
    init
  }
}

object p014_imp {
  def main(args: Array[String]) = {
    init
    val p = bool(Bool("p"))
    val q = bool(Bool("q"))

    /* 制約を記述. ??? は消してください  */
    ???

    dump("csp/p014_imp.csp")
    init
  }
}

object p015_iff {
  def main(args: Array[String]) = {
    init
    val p = bool(Bool("p"))
    val q = bool(Bool("q"))

    /* 制約を記述. ??? は消してください  */
    ???

    dump("csp/p015_iff.csp")
    init
  }
}



object p112_ae2 {
  def main(args: Array[String]) = {
    init
    val x = int(Var("x"), 1, 3)
    val y = int(Var("y"), 1, 3)
    val z = int(Var("z"), 1, 3)

    /* 制約を記述. ??? は消してください */
    ???

    dump("csp/p112_ae2.csp")
    init
  }
}

object p113_ai1 {
  def main(args: Array[String]) = {
    init
    val x = int(Var("x"), 1, 3)
    val y = int(Var("y"), 1, 3)
    val z = int(Var("z"), 1, 3)

    /* 制約を記述. ??? は消してください */
    ???

    dump("csp/p113_ai1.csp")
    init
  }
}
