package controllers

import javax.inject.Inject

import persistence.UserRepository
import play.api.mvc.{Action, Controller}

class AuthController @Inject()(val userRepository: UserRepository) extends Controller {

  def authenticate = Action(parse.multipartFormData) { implicit request =>
    val username = request.body.dataParts("username").head
    val password = request.body.dataParts("password").head

    userRepository.login(username, password) match {
      case true => Ok("logged").withSession("user" -> username)
      case false => Unauthorized
    }
  }

}
