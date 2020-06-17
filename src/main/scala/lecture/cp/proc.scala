package lecture.cp

import jp.kobe_u.copris._, dsl._

object proc {

  def main(args: Array[String]): Unit = {
    require(args.size == 2)
    args.size match {
      case 2 => {
        if (args(0) == "one")
          proc(args(1))
        else if (args(0) == "all")
          procall(args(1))
        else if (args(0).matches("\\d+"))
          procall(args(1),args(0).toInt)
        else if (args(0) == "opt")
          procOpt(args(1))
        else if (args(0) == "lg")
          procLifeGame(args(1))
      }
    }

  }

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

  def procall(fileName: String, numsol: Int = 0) = {
    init // csp の初期化
    new jp.kobe_u.copris.loader.SugarLoader(csp).load(fileName) // ファイルの読み込み. csp オブジェクトにファイルの
    if (find) {
      println("SATISFIABLE")
      printSolution(solution,1)
    } else {
      println("UNSATISFIABLE")
    }
    var cnt = 1
    if (numsol == 0) {
      while (findNext) {
        cnt += 1
        println()
        printSolution(solution, cnt)
      }
    } else {
      while (cnt < numsol && findNext) {
        cnt += 1
        println()
        printSolution(solution, cnt)
      }
    }
    init
  }

  // gitpod /workspace/cp-practice-tfpl $ which clasp
  // /home/linuxbrew/.linuxbrew/bin/clasp

  def procOpt(fileName: String) = {
    init // csp の初期化

    new jp.kobe_u.copris.loader.SugarLoader(csp).load(fileName) // ファイルの読み込み. csp オブジェクトにファイルの
    use(new pb.Solver(csp, new pb.PbSolver("clasp"), "binary"))

    findOpt

    for (key <- solution.intValues.keys if solution.intValues(key) > 0)
      println(s"$key is selected")

    init
  }

  def procLifeGame(fileName: String) = {
    init // csp の初期化

    new jp.kobe_u.copris.loader.SugarLoader(csp).load(fileName) // ファイルの読み込み. csp オブジェクトにファイルの

    if (find) {
      val sol = Some(solution)
      val rows = (0 until 20).filter(i => solution.intValues.contains(Var(s"x_${i}_${0}_0")))
      val cols = (0 until 20).filter(i => solution.intValues.contains(Var(s"x_${0}_${i}_0")))
      val ks = (0 until 20).filter(i => solution.intValues.contains(Var(s"x_${0}_${0}_${i}")))

      for (k <- ks.sorted) {
        println("==================")
        println(s"Generation $k")
        println("==================")
        for (row <- rows.sorted) {
          println(cols.sorted.map(i => solution.intValues(Var(s"x_${row}_${i}_${k}"))).map(j => if (j == 1) "O" else "_").mkString(""))
        }
      }
      println("SAT")
      init
      //(true,sol)
    } else {
      println("UNSAT")
      init
      //(false,None)
    }

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
