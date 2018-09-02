package jira.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{Accept, Authorization, BasicHttpCredentials}
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, MediaTypes}
import akka.stream.ActorMaterializer

import scala.concurrent.Future

class Project {
  def getAllProjects(domain: String, user: String, apiToken: String)(implicit system: ActorSystem, materializer: ActorMaterializer) = {
    val httpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://${domain}.atlassian.net/rest/api/2/project")
      .withHeaders(
        headers = List(
          Authorization(BasicHttpCredentials(user, apiToken)),
          Accept(MediaTypes.`application/json`))
      )
    Http().singleRequest(httpRequest)
  }

  def getProjectVersions(domain: String, projectId: String, user: String, apiToken: String)(implicit system: ActorSystem, materializer: ActorMaterializer): Future[HttpResponse] = {
    val httpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://${domain}.atlassian.net/rest/api/2/project/${projectId}/versions")
      .withHeaders(
        headers = List(
          Authorization(BasicHttpCredentials(user, apiToken)),
          Accept(MediaTypes.`application/json`))
      )
    Http().singleRequest(httpRequest)
  }
}
