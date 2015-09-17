package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

abstract class ObjectWart(objectName: String, errorMessage: String) extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val dbObject = rootMirror.staticModule(objectName)

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, _) if tpt.tpe.contains(dbObject) => {
            u.error(tree.pos, errorMessage)
            super.traverse(tree)
          }
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
