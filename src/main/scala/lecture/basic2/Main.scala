package lecture.basic2

object Main {

    def main(args: Array[String]) = {
        val example1 = Seq(1,2,3)
        val example2 = Seq(100,80,200,40,500)
        println(s"$example1 に対する last の結果は ${last(example1)}")
        println(s"$example2 に対する last の結果は ${last(example2)}")
        println(s"($example1,2) に対する sumFirstN の結果は ${sumFirstN(example1,2)}")
        println(s"($example2,3) に対する sumFirstN の結果は ${sumFirstN(example2,3)}")
    }

    /**
      * seq の最後の要素を返すメソッドを実装してください (要素数が0の場合は考慮しなくてよい) 
      * ??? を消して last メソッドを実装してください
      * @param seq
      * @return
      */
    def last(seq: Seq[Int]): Int = ???

    /**
      * seq の先頭から n 個の要素の和を返す sumFirstN を実装してください (要素数が0の場合は考慮しなくてよい)
      * ??? を消して last メソッドを実装してください
      * @param seq
      * @param n
      * @return
      */
    def sumFirstN(seq: Seq[Int], n: Int): Int = ???

}