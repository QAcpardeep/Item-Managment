package controllers

import persistence.DAO.ItemDAO
import javax.inject._
import play.api.mvc._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.homePage())
  }

  def viewAll = Action.async { implicit request =>

    val items = ItemDAO.viewAll()
    items.map(itemList => Ok(views.html.viewAllPage(itemList)))
  }

}
