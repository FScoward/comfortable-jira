package jira.model

import io.circe.generic.auto._
import io.circe.parser._
import org.scalatest.{EitherValues, FunSpec, Matchers}

class VersionBeanSpec extends FunSpec with Matchers with EitherValues {
  describe("parse version beans") {
    it("no expand") {
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
        issuesStatusForFixVersion = Some(
          IssueStatusForFixVersion(
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

    it("contain expands") {
      val expect = VersionBean(
        expand = Some("<string>"),
        self = "<string>",
        id = "<string>",
        description = "<string>",
        name = "<string>",
        archived = true,
        released = true,
        startDate = Some("<string>"),
        releaseDate = Some("<string>"),
        overdue = true,
        userStartDate = Some("<string>"),
        userReleaseDate = Some("<string>"),
        project = Some("<string>"),
        projectId = 130,
        moveUnfixedIssuesTo = Some("<string>"),
        operations = Some(
          List(
            Operations(
              id = "<string>",
              styleClass = "<string>",
              iconClass = "<string>",
              label = "<string>",
              title = "<string>",
              href = "<string>",
              weight = 2154
            ))),
        remotelinks = Some(
          List(
            RemoteLinks(
              self = "<string>",
              name = "<string>",
              link = "{}"
            ))),
        issuesStatusForFixVersion = Some(
          IssueStatusForFixVersion(
            unmapped = 2154,
            toDo = 2154,
            inProgress = 2154,
            done = 2154
          ))
      )

      val responseJson =
        """
          |{
          |  "expand": "<string>",
          |  "self": "<string>",
          |  "id": "<string>",
          |  "description": "<string>",
          |  "name": "<string>",
          |  "archived": true,
          |  "released": true,
          |  "startDate": "<string>",
          |  "releaseDate": "<string>",
          |  "overdue": true,
          |  "userStartDate": "<string>",
          |  "userReleaseDate": "<string>",
          |  "project": "<string>",
          |  "projectId": 130,
          |  "moveUnfixedIssuesTo": "<string>",
          |  "operations": [
          |    {
          |      "id": "<string>",
          |      "styleClass": "<string>",
          |      "iconClass": "<string>",
          |      "label": "<string>",
          |      "title": "<string>",
          |      "href": "<string>",
          |      "weight": 2154
          |    }
          |  ],
          |  "remotelinks": [
          |    {
          |      "self": "<string>",
          |      "name": "<string>",
          |      "link": "{}"
          |    }
          |  ],
          |  "issuesStatusForFixVersion": {
          |    "unmapped": 2154,
          |    "toDo": 2154,
          |    "inProgress": 2154,
          |    "done": 2154
          |  }
          |}
        """.stripMargin

      val actual = decode[VersionBean](responseJson)
      assert(actual.right.value == expect)
    }
  }
}
