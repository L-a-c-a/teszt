import scala.collection.mutable.Map
import scala.util.Using

object CRUD extends App
{
  def dolog() = "megvan"

  def printList (lista: Map[String, Array[String]]) =
  {
    lista.map { case (k, v) => println(s"$k -> [${v.mkString(",")}]") }
  }

  //var db = DB.apply
  Using.resource(DB.apply)   //.resource kell, Using nem elég, mert ha más kivétel dobódik (pl. túlindexelés), az nem látszik
  { db =>
    //println(db.list)
    //db.list.map { case (k, v) => println(s"$k -> ${v.foldLeft("[")(_+","+_)}]") }
    args(0).toLowerCase match
    {
      case "list" => printList(db.list)
      case "get" => println(s"${args(1)} -> ${db.get(args(1)).mkString(",")}")
      case "set" => 
      {
        db.set(args(1), args(2))
        printList(db.list)
      }
      case "remove" => 
      {
        db.remove(args(1))
        printList(db.list)
      }
      case _ => println("arg1: get|set|remove|list !")
    }
    
  }

}