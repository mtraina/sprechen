package services

import java.io.File

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse
import ws.SpeechWSClient

import scala.concurrent.{ExecutionContext, Future}

class SpeechServiceImplSpec extends FlatSpec with Matchers with MockFactory {
  val client = mock[SpeechWSClient]
  implicit val context: ExecutionContext = stub[ExecutionContext]

  val json = Json.obj(
    "results" -> Json.arr(
      Json.obj(
        "transcript" -> "number "
      )
    )
  )
  val r = mock[WSResponse]
  (r.body _).expects().returning(json.toString)
  (r.json _).expects().returning(json)
  val f = Future.successful(r)
  (client.post(_: File)).expects(*).returning(f)

  val speechService = new SpeechServiceImpl(client, context)

  "A speech service" should "return the text related to the speech" in {
    speechService.speechToText shouldBe "text"
  }

}
