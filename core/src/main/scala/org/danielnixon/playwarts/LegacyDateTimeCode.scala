package org.danielnixon.playwarts

import org.wartremover.{ WartTraverser, WartUniverse }

object Calendar extends ClassWart("java.util.Calendar", "java.util.Calendar is disabled - use java.time.* instead")
object Date extends ClassWart("java.util.Date", "java.util.Date is disabled - use java.time.* instead")
object TimeZone extends ClassWart("java.util.TimeZone", "java.util.TimeZone is disabled - use java.time.* instead")

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object LegacyDateTimeCode extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser =
    WartTraverser.sumList(u)(List(Calendar, Date, TimeZone))
}

