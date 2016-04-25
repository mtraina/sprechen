package controllers

import javax.inject.Inject

import play.api.mvc.Controller
import services.SpeechService

class SpeechController @Inject()(speechService: SpeechService) extends Controller {
  def recognize() = {
    speechService.speechToText()
  }
}
