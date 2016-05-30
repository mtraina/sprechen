package controllers.action

import persistence.UserRepository
import play.api.Logger
import play.api.mvc.Results.Unauthorized
import play.api.mvc.{Action, BodyParser, Request, Result}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class Secured[A] (action: Action[A]) extends Action[A] {
  val userRepository = UserRepository.instance

  override def apply(request: Request[A]): Future[Result] = {
    Logger.debug("secured apply called")

    request.session.get("user").map(userRepository.isLoggedIn) match {
      case Some(r) if r => action(request)
      case _ => Future(Unauthorized)
    }
  }

  override def parser: BodyParser[A] = action.parser

}
