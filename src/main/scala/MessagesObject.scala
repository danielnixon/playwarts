package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object MessagesObject extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val messagesObject = rootMirror.staticModule("play.api.i18n.Messages")
    val ImplicitsName = TermName("Implicits")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, ImplicitsName) if tpt.tpe.contains(messagesObject) => {
            u.error(tree.pos, "play.api.i18n.Messages.Implicits is disabled - use dependency injection instead")
            super.traverse(tree)
          }
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
