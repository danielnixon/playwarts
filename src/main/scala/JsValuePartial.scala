package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{ WartTraverser, WartUniverse }

object JsValuePartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val formSymbol = rootMirror.staticClass("play.api.libs.json.JsValue")
    val GetName = TermName("as")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, GetName) if left.tpe.baseType(formSymbol) != NoType =>
            u.error(tree.pos, "JsValue#as is disabled - use JsValue#asOpt instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ =>
            super.traverse(tree)
        }
      }
    }
  }
}
