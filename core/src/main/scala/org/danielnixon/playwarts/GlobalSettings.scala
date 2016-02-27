package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object GlobalSettings extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val globalSettingsSymbol = rootMirror.staticClass("play.api.GlobalSettings")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case t: ImplDef if t.symbol.typeSignature.baseClasses.contains(globalSettingsSymbol) =>
            u.error(tree.pos, "play.api.GlobalSettings is disabled - use dependency injection instead")
          case t => super.traverse(tree)
        }
      }
    }
  }
}
