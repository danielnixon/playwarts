package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{ WartTraverser, WartUniverse }

object JsLookupResultOps extends WartTraverser {

  class Op(name: String, error: String) extends WartTraverser {
    override lazy val className = "org.danielnixon.playwarts.JsLookupResultOps"

    def apply(u: WartUniverse): u.Traverser = {
      import u.universe._

      val formSymbol = rootMirror.staticClass("play.api.libs.json.JsLookupResult")
      val Name: TermName = name
      new u.Traverser {
        override def traverse(tree: Tree): Unit = {
          tree match {
            // Ignore trees marked by SuppressWarnings
            case t if hasWartAnnotation(u)(t) =>
            case Select(left, Name) if left.tpe.baseType(formSymbol) != NoType ⇒
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
      new Op("as", "JsLookupResult#as is disabled - use JsLookupResult#asOpt instead"),
      new Op("get", "JsLookupResult#get is disabled - use JsLookupResult#getOrElse instead")
    ))

}
