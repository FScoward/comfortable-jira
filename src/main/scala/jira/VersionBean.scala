package jira

case class VersionBean
(
  self: String,
  id: String,
  description: String,
  name: String,
  archived: Boolean,
  released: Boolean,
  releaseDate: Option[String] = None,
  overdue: Boolean,
  userReleaseDate: Option[String] = None,
  projectId: Int,
  issuesStatusForFixVersion: Option[IssueStatusForFixVersion] = None
)

case class IssueStatusForFixVersion
(
  unmapped: Int,
  toDo: Int,
  inProgress: Int,
  done: Int
)
