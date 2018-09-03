package jira.rest

import com.softwaremill.sttp._

class Project(domain: String, user: String, apiToken: String) {
  val baseUri = s"https://${domain}.atlassian.net/rest/api/2/project"
  def getAllProjects()(implicit backend: SttpBackend[Id, Nothing]) = {
    val uri: Uri = uri"https://${domain}.atlassian.net/rest/api/2/project"
    sttp
      .get(uri)
      .auth
      .basic(user, apiToken)
      .headers((HeaderNames.Accept, MediaTypes.Json))
      .send()
  }

  def getProjectVersions(projectId: String)(
      implicit backend: SttpBackend[Id, Nothing]) = {
    val uri = uri"${baseUri}/${projectId}/versions"
    sttp
      .get(uri)
      .auth
      .basic(user, apiToken)
      .headers((HeaderNames.Accept, MediaTypes.Json))
      .send()
  }
}
