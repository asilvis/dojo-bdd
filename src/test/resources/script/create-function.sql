CREATE ALIAS IF NOT EXISTS truncateTables AS $$
ResultSet fpadsetoresasc(java.sql.Connection con) throws Exception {
	StringBuilder sql = new StringBuilder();
	sql.append("	SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'TABLE' AND TABLE_SCHEMA = 'PUBLIC'");
	
        con.createStatement().executeQuery(sql.toString());
        
        ResultSet rs = con.createStatement().executeQuery(sql.toString());
        con.createStatement().executeUpdate("SET REFERENTIAL_INTEGRITY FALSE");
        while(rs.next()) { 
                String tableName = rs.getString(1);
                try {
                	con.createStatement().executeUpdate("TRUNCATE TABLE " + tableName);
				} catch (Exception e) {
				}
        }
     rs.close();
    sql = new StringBuilder();
    sql.append("	SELECT sequence_name FROM INFORMATION_SCHEMA.SEQUENCES");
	
        con.createStatement().executeQuery(sql.toString());
        
        rs = con.createStatement().executeQuery(sql.toString());
        while(rs.next()) { 
                String tableName = rs.getString(1);
                try {
                	con.createStatement().executeUpdate("ALTER SEQUENCE " + tableName + " RESTART WITH 1");
				} catch (Exception e) {
				}
        }
 
	return rs;	
}
$$;