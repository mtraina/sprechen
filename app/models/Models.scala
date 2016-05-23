package models

case class Word(text: String, translations: Set[String])

object JsonFormats {
  import play.api.libs.json.Json

  implicit val wordFormat = Json.format[Word]
}
