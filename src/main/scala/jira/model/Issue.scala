package jira.model

case class Issue(expand: String,
                 id: String,
                 self: String,
                 key: String,
                 renderedFields: Option[RenderedFields],
                 properties: Option[RenderedFields],
                 names: Option[RenderedFields],
                 schema: Option[RenderedFields],
                 transitions: List[Transition],
                 operations: Operation,
                 editmeta: Editmeta,
                 changelog: Changelog,
                 versionedRepresentations: Option[RenderedFields],
                 fields: Option[RenderedFields],
                 editMeta: Editmeta)
