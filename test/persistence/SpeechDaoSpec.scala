package persistence

import com.github.athieriot.EmbedConnection
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.{Net, MongodConfigBuilder}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.specs2.specification.BeforeAfterAll
import play.api.test.{PlaySpecification, WithApplication}
import reactivemongo.api.MongoDriver

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class SpeechDaoSpec extends PlaySpecification with BeforeAfterAll {

  override def beforeAll(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    //Override this method to personalize testing port
    def embedConnectionPort: Int = 12345

    //Override this method to personalize MongoDB version
    def embedMongoDBVersion: Version.Main = Version.Main.PRODUCTION
    lazy val network = new Net(embedConnectionPort, Network.localhostIsIPv6)

    lazy val mongodConfig = new MongodConfigBuilder()
      .version(embedMongoDBVersion)
      .net(network)
      .build
    lazy val runtime = MongodStarter.getDefaultInstance
    lazy val mongodExecutable = runtime.prepare(mongodConfig)
    mongodExecutable.start
  }

  override def afterAll(): Unit = {
    //mongodExecutable.start
  }

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      implicit val context = app.injector.instanceOf[ExecutionContext]
      val speechDao = app.injector.instanceOf[SpeechDao]
      val timeout = 5 seconds
      val result = Await.result(speechDao.find(), timeout)

      result.length must beEqualTo(3)
    }
  }


}
