package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scenarios.SampleScenario1

class SampleSimulation1 extends Simulation {

  val httpConf = http
    .baseURL("https://www.linkedin.com/in/rossaubreysmith/")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  val scn = scenario("Sample scenario 1")
    .exec(SampleScenario1.flowA, SampleScenario1.flowB);

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpConf)
}
