package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object JsLookupResultPartial extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val jsLookupResultSymbol = rootMirror.staticClass("play.api.libs.json.JsLookupResult")
    val GetName = TermName("get")
    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, GetName) if left.tpe.baseType(jsLookupResultSymbol) != NoType =>
            u.error(tree.pos, "JsLookupResult#get is disabled - use JsLookupResult#getOrElse instead")
          case LabelDef(_, _, rhs) if isSynthetic(u)(tree) =>
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
