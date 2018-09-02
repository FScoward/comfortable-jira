package jira.rest

import com.typesafe.config.ConfigFactory
import org.scalatest.AsyncFunSpec

class SearchSpec extends AsyncFunSpec {
  val config = ConfigFactory.load()
  val domain = config.getString("jira.rest.domain")
  val user = config.getString("jira.rest.user")
  val apiToken = config.getString("jira.rest.apiToken")

  describe("search") {
    val search = new Search(domain, user, apiToken)

    ignore("return success") {
      val actual = search.searchForIssuesUsingJqlByGet("text ~ 迷惑メール")
      assert(actual.isRight)
    }

    ignore("return error") {
      val actual = search.searchForIssuesUsingJqlByGet("~~~")
      assert(actual.isLeft)
    }
  }
}
