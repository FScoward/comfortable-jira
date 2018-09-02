package jira.model

case class Transition(id: String,
                      name: String,
                      to: Option[RenderedFields],
                      hasScreen: Boolean,
                      isGlobal: Boolean,
                      isInitial: Boolean,
                      isConditional: Boolean,
                      fields: Option[RenderedFields],
                      expand: String)
