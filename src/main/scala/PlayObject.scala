package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object PlayObject extends WartTraverser {
  class Op(name: String, error: String) extends WartTraverser {
    override lazy val className = "org.danielnixon.playwarts.PlayObject"

    def apply(u: WartUniverse): u.Traverser = {
      import u.universe._

      val playObject = rootMirror.staticModule("play.api.Play")
      val Name: TermName = name
      new u.Traverser {
        override def traverse(tree: Tree): Unit = {
          tree match {
            // Ignore trees marked by SuppressWarnings
            case t if hasWartAnnotation(u)(t) =>
            case Select(tpt, Name) if tpt.tpe.contains(playObject) => u.error(tree.pos, error)
            case _ => super.traverse(tree)
          }
        }
      }
    }
  }

  def apply(u: WartUniverse): u.Traverser =
    WartTraverser.sumList(u)(List(
      new Op("current", "Play#current is disabled - declare a dependency on Application instead"),
      new Op("maybeApplication", "Play#maybeApplication is disabled - declare a dependency on Application instead"),
      new Op("unsafeApplication", "Play#unsafeApplication is disabled - declare a dependency on Application instead")
    ))

}
