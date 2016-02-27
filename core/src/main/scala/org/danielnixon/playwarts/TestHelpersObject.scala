package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object TestHelpersObject extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val symbol = rootMirror.staticModule("play.api.test.Helpers")
    val applicationSymbol = rootMirror.staticClass("play.api.Application")
    val Name = TermName("route")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {

        def acceptsApplicationParam: Boolean = {
          tree.tpe.paramLists.flatten.exists(_.typeSignature.baseType(applicationSymbol) != NoType)
        }

        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, Name) if tpt.tpe.contains(symbol) && !acceptsApplicationParam =>
            u.error(tree.pos, "Use a variant of play.api.test.Helpers#route that accepts a play.api.Application parameter.")
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
