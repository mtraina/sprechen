import com.google.inject.AbstractModule
import controllers.action.{SecuredFactoryImpl, SecuredFactory}
import persistence.{UserRepositoryImpl, UserRepository, WordRepositoryImpl, WordRepository}
import services._
import session.{SessionRepositoryImpl, SessionRepository}
import ws.{TranslateClientImpl, TranslateClient, SpeechClient, SpeechClientImpl}

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {
    // ws clients
    bind(classOf[SpeechClient]).to(classOf[SpeechClientImpl])
    bind(classOf[TranslateClient]).to(classOf[TranslateClientImpl])

    // persistence
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl])
    bind(classOf[WordRepository]).to(classOf[WordRepositoryImpl])

    // services
    bind(classOf[WordService]).to(classOf[WordServiceImpl])

    // auth
    bind(classOf[SecuredFactory]).to(classOf[SecuredFactoryImpl])
    bind(classOf[SessionRepository]).to(classOf[SessionRepositoryImpl])
  }
}
