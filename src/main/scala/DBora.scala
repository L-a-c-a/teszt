import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/* CREATE TABLE "LACA"."TESZT" 
   (	"KULCS" VARCHAR2(2000) NOT NULL ENABLE, 
	"ERTEK" VARCHAR2(2000) NOT NULL ENABLE, 
   CONSTRAINT "TESZT_UN" UNIQUE ("KULCS", "ERTEK")
*/

class DBora extends DB
{
  override def get(kulcs: String) = 
  {
    conn = connOpen
    var ret = Set[String]()
    sql = s"select ertek from teszt where kulcs='$kulcs'"
    stmt = conn.prepareStatement(sql)
    rs = stmt.executeQuery
    while(rs.next) ret += rs.getString("ertek")
    conn.close
    ret
  }
  override def list =
  {
    conn = connOpen
    var ret = scala.collection.mutable.Map("" -> Set[String]()).empty
    sql = "select * from teszt"
    stmt = conn.prepareStatement(sql)
    rs = stmt.executeQuery
    while(rs.next)
    {
      var k = rs.getString("kulcs")
      var v = rs.getString("ertek")
      //ret(k) = (ret.get(k) getOrElse Set[String]()) + v
      ret(k) = ret.get(k).toSet.flatten + v
    }
    conn.close
    ret
  }
  override def set(kulcs: String, ertek: Set[String]) =
  {
    conn = connOpen
    var stmt = conn.createStatement  //lokális, nem Prepared
    (ertek -- get(kulcs)) map {ert => stmt.addBatch(s"insert into teszt values ('$kulcs', '$ert')")}
    println(stmt.executeBatch.sum + " rekord beszúrva")
    conn.close
  }
  override def remove(kulcs: String) =
  {
    conn = connOpen
    var stmt = conn.createStatement  //a PreparedStatement-nek nincs executeUpdate-je
    sql = s"delete teszt where kulcs='$kulcs'"
    println(stmt.executeUpdate(sql) + " rekord törölve")
    conn.close
  }
  override def remove(kulcs: String, ertek: Set[String]) =
  {
    conn = connOpen
    var stmt = conn.createStatement  //a PreparedStatement-nek nincs executeUpdate-je
    sql = s"delete teszt where kulcs='$kulcs' and ertek in ('${ertek.mkString("','")}')"
    println(stmt.executeUpdate(sql) + " rekord törölve")
    conn.close
  }
  override def close() = conn.close

  //init
  val ds = new oracle.jdbc.pool.OracleDataSource()
  ds.setURL("jdbc:oracle:thin:@pici:1621/xe")
  def connOpen = ds.getConnection("laca", "laca")
  var conn = connOpen

  var sql = "select count(*) db from teszt"
  var stmt = conn.prepareStatement(sql)
  var rs = stmt.executeQuery
  var numEredm = -1
  if (rs.next) numEredm = rs.getInt("db")
  println(s"nyitáskor $numEredm db rekord")

  
}