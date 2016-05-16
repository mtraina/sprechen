package persistence

import models.Speech
import play.api.test.{WithApplication, PlaySpecification}

import scala.concurrent.ExecutionContext

class SpeechDaoSpec extends PlaySpecification {

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      implicit val context = app.injector.instanceOf[ExecutionContext]
      val speechDao = app.injector.instanceOf[SpeechDao]
      //speechDao must be (null)
      //val result = speechDao.create(Speech(transcript = "cheers2"))

      speechDao.find().map {
        speeches => {
          speeches.length must beEqualTo(1)
          //print(speeches)
        }
      }
      //result.onComplete(r => r must be (null))(context)
    }
  }

}
