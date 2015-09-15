package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{ WartTraverser, WartUniverse }

object GenTraversableLikeOps extends WartTraverser {

  class Op(name: String, error: String) extends WartTraverser {
    override lazy val className = "org.danielnixon.playwarts.GenTraversableLikeOps"

    def apply(u: WartUniverse): u.Traverser = {
      import u.universe._

      val genTraversableLikeSymbol = rootMirror.staticClass("scala.collection.GenTraversableLike")
      val Name = TermName(name)
      new u.Traverser {
        override def traverse(tree: Tree): Unit = {
          tree match {
            // Ignore trees marked by SuppressWarnings
            case t if hasWartAnnotation(u)(t) =>
            case Select(left, Name) if left.tpe.baseType(genTraversableLikeSymbol) != NoType ⇒
              u.error(tree.pos, error)
            // TODO: This ignores a lot
            case LabelDef(_, _, rhs) if isSynthetic(u)(tree) ⇒
            case _ ⇒
              super.traverse(tree)
          }
        }
      }
    }
  }

  def apply(u: WartUniverse): u.Traverser =
    WartTraverser.sumList(u)(List(
      new Op("head", "GenTraversableLike#head is disabled - use GenTraversableLike#headOption instead"),
      new Op("tail", "GenTraversableLike#tail is disabled - use GenTraversableLike#drop(1) instead"),
      new Op("init", "GenTraversableLike#init is disabled - use GenTraversableLike#dropRight(1) instead"),
      new Op("last", "GenTraversableLike#last is disabled - use GenTraversableLike#lastOption instead")
    ))

}
