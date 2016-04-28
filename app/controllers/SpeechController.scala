package controllers

import javax.inject.{Singleton, Inject}

import play.api.mvc.{Action, Controller}
import services.SpeechService

@Singleton
class SpeechController @Inject()(speechService: SpeechService) extends Controller {

  def recognize = Action {
    Ok(speechService.speechToText())
  }
}
