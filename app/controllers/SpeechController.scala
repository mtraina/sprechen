package controllers

import javax.inject.{Inject, Singleton}

import controllers.action.SecuredFactory
import models.JsonFormats._
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.WordService

import scala.concurrent.ExecutionContext

@Singleton
class SpeechController @Inject()(val wordService: WordService,
                                 val securedFactory: SecuredFactory,
                                 implicit val context: ExecutionContext) extends Controller {

  def recognize = Action(parse.multipartFormData) { request =>
    Logger.debug(s"got request=${request.body.toString}")

    request.body.file("speech") match {
      case Some(f) => wordService.saveWord(f.ref.file)
        Ok
      case _ => BadRequest
    }
  }

  def speeches = securedFactory.secured {
    Action.async { request =>
      wordService.getWords().map(s => Ok(Json.toJson(s)))
    }
  }
}
