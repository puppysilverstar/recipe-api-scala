package models

import play.api.libs.json._

case class Recipe(
  id: Option[Long],
  title: String,
  making_time: String,
  serves: String,
  ingredients: String,
  cost: Int
)

object Recipe {
  implicit val recipeFormat: OFormat[Recipe] = Json.format[Recipe]
}
