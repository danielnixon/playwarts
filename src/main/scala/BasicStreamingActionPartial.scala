package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object BasicStreamingActionPartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val basicStreamingActionSymbol = rootMirror.staticClass("slick.profile.BasicStreamingAction")
    val HeadName = TermName("head")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, HeadName) if left.tpe.baseType(basicStreamingActionSymbol) != NoType =>
            u.error(tree.pos, "BasicStreamingAction#head is disabled - use BasicStreamingAction#headOption instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
