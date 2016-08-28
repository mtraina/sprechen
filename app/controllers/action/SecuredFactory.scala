package controllers.action

import javax.inject.{Inject, Singleton}

import persistence.UserRepository
import play.api.Logger
import play.api.mvc.Results.Unauthorized
import play.api.mvc.{Action, BodyParser, Request, Result}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait SecuredFactory {
  def secured[A](action: Action[A]): Secured[A]
}

@Singleton
class SecuredFactoryImpl @Inject()(val userRepository: UserRepository) extends SecuredFactory {
  override def secured[A](action: Action[A]): Secured[A] = Secured(action)(userRepository)
}

case class Secured[A] (action: Action[A])(val userRepository: UserRepository) extends Action[A] {

  override def apply(request: Request[A]): Future[Result] = {
    Logger.debug("secured apply called")

    request.headers.get(Secured.AUTH_TOKEN_HEADER).map(userRepository.isLoggedIn) match {
      case Some(r) if r => action(request)
      case _ => Future(Unauthorized)
    }
  }

  override def parser: BodyParser[A] = action.parser
}

object Secured {
  val AUTH_TOKEN_HEADER = "X-AUTH-TOKEN"
  val AUTH_TOKEN = "AUTH_TOKEN"

  def createToken(): String = java.util.UUID.randomUUID.toString
}