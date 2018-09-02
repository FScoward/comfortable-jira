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

class Project(domain: String, user: String, apiToken: String) {
  val baseUri = s"https://${domain}.atlassian.net/rest/api/2/project"
  def getAllProjects()(implicit system: ActorSystem,
                       materializer: ActorMaterializer) = {
    val httpRequest = HttpRequest(method = HttpMethods.GET, uri = baseUri)
      .withHeaders(
        headers = List(
          Authorization(BasicHttpCredentials(user, apiToken)),
          Accept(MediaTypes.`application/json`)
        )
      )
    Http().singleRequest(httpRequest)
  }

  def getProjectVersions(projectId: String)(
      implicit system: ActorSystem,
      materializer: ActorMaterializer
  ): Future[HttpResponse] = {
    val httpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"${baseUri}/${projectId}/versions"
    ).withHeaders(
      headers = List(
        Authorization(BasicHttpCredentials(user, apiToken)),
        Accept(MediaTypes.`application/json`)
      )
    )
    Http().singleRequest(httpRequest)
  }
}
