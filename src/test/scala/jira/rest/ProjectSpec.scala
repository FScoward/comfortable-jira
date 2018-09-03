package jira.rest

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.softwaremill.sttp.HttpURLConnectionBackend
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
  implicit val backend = HttpURLConnectionBackend()

  describe("get project") {
    ignore("return status code 200 OK") {
      val project = new Project(domain, user, apiToken)
      assert(project.getAllProjects().is200)
    }
  }
  describe("get project versions") {
    ignore("return status code 200 OK") {
      val project = new Project(domain, user, apiToken)
      assert(project.getProjectVersions(projectId).is200)
    }
  }
}
