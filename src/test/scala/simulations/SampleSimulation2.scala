package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scenarios.SampleScenario2

import scala.concurrent.duration._

class SampleSimulation2 extends Simulation {

  val httpConf = http
    .baseURL("https://www.linkedin.com/in/rossaubreysmith/")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  val scn = scenario("Sample scenario 2")
    .exec(SampleScenario2.flowA, SampleScenario2.flowB)

  setUp(scn.inject(constantUsersPerSec(1) during(1 seconds)))
    .protocols(httpConf)
}
