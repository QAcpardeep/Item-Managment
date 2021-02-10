package persistence.DAO

import persistence.domain.{Item, Items}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ItemDAO {

  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Items] = TableQuery[Items]
  val initUsersCmd = DBIO.seq(table.schema.create)
  db.run(initUsersCmd)

  //You would have a page to view all items
  def viewAll(): Future[Seq[Item]] = {
    db.run(table.result)
  }

  //A page to edit an item
  def editItem(obj: Item): Future[String] = {
    db.run(table.insertOrUpdate(obj)).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  //A page to create an item
  def createItem(obj: Item): Future[String] = {
    db.run(table += obj).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

  //A page to delete an item
  def delete(id: Int): Future[Int] = {
    db.run(table.filter(_.id === id).delete)
  }
}