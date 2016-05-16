package persistence

import javax.inject.Inject

import com.google.inject
import play.api.Play

import play.api.inject.guice.GuiceApplicationBuilder

import play.api.test.FakeApplication
import play.api.test.Helpers._

import reactivemongo.api.gridfs.GridFS

import play.modules.reactivemongo.{
DefaultReactiveMongoApi,
ReactiveMongoApiFromContext,
ReactiveMongoApi
}

import scala.concurrent.duration._

import org.specs2.concurrent.ExecutionEnv

object PlaySpec extends org.specs2.mutable.Specification {
  "be initialized from custom application context" in { implicit ee: ExecutionEnv =>
    import play.api.{
      ApplicationLoader,
      Configuration
    }

    val timeout = 5 seconds
    val env = play.api.Environment.simple(mode = play.api.Mode.Test)
    val config = Configuration.load(env)
    val context = ApplicationLoader.Context(env, None,
      new play.core.DefaultWebCommands(), config)

    val apiFromCustomCtx = new ReactiveMongoApiFromContext(context) {
      lazy val router = play.api.routing.Router.empty
    }

    apiFromCustomCtx.reactiveMongoApi.database.map(_ => {})
      .aka("database resolution") must beEqualTo({}).await(retries = 1, timeout = timeout)

    //val i = GuiceApplicationBuilder.
//    @Inject
//    val speechDao: SpeechDao
//    val j = 0

  }
}