package lecture

import jp.kobe_u.copris._
import jp.kobe_u.copris.dsl._

object CP {

  /*
   * ==============================
   *
   * 注意
   *
   * ==============================
   *
   * このファイルは一切編集しないでください．
   *
   */

  private val nameR1 = """([a-zA-Z]+).*?(\d+)""".r
  private val nameR2 = """([a-zA-Z]+).*?(\d+).*?(\d+)""".r
  private def compare(left: String, right: String): Boolean = {
    val thisName = left match {
      case nameR1(name,num) => (name,num.toInt,1)
      case nameR2(name,num1,num2) => (name,num1.toInt,num2.toInt)
      case _ => (left,1,1)
    }
    val thatName = right match {
      case nameR1(name,num) => (name,num.toInt,1)
      case nameR2(name,num1,num2) => (name,num1.toInt,num2.toInt)
      case _ => (right,1,1)
    }

    if (thisName._2 == thatName._2) {
      if (thisName._3 < thatName._3)
        return false
      else if (thisName._3 > thatName._3)
        return true
    }
    if (thisName._2 < thatName._2) return false
    if (thisName._2 > thatName._2) return true
    if (left < right) return false
    if (left > right) return true
    else true
  }


  private def sortSolutionInt(map: scala.collection.Map[Var,Int]) = {
    map.keys.toSeq.sortWith((l,r) => compare(r.name,l.name)).map(v => (v,map(v)))
  }
  private def sortSolutionBool(map: scala.collection.Map[Bool,Boolean]) = {
    map.keys.toSeq.sortWith((l,r) => compare(r.name,l.name)).map(v => (v,map(v)))
  }

  private def printSolution(sol: Solution, n: Int) = {
    println("=====================")
    println(s"Solution $n")
    println("=====================")
    sortSolutionBool(sol.boolValues).map(xv => s"${xv._1} = ${xv._2}").foreach(println)
    sortSolutionInt(sol.intValues).map(xv => s"${xv._1} = ${xv._2}").foreach(println)
  }

  def proc(fileName: String) = {
    init // csp の初期化
    new jp.kobe_u.copris.loader.SugarLoader(csp).load(fileName) // ファイルの読み込み. csp オブジェクトにファイルの
    if (find) {
      println("SATISFIABLE")
      printSolution(solution,1)
    } else {
      println("UNSATISFIABLE")
    }
    init
  }

  def procall(fileName: String) = {
    init // csp の初期化
    new jp.kobe_u.copris.loader.SugarLoader(csp).load(fileName) // ファイルの読み込み. csp オブジェクトにファイルの
    if (find) {
      println("SATISFIABLE")
      printSolution(solution,1)
    } else {
      println("UNSATISFIABLE")
    }
    var cnt = 1
    while (findNext) {
      cnt += 1
      println()
      printSolution(solution, cnt)
    }
    init
  }

  def findSolution(csp0: CSP): (Boolean,Option[Solution]) = {
    init
    use(new sugar.Solver(csp0))
    val res = if (find) {
      (true,Some(solution))
    } else {
      (false,None)
    }
    init
    res
  }

  def enumerateSolutions(csp0: CSP, num: Int = 0): (Boolean,Option[Seq[Solution]]) = {
    init
    use(new sugar.Solver(csp0))
    var ss = Seq.empty[Solution]

    if (find)
      ss = Seq(solution)
    else
      return (false,None)

    while ((num == 0 || num > ss.size) && findNext)
      ss = ss :+ solution
    init
    (true,Some(ss))
  }

}
