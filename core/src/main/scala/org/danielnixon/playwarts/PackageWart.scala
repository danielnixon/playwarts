package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

import scala.util.control.Exception.catching

abstract class PackageWart(targetPackage: String, errorMessage: String, packages: Set[String], classes: Set[String]) extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val packageSymbols = packages flatMap { p =>
      catching(classOf[ScalaReflectionException]) opt {
        rootMirror.staticPackage(s"$targetPackage.$p")
      }
    }
    val classSymbols = classes.map(c => rootMirror.staticClass(s"$targetPackage.$c"))
    val objectSymbols = classes.map(c => rootMirror.staticModule(s"$targetPackage.$c"))

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(left, _) if packageSymbols.exists(left.tpe.contains) && left.tpe.termSymbol.isPackage =>
            u.error(tree.pos, errorMessage)
            super.traverse(tree)
          case Select(left, _) if classSymbols.exists(left.tpe.baseType(_) != NoType) =>
            u.error(tree.pos, errorMessage)
          case Select(left, _) if objectSymbols.exists(left.tpe.contains) =>
            u.error(tree.pos, errorMessage)
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
