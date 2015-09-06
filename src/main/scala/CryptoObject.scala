package org.danielnixon.playwarts

import org.brianmckenna.wartremover.{WartTraverser, WartUniverse}

object CryptoObject extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val cryptoObject = rootMirror.staticModule("play.api.libs.Crypto")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          // Ignore trees marked by SuppressWarnings
          case t if hasWartAnnotation(u)(t) =>
          case Select(tpt, _) if tpt.tpe.contains(cryptoObject) => {
            u.error(tree.pos, "play.api.libs.Crypto object is disabled - use play.api.libs.Crypto class instead")
            super.traverse(tree)
          }
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
