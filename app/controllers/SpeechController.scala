package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.mvc.{Action, Controller}
import services.SpeechService

import scala.concurrent.ExecutionContext

@Singleton
class SpeechController @Inject()(speechService: SpeechService,
                                 implicit val context: ExecutionContext) extends Controller {

  def recognizeTempFile = Action.async(parse.temporaryFile) { request =>
    speechService.speechToText(request.body.file)
      .map { r =>
        Logger.debug(s"json response=${r.json}")
        Ok((r.json \\ "transcript").head)
    }
  }

  def recognize = Action.async(parse.multipartFormData) { request =>
    Logger.debug(request.body.toString)

    val speech = request.body.file("speech").get
    speechService.speechToText(speech.ref.file)
          .map { r =>
            Logger.debug(s"json response=${r.json}")
            Ok((r.json \\ "transcript").head)
        }
  }
}
