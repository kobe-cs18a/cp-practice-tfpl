package lecture

import org.scalatest.Reporter
import org.scalatest.events._
import Console._

class TfplReporter extends Reporter {
  // https://qiita.com/NomadBlacky/items/335cc84d0da978240b60

  var totalScore = 0
  var fullScore = 0
  var table = IndexedSeq.empty[Tuple2[String,String]]
  val c1width = 50
  val c2width = 3
  val c3width = 3

  private def alignR(str: String, width: Int) = {
    val strWidth = str.map{ s =>
      if (s.toString.getBytes(java.nio.charset.Charset.forName("UTF-8")).length > 1) 2 else 1
    }.sum
    s"${Seq.fill(width - strWidth)(" ").mkString}$str"
  }

  private def alignL(str: String, width: Int) = {
    val strWidth = str.map{ s =>
      if (s.toString.getBytes(java.nio.charset.Charset.forName("UTF-8")).length > 1) 2 else 1
    }.sum
    s"$str${Seq.fill(width - strWidth)(" ").mkString}"
  }

  override def apply(event: Event): Unit = event match {
    case TestSucceeded(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, rerunner, payload, threadName, timeStamp) => {
      val name = testName.split(':').head.trim
      val score = if (testName.contains(':')) testName.split(':').last.trim.toInt else 1
      totalScore += score
      fullScore += score
      table = table :+ (s"${suiteName}",s"| ${alignL(s"${name}", c1width)} | ${alignR(score.toString, c2width)} |" + GREEN + " OK " + RESET + s"| ${alignR(score.toString, c2width)} |")
      // println(s"OK: ${suiteName} ${testName}")
    }
    case TestFailed(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, analysis, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) => {
      val name = testName.split(':').head.trim
      val score = if (testName.contains(':')) testName.split(':').last.trim.toInt else 1 // testName.split(':').last.trim.toInt
      fullScore += score
      table = table :+ (s"${suiteName}",s"| ${alignL(s"${name}", c1width)} | ${alignR(score.toString, c2width)} |" + RED + " NG " + RESET + s"| ${alignR("0", c2width)} |")
    }
    case RunCompleted(ordinal, duration, summary, formatter, location, payload, threadName, timeStamp) => {

      var previousSuite = ""
      table.sorted.foreach{line =>
        if (previousSuite != line._1) {
          println("|" + Seq.fill(c1width+c2width+c3width+13)("-").mkString + "|")
          println(s"| ${line._1}")
          previousSuite = line._1
        }
        println(line._2)
      }
      println("|" + Seq.fill(c1width+c2width+c3width+13)("-").mkString + "|")
      println(s"| ${alignL(s"Total", c1width)} | ${alignR(fullScore.toString, 3)} |    | ${alignR(totalScore.toString, 3)} |")
    }
    case _ =>
  }
  /*     case _: RecordableEvent =>
  case _: ExceptionalEvent =>
  case _: NotificationEvent =>
  case TestStarting(ordinal, suiteName, suiteId, suiteClassName, testName, testText, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case TestFailed(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, analysis, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case TestIgnored(ordinal, suiteName, suiteId, suiteClassName, testName, testText, formatter, location, payload, threadName, timeStamp) =>
  case TestPending(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, payload, threadName, timeStamp) =>
  case TestCanceled(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case SuiteStarting(ordinal, suiteName, suiteId, suiteClassName, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case SuiteCompleted(ordinal, suiteName, suiteId, suiteClassName, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case SuiteAborted(ordinal, message, suiteName, suiteId, suiteClassName, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
  case RunStarting(ordinal, testCount, configMap, formatter, location, payload, threadName, timeStamp) =>
  case RunStopped(ordinal, duration, summary, formatter, location, payload, threadName, timeStamp) =>
  case RunAborted(ordinal, message, throwable, duration, summary, formatter, location, payload, threadName, timeStamp) =>
  case InfoProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
  case AlertProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
  case NoteProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
  case MarkupProvided(ordinal, text, nameInfo, formatter, location, payload, threadName, timeStamp) =>
  case ScopeOpened(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
  case ScopeClosed(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
  case ScopePending(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
  case DiscoveryStarting(ordinal, configMap, threadName, timeStamp) =>
  case DiscoveryCompleted(ordinal, duration, threadName, timeStamp) =>
*/
}