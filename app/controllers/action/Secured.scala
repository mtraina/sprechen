package controllers.action

import persistence.UserRepository
import play.api.Logger
import play.api.mvc.{Result, Request, BodyParser, Action}
import play.api.mvc.Results.Unauthorized

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Secured[A] (action: Action[A]) extends Action[A] {
  val userRepository = UserRepository.instance

  override def apply(request: Request[A]): Future[Result] = {
    Logger.debug("apply called")

    request.session.get("user").map(userRepository.isLoggedIn) match {
      // check response present but false
      case Some(r) if r => action(request)
      case _ => Future(Unauthorized)
    }
  }

  override def parser: BodyParser[A] = action.parser

}
