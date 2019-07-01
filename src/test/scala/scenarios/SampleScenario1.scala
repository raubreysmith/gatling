package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef.http

object SampleScenario1 {

  val flowA = exec(http("Flow A")
    .get("/"))
    .pause(5)

  val flowB = exec(http("Flow B")
    .get("/"))
    .pause(5)
}
