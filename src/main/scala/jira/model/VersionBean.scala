package jira.model

case class VersionBean
(
  expand: Option[String] = None,
  self: String,
  id: String,
  description: String,
  name: String,
  archived: Boolean,
  released: Boolean,
  startDate: Option[String] = None,
  releaseDate: Option[String] = None,
  overdue: Boolean,
  userStartDate: Option[String] = None,
  userReleaseDate: Option[String] = None,
  project: Option[String] = None,
  projectId: Int,
  moveUnfixedIssuesTo: Option[String] = None,
  operations: Option[List[Operations]] = None,
  remotelinks: Option[List[RemoteLinks]] = None,
  issuesStatusForFixVersion: Option[IssueStatusForFixVersion] = None
)

case class IssueStatusForFixVersion
(
  unmapped: Int,
  toDo: Int,
  inProgress: Int,
  done: Int
)
