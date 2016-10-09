package controllers

import java.util.Base64

import models.User
import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import persistence.UserRepository
import play.api.libs.json.Json
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._

class AuthControllerSpec extends PlaySpec with MockitoSugar with Results {
  val userRepository = mock[UserRepository]
  val authController = new AuthController(userRepository)

  "An auth controller" should {

    "not authenticate with not matching credentials" in {
      // given
      val user = "user"
      val pass = "pass"
      given(userRepository.login(user, pass)) willReturn None
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass"))

      // when
      val result = authController.authenticate()(request)

      // then
      status(result) mustBe UNAUTHORIZED
    }

    "authenticate with matching credentials" in {
      // given
      val user = "user"
      val pass = "pass"
      given(userRepository.login(user, pass)) willReturn Some("token")
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue(s"$user:$pass"))

      // when
      val result = authController.authenticate()(request)

      // then
      status(result) mustBe OK
      contentAsJson(result) mustBe Json.obj("token" -> "token")
    }

    "not authenticate with not valid authentication header" in {
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> "user:pass")
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
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> "user:pass")
      authController.credentials(request) mustBe None
    }

    "return an empty user from invalid encoded credentials" in {
      val request = FakeRequest(POST, "/auth").withHeaders("Authorization" -> authHeaderValue("user:pass:test"))
      authController.credentials(request) mustBe None
    }
  }

  def authHeaderValue(credentials: String) = "Basic " + Base64.getEncoder.encodeToString(credentials.getBytes)
}
