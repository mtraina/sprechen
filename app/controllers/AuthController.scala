package controllers

import java.util.Base64
import javax.inject.Inject

import models.User
import persistence.UserRepository
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller, RequestHeader}

class AuthController @Inject()(val userRepository: UserRepository) extends Controller {

  def authenticate = Action { request =>
    credentials(request).map(u => userRepository.login(u.username, u.password) match {
      case Some(token) => Ok(Json.obj("token" -> token))
      case None => Unauthorized
    }).getOrElse(Unauthorized)
  }

  def credentials(request: RequestHeader): Option[User] = {
    request.headers.get("Authorization").flatMap { authorization =>
      authorization.split(" ").drop(1).headOption.flatMap { encoded =>
        new String(Base64.getDecoder.decode(encoded), "UTF-8").split(":") match {
          case Array(u, p) => Some(User(u, p))
          case _ => None
        }
      }
    }
  }
}
