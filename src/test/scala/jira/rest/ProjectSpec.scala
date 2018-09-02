package jira.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import org.scalatest.AsyncFunSpec

class ProjectSpec extends AsyncFunSpec {
  val config = ConfigFactory.load()
  val domain = config.getString("jira.rest.domain")
  val projectId = "10002"
  val user = config.getString("jira.rest.user")
  val apiToken = config.getString("jira.rest.apiToken")
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  describe("get project") {
    ignore("return status code 200 OK") {
      val project = new Project()
      project.getAllProjects(domain, user, apiToken).map(response => {
        assert(response.status == StatusCodes.OK)
      })
    }
  }
  describe("get project versions") {
    ignore("return status code 200 OK") {
      val project = new Project()
      project.getProjectVersions(domain, projectId, user, apiToken).map(response => assert(response.status == StatusCodes.OK))
    }
  }
}
