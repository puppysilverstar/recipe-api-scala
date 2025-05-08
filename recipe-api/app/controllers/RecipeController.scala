package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.Recipe
import scala.collection.mutable
import java.util.concurrent.atomic.AtomicLong

@Singleton
class RecipeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val recipes = mutable.Map[Long, Recipe]()
  private val idCounter = new AtomicLong(1)

  def createRecipe: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Recipe].fold(
      _ => BadRequest(Json.obj(
        "message" -> "Recipe creation failed!",
        "required" -> "title, making_time, serves, ingredients, cost"
      )),
      data => {
        val id = idCounter.getAndIncrement()
        val recipeWithId = data.copy(id = Some(id))
        recipes += (id -> recipeWithId)
        Ok(Json.obj(
          "message" -> "Recipe successfully created!",
          "recipe" -> Seq(recipeWithId)
        ))
      }
    )
  }

  def getRecipes: Action[AnyContent] = Action {
    Ok(Json.obj("recipes" -> recipes.values.toSeq))
  }

  def getRecipeById(id: Long): Action[AnyContent] = Action {
    recipes.get(id) match {
      case Some(recipe) =>
        Ok(Json.obj("message" -> "Recipe details by id", "recipe" -> Seq(recipe)))
      case None =>
        NotFound(Json.obj("message" -> "No recipe found"))
    }
  }

  def updateRecipe(id: Long): Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Recipe].fold(
      _ => BadRequest(Json.obj("message" -> "Recipe update failed!")),
      data => {
        recipes.get(id) match {
          case Some(_) =>
            val updatedRecipe = data.copy(id = Some(id))
            recipes.update(id, updatedRecipe)
            Ok(Json.obj("message" -> "Recipe successfully updated!", "recipe" -> Seq(updatedRecipe)))
          case None =>
            NotFound(Json.obj("message" -> "No recipe found"))
        }
      }
    )
  }

  def deleteRecipe(id: Long): Action[AnyContent] = Action {
    if (recipes.remove(id).isDefined)
      Ok(Json.obj("message" -> "Recipe successfully removed!"))
    else
      NotFound(Json.obj("message" -> "No recipe found"))
  }
}
