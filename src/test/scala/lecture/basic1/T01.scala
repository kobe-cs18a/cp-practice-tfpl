package lecture.basic1

import org.scalatest.FunSuite
import org.scalatest.Reporter

class T01 extends FunSuite {
    test("最初のテスト") {
        assert(lecture.basic1.Main.firstMethod() == "Hello World!")
    }
}