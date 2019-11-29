import scala.util.Using

object CRUD extends App
{
  def printList (lista: DB.listTip /*Map[String, Array[String]]*/) =
  {
    lista.map { case (k, v) => println(s"$k -> [${v.mkString(",")}]") }
  }

  Using.resource(DB.apply)   //.resource kell, Using nem elég, mert ha más kivétel dobódik (pl. túlindexelés), az elnyelődik
  { db =>
    args(0).toLowerCase match
    {
      case "list" => printList(db.list)
      case "get" => println(s"${args(1)} -> [${db.get(args(1)).mkString(",")}]")
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