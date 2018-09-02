package jira.model

case class Issue(expand: String,
                 id: String,
                 self: String,
                 key: String,
                 renderedFields: Option[RenderedFields],
                 properties: Option[RenderedFields],
                 names: Option[RenderedFields],
                 schema: Option[RenderedFields],
                 transitions: Option[List[Transition]],
                 operations: Option[Operation],
                 editmeta: Option[Editmeta],
                 changelog: Option[Changelog],
                 versionedRepresentations: Option[RenderedFields],
                 fields: Option[RenderedFields],
                 editMeta: Option[Editmeta])
