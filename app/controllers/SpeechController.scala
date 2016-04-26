package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.SpeechService

class SpeechController @Inject()(speechService: SpeechService) extends Controller {

  def recognize = Action {
    Ok(speechService.speechToText())
  }
}
