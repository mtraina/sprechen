package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}
import services.SpeechService

@Singleton
class SpeechController @Inject()(speechService: SpeechService) extends Controller {

  def recognize = Action(parse.temporaryFile) { request =>
    Ok(speechService.speechToText(request.body.file))
  }

}
