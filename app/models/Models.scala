package models

import java.util.Date

case class User(username: String, password: String)

case class Session(user: User, loggedIn: Date, lastSeen: Date)

case class Word(text: String, translations: Seq[String])

object JsonFormats {
  import play.api.libs.json.Json

  implicit val userFormat = Json.format[User]
  implicit val wordFormat = Json.format[Word]
}
