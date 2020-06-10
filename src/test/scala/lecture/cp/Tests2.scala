package lecture.cp

import org.scalatest.funsuite.AnyFunSuite
import jp.kobe_u.copris._
import kobe_cs18a.tfpl.Sudoku

abstract class Base extends AnyFunSuite {

  def varsAreCorrect(cspStu: CSP, cspTec: CSP): Boolean = kobe_cs18a.tfpl.CP.checkVars(cspStu, cspTec)

  def constraintsAreCorrect(cspStu: CSP, cspTec: CSP, numSol: Int): Boolean = kobe_cs18a.tfpl.CP.checkCons(cspStu, cspTec, numSol)._1

  def constraintsAreCorrectSai(cspStu: CSP, cspTec: CSP): Boolean = kobe_cs18a.tfpl.CP.checkSai(cspStu, cspTec)._1

  def gcpParFile(id: Int, fileName: String, numSol: Int, cspTec: CSP) = {

    val cspStu = CSP()
    new jp.kobe_u.copris.loader.SugarLoader(cspStu).load(fileName)

    test(s"${id}-a) ${fileName} がある: 0") {
      assert(new java.io.File(s"${fileName}").exists())
    }

    test(s"${id}-b) 変数が正しく定義されている: 0") {
      assert(varsAreCorrect(cspStu, cspTec))
    }

    test(s"${id}-c) 制約が正しく定義されている: 1") {
      assert(constraintsAreCorrect(cspStu, cspTec, numSol))
    }
  }

  def ParFileSai(id: Int, fileName: String, numSol: Int, cspTec: CSP) = {

    val cspStu = CSP()
    new jp.kobe_u.copris.loader.SugarLoader(cspStu).load(fileName)

    test(s"${id}-a) ${fileName} がある: 0") {
      assert(new java.io.File(s"${fileName}").exists())
    }

    test(s"${id}-b) 変数が正しく定義されている: 0") {
      assert(varsAreCorrect(cspStu, cspTec))
    }

    test(s"${id}-c) 制約が正しく定義されている: 1") {
      assert(constraintsAreCorrectSai(cspStu, cspTec))
    }
  }


}

class p201_gcp1 extends Base {

  val testSet = Seq(
    ("myciel3", 3, 20),
    ("myciel3", 4, 20),
    ("myciel4", 4, 20),
    ("myciel4", 5, 20),
  )

  var cnt = 0
  for ((iname, ncol, nsol) <- testSet) {
    cnt += 1
    val fileName: String = lecture.cp.EX2.getName(this, Seq(iname, ncol))
    val cspTec = (new kobe_cs18a.tfpl.p201_gcp1(s"data/${iname}.col", ncol)).getCSP()
    gcpParFile(cnt, fileName, nsol, cspTec)
  }

}

class p202_gcp2 extends Base {

  val testSet = Seq(
    ("myciel3", 6, 20),
    ("myciel3", 7, 20),
    ("myciel4", 8, 20),
    ("myciel4", 9, 20),
  )

  var cnt = 0
  for ((iname, ncol, nsol) <- testSet) {
    cnt += 1
    val fileName: String = lecture.cp.EX2.getName(this, Seq(iname, ncol))
    val cspTec = (new kobe_cs18a.tfpl.p202_gcp2(s"data/${iname}.col", ncol)).getCSP()
    gcpParFile(cnt, fileName, nsol, cspTec)
  }

}

class p203_gcp3 extends Base {

  val testSet = Seq(
    ("GEOM20", 20, 20),
    ("GEOM20", 21, 20),
    ("GEOM20a", 19, 20),
    ("GEOM20a", 20, 20),
  )

  var cnt = 0
  for ((iname, ncol, nsol) <- testSet) {
    cnt += 1
    val fileName: String = lecture.cp.EX2.getName(this, Seq(iname, ncol))
    val cspTec = (new kobe_cs18a.tfpl.p203_gcp3(s"data/${iname}.col", ncol)).getCSP()
    gcpParFile(cnt, fileName, nsol, cspTec)
  }

}

class p301_sudoku extends Base {

  val testSet = Seq(
    ("SUDOKU-LV7-149", 0),
    ("SUDOKU-LV7-150", 0)
  )

  var cnt = 0
  for ((iname, nsol) <- testSet) {
    cnt += 1
    val fileName: String = lecture.cp.EX2.getName(this, Seq(iname))
    val mondai = Sudoku.readFromFile(s"data/${iname}.csv")
    val cspTec = (new kobe_cs18a.tfpl.p301_sudoku(mondai)).getCSP()
    gcpParFile(cnt, fileName, nsol, cspTec)
  }

}

class p302_mahoujin_3 extends Base {

  var cnt = 0
  cnt += 1
  val fileName: String = EX2.getName(this, Seq.empty)
  val cspTec = (new kobe_cs18a.tfpl.p312_mahoujin_n(3)).getCSP()
  gcpParFile(cnt, fileName, 20, cspTec)

}

class p312_mahoujin_n extends Base {

  var cnt = 0
  for (n <- Seq(4,5,6)) {
    cnt += 1
    val fileName: String = EX2.getName(this, Seq(n))
    val cspTec = (new kobe_cs18a.tfpl.p312_mahoujin_n(n)).getCSP()
    gcpParFile(cnt, fileName, 5, cspTec)
  }

}

class p303_4queen extends Base {

  var cnt = 0
  for (n <- Seq(4)) {
    cnt += 1
    val fileName: String = EX2.getName(this, Seq())
    val cspTec = (new kobe_cs18a.tfpl.p313_nqueen(n)).getCSP()
    gcpParFile(cnt, fileName, 20, cspTec)
  }

}

class p313_nqueen extends Base {

  var cnt = 0
  for (n <- Seq(5,6,7)) {
    cnt += 1
    val fileName: String = EX2.getName(this, Seq(n))
    val cspTec = (new kobe_cs18a.tfpl.p313_nqueen(n)).getCSP()
    gcpParFile(cnt, fileName, 20, cspTec)
  }

}

class p401_saizeriya_pfix extends Base {

  val testSet = Seq(
    ("saizeriya_1"),
    ("saizeriya_2")
  )

  var cnt = 0
  for ((iname) <- testSet) {
    cnt += 1
    val fileName: String = lecture.cp.EX2.getName(this, Seq(iname))
    val (menu,price,calory) = kobe_cs18a.tfpl.EXS.readFromSaiFile(s"data/${iname}.csv")
    val cspTec = (new kobe_cs18a.tfpl.p401_saizeriya_pfix(menu,price,calory)).getCSP()
    ParFileSai(cnt, fileName, 1, cspTec)
  }



}



