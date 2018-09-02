package jira.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{
  Accept,
  Authorization,
  BasicHttpCredentials
}
import akka.http.scaladsl.model.{
  HttpMethods,
  HttpRequest,
  HttpResponse,
  MediaTypes
}
import akka.stream.ActorMaterializer

import scala.concurrent.Future

class Search(domain: String, user: String, apiToken: String) {
  def searchForIssuesUsingJqlByGet()(
      implicit system: ActorSystem,
      materializer: ActorMaterializer
  ): Future[HttpResponse] = {
    val httpRequest =
      HttpRequest(
        method = HttpMethods.GET,
        uri = s"https://${domain}.atlassian.net/rest/api/2/search"
      ).withHeaders(
        headers = List(
          Authorization(BasicHttpCredentials(user, apiToken)),
          Accept(MediaTypes.`application/json`)
        )
      )
    Http().singleRequest(httpRequest)
  }
}
