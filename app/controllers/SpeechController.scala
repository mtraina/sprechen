package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.mvc.{Action, Controller}
import services.SpeechService

import scala.concurrent.ExecutionContext

@Singleton
class SpeechController @Inject()(speechService: SpeechService,
                                 implicit val context: ExecutionContext) extends Controller {

  def recognize = Action(parse.multipartFormData) { request =>
    Logger.debug(s"got request=${request.body.toString}")

    request.body.file("speech") match {
      case Some(f) => speechService.saveSpeech(f.ref.file)
        Ok
      case _ => BadRequest
    }
  }
}
