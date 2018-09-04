import com.softwaremill.sttp.HttpURLConnectionBackend
import com.typesafe.config.ConfigFactory
import jira.rest.{Project, Search}

object Main {
  def main(args: Array[String]): Unit = {
    case class Command(command: String = "")

    val config = ConfigFactory.load()
    val domain = config.getString("jira.rest.domain")
    val user = config.getString("jira.rest.user")
    val apiToken = config.getString("jira.rest.apiToken")
    implicit val backend = HttpURLConnectionBackend()

    val parser = new scopt.OptionParser[Command]("comfortable-jira") {
      head("scopt", "3.x")

      cmd("search")
        .action((_, c) => c.copy(command = "search"))
        .text("search")
        .children(
          opt[String]("jql")
            .abbr("jql")
            .action((_, c) => c.copy())
            .foreach { s =>
              new Search(domain, user, apiToken)
                .searchForIssuesUsingJqlByGet(s)
                .fold(println, println)
            }
        )

      cmd("project")
        .action((_, c) => c.copy(command = "project"))
        .text("project")
        .children {
          opt[Unit]("all")
            .abbr("a")
            .foreach { _ =>
              new Project(domain, user, apiToken)
                .getAllProjects()
                .body
                .fold(println, println)
            }
          opt[String]("version")
            .abbr("v")
            .foreach { projectId =>
              new Project(domain, user, apiToken)
                .getProjectVersions(projectId)
                .body
                .fold(println, println)
            }
        }
    }
    parser.parse(args, Command())
  }
}
