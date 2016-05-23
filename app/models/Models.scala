package models

case class Word(text: String, translations: Seq[String])

object JsonFormats {
  import play.api.libs.json.Json

  implicit val wordFormat = Json.format[Word]
}
