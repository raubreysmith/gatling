package recorder

import java.nio.file.Path

import io.gatling.recorder.GatlingRecorder
import io.gatling.recorder.config.RecorderPropertiesBuilder
import io.gatling.commons.util.PathHelper._

object Recorder extends App {

	val recorderConfigFile: Path = getClass.getClassLoader.getResource("recorder.conf")
	val projectRootDir: Path = recorderConfigFile.ancestor(3)
	val recorderOutputDirectory: Path = projectRootDir / "src" / "test" / "scala"

	val props = new RecorderPropertiesBuilder
	props.simulationOutputFolder(recorderOutputDirectory.toString)

	GatlingRecorder.fromMap(props.build, Some(recorderConfigFile))
}
