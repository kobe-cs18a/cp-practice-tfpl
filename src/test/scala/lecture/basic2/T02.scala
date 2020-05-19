package lecture.basic2

import org.scalatest.FunSuite
import org.scalatest.Reporter

import kobe_cs18a.tfpl._

class T02 extends FunSuite {
    val es = Seq(
        Seq(1,2,3,4,5),
        Seq(-1, 5, 100,80,200,40,500),
        Seq(5,4,3,2,1,0,-1),
        Seq(9,99,999,9999,99999,999999)
    )

    test("last のテスト") {
        assert(
            es.forall(e => lecture.basic2.Main.last(e) == FirstTest.last(e))
            )
    }

    test("sumFirstN のテスト") {
        assert(
            es.forall(e => lecture.basic2.Main.sumFirstN(e,5) == FirstTest.sumFirstN(e,5))
            )
    }

}