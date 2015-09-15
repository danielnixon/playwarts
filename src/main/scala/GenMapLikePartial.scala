package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object GenMapLikePartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val mapSymbol = rootMirror.staticClass("scala.collection.GenMapLike")
    val ApplyName = TermName("apply")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, ApplyName) if left.tpe.baseType(mapSymbol) != NoType =>
            u.error(tree.pos, "GenMapLike#apply is disabled - use GenMapLike#get instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ => super.traverse(tree)
        }
      }
    }
  }
}