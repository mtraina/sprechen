package persistence

import org.specs2.concurrent.{ExecutionEnv => EE}
import play.api.test.{PlaySpecification, WithApplication}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

class SpeechDaoSpec extends PlaySpecification {

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      implicit val context = app.injector.instanceOf[ExecutionContext]
      val speechDao = app.injector.instanceOf[SpeechDao]
      //speechDao must be (null)
      //val result = speechDao.create(Speech(transcript = "cheers2"))

      //speechDao.find().map(s => s.length) must beEqualTo(1).awaitFor(2 seconds)
      val timeout = 5 seconds
      val result = Await.result(speechDao.find(), timeout)

      result.length must beEqualTo(3)
    }
  }

}
