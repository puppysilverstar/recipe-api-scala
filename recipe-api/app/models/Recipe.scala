package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Recipe(
  id: Option[Long],
  title: String,
  making_time: String,
  serves: String,
  ingredients: String,
  cost: Int
)

object Recipe {
  implicit val recipeReads: Reads[Recipe] = (
    (__ \ "id").readNullable[Long] and
    (__ \ "title").read[String] and
    (__ \ "making_time").read[String] and
    (__ \ "serves").read[String] and
    (__ \ "ingredients").read[String] and
    (__ \ "cost").read[String].map(_.toInt)
  )(Recipe.apply _)

  implicit val recipeWrites: OWrites[Recipe] = new OWrites[Recipe] {
    def writes(recipe: Recipe): JsObject = Json.obj(
      "id" -> recipe.id,
      "title" -> recipe.title,
      "making_time" -> recipe.making_time,
      "serves" -> recipe.serves,
      "ingredients" -> recipe.ingredients,
      "cost" -> recipe.cost.toString
    )
  }
}
