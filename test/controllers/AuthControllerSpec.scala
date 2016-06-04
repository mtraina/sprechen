package controllers

import java.util.Base64

import models.User
import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import persistence.UserRepository
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

class AuthControllerSpec extends PlaySpec with MockitoSugar with Results {
  val userRepository = mock[UserRepository]
  val authController = new AuthController(userRepository)

  "An auth controller" should {
    // TODO: no mocking: "with sessions needs a running application
//    "authenticate with valid credentials" in {
//      val user = "user"
//      val pass = "pass"
//      given(userRepository.login(user, pass)) willReturn true
//      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass"))
//      val result = authController.authenticate()(request)
//      status(result) mustBe OK
//      val bodyText: String = contentAsString(result)
//      bodyText mustBe "logged"
//    }

    "not authenticate with not matching credentials" in {
      val user = "user"
      val pass = "pass"
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass"))
      val result = authController.authenticate()(request)
      status(result) mustBe UNAUTHORIZED
    }

    "create a user from encoded credentials" in {
      val user = "user"
      val pass = "pass"
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass"))

      val result = authController.credentials(request)
      result mustBe Some(User(user, pass))
    }

    "return an empty user from non encoded credentials" in {
      val user = "user"
      val pass = "pass"
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> s"$user:$pass")

      authController.credentials(request) mustBe None
    }

    "return an empty user from invalid encoded credentials" in {
      val user = "user"
      val pass = "pass"
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass:test"))

      authController.credentials(request) mustBe None
    }
  }

  def authHeaderValue(credentials: String) = "Basic " + Base64.getEncoder.encodeToString(credentials.getBytes)

}
