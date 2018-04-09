package codes.seanhenry.mockgenerator.generator.templates

import codes.seanhenry.mockgenerator.ast.*
import codes.seanhenry.mockgenerator.generator.MockTransformer

class ClosureProtocolTest : MockGeneratorTestTemplate {

  override fun build(generator: MockTransformer) {
    generator.add(
        Method.Builder("map")
            .parameter("closure") { param ->
              param.type().function {}
            }
            .build(),
        Method.Builder("flatMap")
            .parameter("closure") { param ->
              param.type().function { func -> func.returnType("Void") }
            }
            .build(),
        Method.Builder("filter")
            .parameter("closure") { param ->
              param.type().function { func ->
                func.argument("String").returnType("Bool")
              }
            }
            .build(),
        Method.Builder("multi")
            .parameter("animations") { param ->
              param.type().function { func -> func.argument("Int") }
            }
            .parameter("completion") { param ->
              param.type().function { func -> func.argument("Bool") }
            }
            .build(),
        Method.Builder("optional")
            .parameter("animations") { param ->
              param.type().optional { opt ->
                opt.type().function { it.argument("Int") }
              }
            }
            .parameter("completion") { param ->
              param.type().optional { opt ->
                opt.type().function { it.argument("Bool") }
              }
            }
            .build(),
        Method.Builder("optionalParam")
            .parameter("_", "closure") { param ->
              param.type().function { func ->
                func.argument().optional { it.type("String") }
              }
            }
            .build(),
        Method.Builder("optionalParams")
            .parameter("_", "closure") { param ->
              param.type().function { func ->
                func.argument().optional { it.type("String") }
                    .argument().optional { it.type("Int") }
              }
            }
            .build(),
        Method.Builder("optionalArrayParams")
            .parameter("_", "closure") { param ->
              param.type().function { func ->
                func.argument().optional { it.type().array { it.type("String") } }
                  .argument().array { it.type("UInt") }
              }
            }
            .build(),
        Method.Builder("doNotSuppressWarning1")
            .parameter("_", "closure") { param ->
              param.type().function { }
            }
            .build(),
        Method.Builder("doNotSuppressWarning2")
            .parameter("_", "closure") { param ->
              param.type().function { it.returnType("Void") }
            }
            .build(),
        Method.Builder("doNotSuppressWarning3")
            .parameter("_", "closure") { param ->
              param.type().function { it.returnType("(Void)") }
            }
            .build(),
        Method.Builder("suppressWarning1")
            .parameter("_", "closure") { param ->
              param.type().function { it.returnType("String") }
            }
            .build(),
        Method.Builder("suppressWarning2")
            .parameter("_", "closure") { param ->
              param.type().function { it.returnType("(String)") }
            }
            .build(),
        Method.Builder("suppressWarning3")
            .parameter("_", "closure") { param ->
              param.type().function { func ->
                func.returnType().optional { it.type("String") }
              }
            }
            .build(),
        Method.Builder("suppressWarning4")
            .parameter("_", "closure") { param ->
              param.type().function { func ->
                func.returnType().optional { it.type("String").unwrapped() }
              }
            }
            .build()
    )
  }

