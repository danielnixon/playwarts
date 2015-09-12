package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object LangObject extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val langObject = rootMirror.staticModule("play.api.i18n.Lang")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, _) if tpt.tpe.contains(langObject) => {
            u.error(tree.pos, "play.api.i18n.Lang is disabled - use play.api.i18n.Langs instead")
            super.traverse(tree)
          }
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
