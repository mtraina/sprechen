package persistence

import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.specs2.specification.BeforeAfterAll

trait EmbeddedMongo extends BeforeAfterAll {

  def embedConnectionPort: Int = 12345
  def embedMongoDBVersion: Version.Main = Version.Main.PRODUCTION

  lazy val network = new Net(embedConnectionPort, Network.localhostIsIPv6)

  lazy val mongodConfig = new MongodConfigBuilder()
    .version(embedMongoDBVersion)
    .net(network)
    .build

  lazy val runtime = MongodStarter.getDefaultInstance

  lazy val mongodExecutable = runtime.prepare(mongodConfig)

  override def beforeAll(): Unit = {
    mongodExecutable.start
  }

  override def afterAll(): Unit = {
    mongodExecutable.stop
  }

}
