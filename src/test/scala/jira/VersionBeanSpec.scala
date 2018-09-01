package jira

import org.scalatest.{EitherValues, FunSpec, Matchers}
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

class VersionBeanSpec extends FunSpec with Matchers with EitherValues {
  describe("parse version beans") {
    it("pattern1") {
      val versionBean1 = VersionBean(
        self = "http://your-domain.atlassian.net/rest/api/2/version/10000",
        id = "10000",
        description = "An excellent version",
        name = "New Version 1",
        archived = false,
        released = true,
        releaseDate = Some("2010-07-06"),
        overdue = true,
        userReleaseDate = Some("6/Jul/2010"),
        projectId = 10000
      )

      val versionBean2 = VersionBean(
        self = "http://your-domain.atlassian.net/rest/api/2/version/10010",
        id = "10010",
        description = "Minor Bugfix version",
        name = "Next Version",
        archived = false,
        released = false,
        overdue = false,
        projectId = 10000,
        issuesStatusForFixVersion = Some(IssueStatusForFixVersion(
          unmapped = 0,
          toDo = 10,
          inProgress = 20,
          done = 100
        ))
      )

      val responseJson =
        s"""
          |[
          |  {
          |    "self": "${versionBean1.self}",
          |    "id": "${versionBean1.id}",
          |    "description": "${versionBean1.description}",
          |    "name": "${versionBean1.name}",
          |    "archived": ${versionBean1.archived},
          |    "released": ${versionBean1.released},
          |    "releaseDate": "${versionBean1.releaseDate.get}",
          |    "overdue": ${versionBean1.overdue},
          |    "userReleaseDate": "${versionBean1.userReleaseDate.get}",
          |    "projectId": ${versionBean1.projectId}
          |  },
          |  {
          |    "self": "${versionBean2.self}",
          |    "id": "${versionBean2.id}",
          |    "description": "${versionBean2.description}",
          |    "name": "${versionBean2.name}",
          |    "archived": ${versionBean2.archived},
          |    "released": ${versionBean2.released},
          |    "overdue": ${versionBean2.overdue},
          |    "projectId": ${versionBean2.projectId},
          |    "issuesStatusForFixVersion": {
          |      "unmapped": ${versionBean2.issuesStatusForFixVersion.get.unmapped},
          |      "toDo": ${versionBean2.issuesStatusForFixVersion.get.toDo},
          |      "inProgress": ${versionBean2.issuesStatusForFixVersion.get.inProgress},
          |      "done": ${versionBean2.issuesStatusForFixVersion.get.done}
          |    }
          |  }
          |]
        """.stripMargin

      val expect = List(versionBean1, versionBean2)

      val actual = decode[List[VersionBean]](responseJson)
      assert(actual.right.value == expect)
    }
  }
}


