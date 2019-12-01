import scala.util.Using

object CRUD extends App
{
  def listOut (lista: DB.listTip) =
  {
    lista.map { case (k, v) => s"$k -> [${v.mkString(",")}]" } .mkString("\n")
  }

  Using.resource(DB.apply)   //.resource kell, Using nem elég, mert ha más kivétel dobódik (pl. túlindexelés), az elnyelődik
  { db =>
    var out = args(0).toLowerCase match
    {
      case "list" => listOut(db.list)
      case "get" => s"${args(1)} -> [${db.get(args(1)).mkString(",")}]"
      case "set" => 
      {
        db.set(args(1), args.drop(2).toSet)
        listOut(db.list)
      }
      case "remove" => 
      {
        if (args.size==2) db.remove(args(1)) else db.remove(args(1), args.drop(2).toSet)
        listOut(db.list)
      }
      case _ => "arg1: get|set|remove|list !"
    }
    println(out)
  }

}