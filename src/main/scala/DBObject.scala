package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object DBObject extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val dbObject = rootMirror.staticModule("play.api.db.DB")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, _) if tpt.tpe.contains(dbObject) => {
            u.error(tree.pos, "play.api.db.DB is disabled - use DBApi or Database instead")
            super.traverse(tree)
          }
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
