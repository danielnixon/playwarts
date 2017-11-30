package org.danielnixon.playwarts

import org.wartremover.{ WartTraverser, WartUniverse }

object InjectedController extends WartTraverser {

  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    new u.Traverser {
      override def traverse(tree: Tree): Unit = tree match {
        // Ignore trees marked by SuppressWarnings
        case t if hasWartAnnotation(u)(t) =>

        case ClassDef(_, _, _, Template((parents, _, _))) if parents.exists(x => x.tpe.typeSymbol.fullName == "play.api.mvc.InjectedController") =>
          error(u)(tree.pos, "InjectedController is disabled - use AbstractController instead")
        case _ =>
          super.traverse(tree)
      }
    }
  }
}