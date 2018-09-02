package jira.model

case class SearchResultsBean(expand: String,
                             startAt: Double,
                             maxResults: Double,
                             total: Double,
                             issues: List[Issue],
                             warningMessages: Option[List[String]],
                             names: Option[RenderedFields],
                             schema: Option[RenderedFields])
