package persistence

import play.api.test.{PlaySpecification, WithApplication}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class SpeechDaoSpec extends PlaySpecification with EmbeddedMongo {

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      implicit val context = app.injector.instanceOf[ExecutionContext]
      val speechDao = app.injector.instanceOf[SpeechDao]
      val timeout = 5 seconds
      val result = Await.result(speechDao.find(), timeout)

      result.length must beEqualTo(0)
    }
  }


}
