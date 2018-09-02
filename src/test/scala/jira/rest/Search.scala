package jira.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.model.headers.{Accept, Authorization, BasicHttpCredentials}
import akka.stream.ActorMaterializer

class Search() {
  def searchForIssuesUsingJqlByGet(domain: String, user: String, apiToken: String)(implicit system: ActorSystem, materializer: ActorMaterializer) = {
    val httpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://${domain}.atlassian.net/rest/api/2/search")
      .withHeaders(
        headers = List(
          Authorization(BasicHttpCredentials(user, apiToken)),
          Accept(MediaTypes.`application/json`))
      )
    Http().singleRequest(httpRequest)
  }
}
