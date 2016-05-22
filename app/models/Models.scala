package models

case class Speech(transcript: String)

object JsonFormats {
  import play.api.libs.json.Json

  implicit val speechFormat = Json.format[Speech]
}