  override fun getExpected(): String {
    return """
    var invokedMap = false
    var invokedMapCount = 0
    func map(closure: () -> ()) {
    invokedMap = true
    invokedMapCount += 1
    closure()
    }
    var invokedFlatMap = false
    var invokedFlatMapCount = 0
    func flatMap(closure: () -> Void) {
    invokedFlatMap = true
    invokedFlatMapCount += 1
    closure()
    }
    var invokedFilter = false
    var invokedFilterCount = 0
    var stubbedFilterClosureResult: (String, Void)?
    func filter(closure: (String) -> Bool) {
    invokedFilter = true
    invokedFilterCount += 1
    if let result = stubbedFilterClosureResult {
    _ = closure(result.0)
    }
    }
    var invokedMulti = false
    var invokedMultiCount = 0
    var stubbedMultiAnimationsResult: (Int, Void)?
    var stubbedMultiCompletionResult: (Bool, Void)?
    func multi(animations: (Int) -> (), completion: (Bool) -> ()) {
    invokedMulti = true
    invokedMultiCount += 1
    if let result = stubbedMultiAnimationsResult {
    animations(result.0)
    }
    if let result = stubbedMultiCompletionResult {
    completion(result.0)
    }
    }
    var invokedOptional = false
    var invokedOptionalCount = 0
    var stubbedOptionalAnimationsResult: (Int, Void)?
    var stubbedOptionalCompletionResult: (Bool, Void)?
    func optional(animations: ((Int) -> ())?, completion: ((Bool) -> ())?) {
    invokedOptional = true
    invokedOptionalCount += 1
    if let result = stubbedOptionalAnimationsResult {
    animations?(result.0)
    }
    if let result = stubbedOptionalCompletionResult {
    completion?(result.0)
    }
    }
    var invokedOptionalParam = false
    var invokedOptionalParamCount = 0
    var stubbedOptionalParamClosureResult: (String?, Void)?
    func optionalParam(_ closure: (String?) -> ()) {
    invokedOptionalParam = true
    invokedOptionalParamCount += 1
    if let result = stubbedOptionalParamClosureResult {
    closure(result.0)
    }
    }
    var invokedOptionalParams = false
    var invokedOptionalParamsCount = 0
    var stubbedOptionalParamsClosureResult: (String?, Int?)?
    func optionalParams(_ closure: (String?, Int?) -> ()) {
    invokedOptionalParams = true
    invokedOptionalParamsCount += 1
    if let result = stubbedOptionalParamsClosureResult {
    closure(result.0, result.1)
    }
    }
    var invokedOptionalArrayParams = false
    var invokedOptionalArrayParamsCount = 0
    var stubbedOptionalArrayParamsClosureResult: ([String]?, [UInt])?
    func optionalArrayParams(_ closure: ([String]?, [UInt]) -> ()) {
    invokedOptionalArrayParams = true
    invokedOptionalArrayParamsCount += 1
    if let result = stubbedOptionalArrayParamsClosureResult {
    closure(result.0, result.1)
    }
    }
    var invokedDoNotSuppressWarning1 = false
    var invokedDoNotSuppressWarning1Count = 0
    func doNotSuppressWarning1(_ closure: () -> ()) {
    invokedDoNotSuppressWarning1 = true
    invokedDoNotSuppressWarning1Count += 1
    closure()
    }
    var invokedDoNotSuppressWarning2 = false
    var invokedDoNotSuppressWarning2Count = 0
    func doNotSuppressWarning2(_ closure: () -> Void) {
    invokedDoNotSuppressWarning2 = true
    invokedDoNotSuppressWarning2Count += 1
    closure()
    }
    var invokedDoNotSuppressWarning3 = false
    var invokedDoNotSuppressWarning3Count = 0
    func doNotSuppressWarning3(_ closure: () -> (Void)) {
    invokedDoNotSuppressWarning3 = true
    invokedDoNotSuppressWarning3Count += 1
    closure()
    }
    var invokedSuppressWarning1 = false
    var invokedSuppressWarning1Count = 0
    func suppressWarning1(_ closure: () -> String) {
    invokedSuppressWarning1 = true
    invokedSuppressWarning1Count += 1
    _ = closure()
    }
    var invokedSuppressWarning2 = false
    var invokedSuppressWarning2Count = 0
    func suppressWarning2(_ closure: () -> (String)) {
    invokedSuppressWarning2 = true
    invokedSuppressWarning2Count += 1
    _ = closure()
    }
    var invokedSuppressWarning3 = false
    var invokedSuppressWarning3Count = 0
    func suppressWarning3(_ closure: () -> String?) {
    invokedSuppressWarning3 = true
    invokedSuppressWarning3Count += 1
    _ = closure()
    }
    var invokedSuppressWarning4 = false
    var invokedSuppressWarning4Count = 0
    func suppressWarning4(_ closure: () -> String!) {
    invokedSuppressWarning4 = true
    invokedSuppressWarning4Count += 1
    _ = closure()
    }
    """.trimIndent()
  }
}
