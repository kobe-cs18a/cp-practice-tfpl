package lecture

import org.scalatest.funsuite.AnyFunSuite
import jp.kobe_u.copris._

abstract class Template extends AnyFunSuite {
  val baseDir = "csp/"
  def fileName = s"$baseDir${this.getClass.getName.split('.').last}.csp"
  var fileScore: Int = 0
  var varScore: Int = 0
  var conScore: Int = 1

  /* 表示する文字 */
  def fileExist(score: Int) = s"a) ${fileName} がある: $score"
  def varsCorrect(score: Int) = s"b) 変数が正しく定義されている: $score"
  def consCorrect(score: Int) = s"c) 制約が正しく定義されている: $score"

  /* テスト */

  val cspStu = CSP()
  new jp.kobe_u.copris.loader.SugarLoader(cspStu).load(fileName)

  def cspTec: CSP
  def varsAreCorrect(cspStu: CSP, cspTec: CSP): Boolean = kobe_cs18a.tfpl.CP.checkVars(cspStu,cspTec)
  def constraintsAreCorrect(cspStu: CSP, cspTec: CSP): Boolean = kobe_cs18a.tfpl.CP.checkCons(cspStu,cspTec)._1

  test(fileExist(fileScore)) {
    assert(new java.io.File(s"${fileName}").exists())
  }

  test(varsCorrect(varScore)) {
    assert(varsAreCorrect(cspStu,cspTec))
  }

  test(consCorrect(conScore)) {
    assert(constraintsAreCorrect(cspStu,cspTec))
  }


}

