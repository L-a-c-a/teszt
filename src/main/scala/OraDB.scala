class OraDB 
{
  val url = "jdbc:oracle:thin:@pici:1621/xe"
  val usr = "laca"
  val pwd = "laca"

  val ds = new oracle.jdbc.pool.OracleDataSource()
  ds.setURL(url)
  def open = ds.getConnection(usr, pwd)

}