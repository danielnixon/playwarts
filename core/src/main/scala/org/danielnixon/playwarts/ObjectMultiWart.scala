package org.danielnixon.playwarts

import org.wartremover.{ WartTraverser, WartUniverse }

abstract class ObjectMultiWart(
    wartClassName: String,
    targetObjectName: String,
    methods: List[(String, String)]
) extends WartTraverser {

  class Op(name: String, error: String) extends WartTraverser {
    override lazy val className: String = wartClassName

    def apply(u: WartUniverse): u.Traverser = {
      import u.universe._

      val symbol = rootMirror.staticModule(targetObjectName)
      val Name = TermName(name)

      new u.Traverser {
        override def traverse(tree: Tree): Unit = {
          tree match {
            // Ignore trees marked by SuppressWarnings
            case t if hasWartAnnotation(u)(t) =>
            case Select(tpt, Name) if tpt.tpe.contains(symbol) => error(u)(tree.pos, error)
            case _ => super.traverse(tree)
          }
        }
      }
    }
  }

  def apply(u: WartUniverse): u.Traverser =
    WartTraverser.sumList(u)(methods.map(method => new Op(method._1, method._2)))
}
