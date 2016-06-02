package controllers

import java.io.File

import controllers.action.SecuredFactory
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.ws.WSResponse
import services.WordService

import org.mockito.Mockito._

import scala.concurrent.{ExecutionContext, Future}

class SpeechControllerSpec extends FlatSpec with Matchers with MockitoSugar {
  val wordService = mock[WordService]
  val securedFactory = mock[SecuredFactory]
  implicit val context: ExecutionContext = mock[ExecutionContext]
  val speechController = new SpeechController(wordService, securedFactory, context)

  ignore should "give back the text related to the speech" in {
    val f = new File("")
    val r = mock[WSResponse]

    when(r.status) thenReturn play.api.http.Status.OK
    when(r.body) thenReturn "{}"

    val response = Future.successful(r)

    when(wordService.saveWord(f)) thenReturn response
    //(speechService.speechToText _).expects(*).returning(Future(response))
    speechController.recognize shouldBe response
  }

}
