package codes.seanhenry.mockgenerator.ast

import codes.seanhenry.mockgenerator.visitor.Visitor

class BracketType(val type: TypeIdentifier) : TypeIdentifier("") {

  override var text: String
    set(_) {}
    get() { return "(${type.text})" }

  override fun accept(visitor: Visitor) {
    visitor.visitBracketType(this)
  }
}
