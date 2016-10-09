package services

import java.io.File

import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import persistence.WordRepository
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse
import ws.{TranslateClient, SpeechClient}

import scala.concurrent.Future

class WordServiceImplSpec extends FlatSpec with Matchers with MockitoSugar {
  val f = new File("")
  val r = mock[WSResponse]
  val json = Json.parse("""{"transcript":"text"}""")
  given(r.json).willReturn(json)
  val response = Future.successful(r)
  val client = mock[SpeechClient]
  val wr = mock[WordRepository]
  given(client.post(f)) willReturn response

  val tc = mock[TranslateClient]
  val wordService = new WordServiceImpl(client, wr, tc)

  "A word service" should "save the speech and return the future of the value" in {
    val resp = wordService.saveWord(f)
    resp.value.get.get.json shouldBe json
  }
}
