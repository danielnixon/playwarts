package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

abstract class ClassMultiWart(
  wartClassName: String,
  targetClassName: String,
  methods: List[(String, String)]) extends WartTraverser {

  class Op(name: String, error: String) extends WartTraverser {
    override lazy val className = wartClassName

    def apply(u: WartUniverse): u.Traverser = {
      import u.universe._

      val symbol = rootMirror.staticClass(targetClassName)
      val Name = TermName(name)

      new u.Traverser {
        override def traverse(tree: Tree): Unit = {
          tree match {
            // Ignore trees marked by SuppressWarnings
            case t if hasWartAnnotation(u)(t) =>
            case Select(left, Name) if left.tpe.baseType(symbol) != NoType ⇒
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
    WartTraverser.sumList(u)(methods.map(method => new Op(method._1, method._2)))
}