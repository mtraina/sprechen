package controllers

import javax.inject.Inject

import persistence.UserRepository
import play.api.mvc.{Request, Action, Controller}

class AuthController @Inject()(val userRepository: UserRepository) extends Controller {

  def authenticate = Action { implicit request =>
    Ok("logged")
//    val data = request.body.asJson
//    val username = data.get.get("username").head
//    val password = request.body//.dataParts("password").head
//
//    userRepository.login(username, password) match {
//      case true => Ok("logged").withSession("user" -> username)
//      case false => Unauthorized
//    }
  }

//  def credentials(request: Request) = {
//    request.headers.get("Authorization")
//  }

}
