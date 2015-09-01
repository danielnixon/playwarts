package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{ WartTraverser, WartUniverse }

object FormPartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val formSymbol = rootMirror.staticClass("play.api.data.Form")
    val GetName = TermName("get")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, GetName) if left.tpe.baseType(formSymbol) != NoType =>
            u.error(tree.pos, "Form#get is disabled - use Form#fold instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ =>
            super.traverse(tree)
        }
      }
    }
  }
}
