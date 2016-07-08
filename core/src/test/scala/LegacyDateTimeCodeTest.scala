import java.util.Date

import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.LegacyDateTimeCode
import org.scalatest.FunSuite

class LegacyDateTimeCodeTest extends FunSuite {
  test("can't use java.util.Date") {
    val result = WartTestTraverser(LegacyDateTimeCode) {
      val foo = new java.util.Date
    }
    assertResult(List("java.util.Date is disabled - use java.time.* instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use java.util.Calendar") {
    val result = WartTestTraverser(LegacyDateTimeCode) {
      val foo = new java.util.Calendar {
        override def getGreatestMinimum(field: Int): Int = ???

        override def getMaximum(field: Int): Int = ???

        override def computeFields(): Unit = ???

        override def roll(field: Int, up: Boolean): Unit = ???

        override def getLeastMaximum(field: Int): Int = ???

        override def add(field: Int, amount: Int): Unit = ???

        override def getMinimum(field: Int): Int = ???

        override def computeTime(): Unit = ???
      }
    }
    assertResult(List("java.util.Calendar is disabled - use java.time.* instead", "java.util.Calendar is disabled - use java.time.* instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use java.util.GregorianCalendar") {
    val result = WartTestTraverser(LegacyDateTimeCode) {
      val foo = new java.util.GregorianCalendar
    }
    assertResult(List("java.util.Calendar is disabled - use java.time.* instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use java.util.TimeZone") {
    val result = WartTestTraverser(LegacyDateTimeCode) {
      val foo = new java.util.TimeZone {
        override def setRawOffset(offsetMillis: Int): Unit = ???

        override def getOffset(era: Int, year: Int, month: Int, day: Int, dayOfWeek: Int, milliseconds: Int): Int = ???

        override def useDaylightTime(): Boolean = ???

        override def getRawOffset: Int = ???

        override def inDaylightTime(date: Date): Boolean = ???
      }
    }
    assertResult(List("java.util.TimeZone is disabled - use java.time.* instead", "java.util.TimeZone is disabled - use java.time.* instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
