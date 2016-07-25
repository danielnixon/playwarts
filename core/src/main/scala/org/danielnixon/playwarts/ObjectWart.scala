package org.danielnixon.playwarts

import org.wartremover.{ WartTraverser, WartUniverse }

abstract class ObjectWart(targetObjectName: String, errorMessage: String) extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val symbol = rootMirror.staticModule(targetObjectName)

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, _) if tpt.tpe.contains(symbol) =>
            u.error(tree.pos, errorMessage)
            super.traverse(tree)
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
