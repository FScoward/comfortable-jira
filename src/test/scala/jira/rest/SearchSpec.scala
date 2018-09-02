package jira.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.scalatest.AsyncFunSpec

class SearchSpec extends AsyncFunSpec {
  val config = ConfigFactory.load()
  val domain = config.getString("jira.rest.domain")
  val projectId = "10002"
  val user = config.getString("jira.rest.user")
  val apiToken = config.getString("jira.rest.apiToken")
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  describe("search") {
    ignore("return status code 200 OK") {
      val search = new Search(domain, user, apiToken)
      search.searchForIssuesUsingJqlByGet().map { response =>
        assert(response.status == StatusCodes.OK)
      }
    }
  }
}
