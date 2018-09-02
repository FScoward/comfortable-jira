package jira.model

import io.circe.generic.auto._
import io.circe.parser._
import org.scalatest.{EitherValues, FunSpec, Matchers}

class SearchResultsBeanSpec extends FunSpec with Matchers with EitherValues {
  describe("parse search results bean") {
    it("contain expands") {
      val responseJson =
        """
          |{
          |  "expand": "<string>",
          |  "startAt": 2154,
          |  "maxResults": 2154,
          |  "total": 2154,
          |  "issues": [
          |    {
          |      "expand": "<string>",
          |      "id": "<string>",
          |      "self": "<string>",
          |      "key": "<string>",
          |      "renderedFields": {},
          |      "properties": {},
          |      "names": {},
          |      "schema": {},
          |      "transitions": [
          |        {
          |          "id": "<string>",
          |          "name": "<string>",
          |          "to": {},
          |          "hasScreen": true,
          |          "isGlobal": true,
          |          "isInitial": true,
          |          "isConditional": true,
          |          "fields": {},
          |          "expand": "<string>"
          |        }
          |      ],
          |      "operations": {
          |        "linkGroups": [
          |          {}
          |        ]
          |      },
          |      "editmeta": {
          |        "fields": {}
          |      },
          |      "changelog": {
          |        "startAt": 2154,
          |        "maxResults": 2154,
          |        "total": 2154,
          |        "histories": [
          |          {}
          |        ]
          |      },
          |      "versionedRepresentations": {},
          |      "fields": {},
          |      "editMeta": {
          |        "fields": {}
          |      }
          |    }
          |  ],
          |  "warningMessages": [
          |    "<string>"
          |  ],
          |  "names": {},
          |  "schema": {}
          |}
        """.stripMargin
      val expect = SearchResultsBean(
        expand = "<string>",
        startAt = 2154,
        maxResults = 2154,
        total = 2154,
        issues = List(
          Issue(
            expand = "<string>",
            id = "<string>",
            self = "<string>",
            key = "<string>",
            renderedFields = Some(RenderedFields()),
            properties = Some(RenderedFields()),
            names = Some(RenderedFields()),
            schema = Some(RenderedFields()),
            transitions = List(
              Transition(
                id = "<string>",
                name = "<string>",
                to = Some(RenderedFields()),
                hasScreen = true,
                isGlobal = true,
                isInitial = true,
                isConditional = true,
                fields = Some(RenderedFields()),
                expand = "<string>"
              )
            ),
            operations = Operation(
              linkGroups = List(Some(RenderedFields()))
            ),
            editmeta = Editmeta(
              fields = Some(RenderedFields())
            ),
            changelog = Changelog(
              startAt = 2154,
              maxResults = 2154,
              total = 2154,
              histories = List(Some(RenderedFields()))
            ),
            versionedRepresentations = Some(RenderedFields()),
            fields = Some(RenderedFields()),
            editMeta = Editmeta(
              fields = Some(RenderedFields())
            )
          )
        ),
        warningMessages = List("<string>"),
        names = Some(RenderedFields()),
        schema = Some(RenderedFields())
      )

      val actual = decode[SearchResultsBean](responseJson)
      assert(actual.right.value == expect)
    }
  }
}
