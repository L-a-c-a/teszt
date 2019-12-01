import java.io.File
import java.io.PrintWriter
import scala.io.Source
import DBproba._  // hogy db-t ne kelljen kvalifikálni
import Serialization._

class DBproba extends DB
{
  override def get(kulcs: String) = db.get(kulcs) getOrElse Set[String]()
  override def list = db
  //override def set(kulcs: String, ertek: String) = db += kulcs -> Set(ertek)  //nem jó, a halmazt ki kell egészíteni!
  override def set(kulcs: String, ertek: Set[String]) = db(kulcs) = get(kulcs) ++ ertek
  override def remove(kulcs: String) = db -= kulcs
  override def remove(kulcs: String, ertek: Set[String]) = db(kulcs) = get(kulcs) -- ertek
  override def close() = 
  {
    val writer = new PrintWriter(new File(serFileName))
    writer.write(serialise(db))
    writer.close()
    println ("DBproba csukva")
  }

  //init
  try { db = deserialise(Source.fromFile(serFileName).getLines.mkString).asInstanceOf[DB.listTip] }
  catch { case x: java.io.FileNotFoundException => {} }
  
}

object DBproba
{
  var db:DB.listTip =  //ha nincs a fájl
  (
  scala.collection.mutable.Map
    ( "k1" -> Set("v1", "v2", "v3")
    , "k2" -> Set("v21", "v22")
    , "k3" -> Set()
    )
  )

  val serFileName = "dbser.txt"

}