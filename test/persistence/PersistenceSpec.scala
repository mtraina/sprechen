package persistence

import models.Speech
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec

import play.api.{Configuration, Mode}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.WSClient
import play.api.inject.bind
import play.modules.reactivemongo.ReactiveMongoApi

class PersistenceSpec extends PlaySpec with MockitoSugar {

  "A persistence class" must {
    val wsClient = mock[WSClient]
    val conf = mock[Configuration]

    val app = new GuiceApplicationBuilder()
      .in(Mode.Test)
      .configure("play.modules.enabled" -> List("play.modules.reactivemongo.ReactiveMongoModule"))
      .overrides(bind[WSClient].to(wsClient))
      .overrides(bind[Configuration].to(conf))
      .build

    val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]

    val speechDao = new SpeechDaoImpl(reactiveMongoApi)

    val res = speechDao.create(Speech(transcript = "cheers"))

    res mustBe null

  }

}
