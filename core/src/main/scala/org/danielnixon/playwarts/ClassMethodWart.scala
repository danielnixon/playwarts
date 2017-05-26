package org.danielnixon.playwarts

import org.wartremover.{ WartTraverser, WartUniverse }

abstract class ClassMethodWart(targetClassName: String, termName: String, errorMessage: String) extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val symbol = rootMirror.staticClass(targetClassName)
    val Name = TermName(termName)

    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, Name) if left.tpe.baseType(symbol) != NoType =>
            error(u)(tree.pos, errorMessage)
          case LabelDef(_, _, _) if isSynthetic(u)(tree) =>
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
