package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.mvc.{Action, Controller}
import services.SpeechService

import scala.concurrent.ExecutionContext

@Singleton
class SpeechController @Inject()(speechService: SpeechService,
                                 implicit val context: ExecutionContext) extends Controller {

  def recognize = Action.async(parse.temporaryFile) { request =>
    speechService.speechToText(request.body.file)
      .map { r =>
        Logger.debug(s"json response=${r.json}")
        Ok((r.json \\ "transcript").head)
    }
  }
}
