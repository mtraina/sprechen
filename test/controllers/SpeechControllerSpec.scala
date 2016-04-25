package controllers

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import services.SpeechService

class SpeechControllerSpec extends FlatSpec with Matchers with MockFactory {
  val speechService = mock[SpeechService]
  val speechController = new SpeechController(speechService)

  "A speech controller" should "give back the text related to the speech" in {
    (speechService.speechToText _).expects().returning("text")
    speechController.recognize() shouldBe "text"
  }

}
