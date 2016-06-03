package controllers

import java.util.Base64

import akka.stream.Materializer
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import persistence.UserRepository
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

class AuthControllerSpec extends PlaySpec with MockitoSugar with Results {
  val userRepository = mock[UserRepository]
  val authController = new AuthController(userRepository)
  def mockApp = new GuiceApplicationBuilder().build()
  val materializer = mockApp.injector.instanceOf[Materializer]

  "An auth controller" should {
    "authenticate with valid credentials" in {
      //val request = FakeRequest(POST, "/auth").withMultipartFormDataBody("username" -> "user", "password" -> "pass")
      //val result = authController.authenticate()(request)
      //status(result.run()(materializer)) mustEqual OK
//      val bodyText: String = contentAsString(result)
//      bodyText mustBe "logged"
    }

    "get the credentials" in {
      val user = "user"
      val pass = "pass"
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(user, pass))
      val result = authController.authenticate()(request)
      status(result) mustBe OK
    }
  }

  def authHeaderValue(username: String, password: String) =
    "Basic " + Base64.getEncoder.encodeToString(s"$username:$password".getBytes)

}
