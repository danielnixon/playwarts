package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartUniverse, WartTraverser}

object UntypedEquality extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val errorMessage = "Untyped equality is disabled - use a typesafe alternative"
    val EqEqName = TermName("$eq$eq") // ==
    val BangEqName = TermName("$bang$eq") // !=
    val EqName = TermName("eq")
    val NeName = TermName("ne")
    val EqualsName = TermName("equals")

    new u.Traverser {
      override def traverse(tree: Tree) {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case t if isSynthetic(u)(t) =>
          case Select(left, EqEqName) if !isSynthetic(u)(left) => u.error(tree.pos, errorMessage)
          case Select(left, BangEqName) if !isSynthetic(u)(left) => u.error(tree.pos, errorMessage)
          case Select(left, EqName) if !isSynthetic(u)(left) => u.error(tree.pos, errorMessage)
          case Select(left, NeName) if !isSynthetic(u)(left) => u.error(tree.pos, errorMessage)
          case Select(left, EqualsName) if !isSynthetic(u)(left) => u.error(tree.pos, errorMessage)
          case _ => super.traverse(tree)
        }
      }
    }
  }
}

