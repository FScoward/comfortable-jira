package jira.rest

import com.softwaremill.sttp._
import jira.model.SearchResultsBean

import io.circe.generic.auto._
import io.circe.parser._

class Search(domain: String, user: String, apiToken: String) {
  def searchForIssuesUsingJqlByGet(
      jql: String): Either[String, SearchResultsBean] = {
    implicit val backend = HttpURLConnectionBackend()
    val uri: Uri =
      uri"https://${domain}.atlassian.net/rest/api/2/search?jql=${jql}"
    val response: Id[Response[String]] = sttp
      .get(uri)
      .auth
      .basic(user, apiToken)
      .headers((HeaderNames.Accept, MediaTypes.Json))
      .send()
    for {
      body <- response.body
      bean <- decode[SearchResultsBean](body)
        .fold(error => Left(error.getMessage), success => Right(success))
    } yield bean
  }
}
