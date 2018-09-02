package jira.model

case class Changelog(startAt: Double,
                     maxResults: Double,
                     total: Double,
                     histories: List[Option[RenderedFields]])
