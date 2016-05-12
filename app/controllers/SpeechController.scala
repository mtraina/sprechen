package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.SpeechService

import scala.concurrent.{Future, ExecutionContext}

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

    val speech = request.body.file("speech")
    speech.map(s => speechService.speechToText(s.ref.file)
        .map { r =>
          Logger.debug(s"json response=${r.json}")
          val guesses = r.json \\ "transcript"
          Ok(Json.obj("guesses" -> guesses))
        })
      .getOrElse(Future(BadRequest))
  }

  def create = Action.async {
    speechService.createSpeech().map(_ => Ok)
  }
}
