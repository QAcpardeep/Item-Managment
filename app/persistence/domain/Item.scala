package persistence.domain
import slick.jdbc.MySQLProfile.api._
case class Item(id: Int, name: String, description: String, manufacturer: String, warrenty:Double, price: Double, discount: Double)

case class Items(tag: Tag) extends Table[Item] (tag, "items"){
  def id = column[Int]("item_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def description = column[String]("description")
  def manufacturer = column[String]("manufacturer")
  def warrenty = column[Double]("warrenty")
  def price = column[Double]("price")
  def discount = column[Double]("discount")
  def * = (id, name, description, manufacturer, warrenty, price, discount) <> (Item.tupled, Item.unapply)
}

