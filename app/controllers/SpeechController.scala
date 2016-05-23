package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.WordService

import scala.concurrent.ExecutionContext
import models.JsonFormats._

@Singleton
class SpeechController @Inject()(wordService: WordService,
                                 implicit val context: ExecutionContext) extends Controller {

  def recognize = Action(parse.multipartFormData) { request =>
    Logger.debug(s"got request=${request.body.toString}")

    request.body.file("speech") match {
      case Some(f) => wordService.saveWord(f.ref.file)
        Ok
      case _ => BadRequest
    }
  }

  def speeches = Action.async {
    wordService.getWords().map(s => Ok(Json.toJson(s)))
  }
}
