package services

import java.io.File
import javax.inject.Inject

import ws.SpeechWSClient

import scala.concurrent.ExecutionContext

trait SpeechService {
  def speechToText(): String
}

class SpeechServiceImpl @Inject()(val client: SpeechWSClient,
                                  implicit val context: ExecutionContext) extends SpeechService {

  override def speechToText(): String = {

    val response = client.post(new File("data/myRecording01.wav"))
    response.map {
      r => {
        val transcripts = r.json \\ "transcript"
        print(transcripts.head)
      }
    }
    "text"
  }
}