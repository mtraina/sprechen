package controllers

import play.api.mvc.{Action, Controller}

class AuthController extends Controller {

  def authenticate = Action { implicit request =>
    Ok("logged").withSession("user" -> "user1")
  }

}
