/* DBHandler for SQLite
 * Kim TaeHee
 * First Created 121204 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GDBHandler {
	String sqlStr;
	public void init() throws Exception {
		Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Gesitoring_JavaServer.db3");
        Statement stat = conn.createStatement();
        
        //유의 executeQuery는 반환값이 있을 때, executeUpdate는 반환값이 없는 질의어에 사용
        //stat.executeUpdate("DROP TABLE sitelist");
        
        sqlStr = "CREATE TABLE IF NOT EXISTS sitelist (s_sitename TEXT PRIMARY KEY, s_url TEXT NOT NULL UNIQUE," +
        		" s_user_set_enable INTEGER NOT NULL, s_last_access_time TEXT, s_last_modified_time TEXT, s_refresh_cycle INTEGER, " +
        		"s_length INTEGER, s_contents TEXT, s_updated_contents TEXT, s_available INTEGER, s_user_unchecked INTEGER, s_time_to_check TEXT);";
        		
        System.out.println("SQL: "+sqlStr);
        stat.executeUpdate(sqlStr);
        
        sqlStr = "CREATE TABLE IF NOT EXISTS programoption (p_option TEXT PRIMARY KEY, p_value INTEGER, p_remark TEXT);";
        System.out.println("SQL: "+sqlStr);
        stat.executeUpdate(sqlStr);
        
//        stat.executeUpdate("INSERT INTO sitelist (s_sitename, s_url, s_user_set_enable, s_refresh_cycle) " +
//        		"VALUES ('게시터링','http://203.246.81.237/~ec/xe/index.php?mid=ec_gesitoring', 1, 3);"
//        		);

        String url = null;
        
        sqlStr = "SELECT * FROM SITELIST;";
        System.out.println("SQL: "+sqlStr);
        ResultSet rs = stat.executeQuery(sqlStr);
        while (rs.next()) {
            System.out.println("sitename = " + rs.getString("s_sitename"));
            url = rs.getString("s_url");
            System.out.println("url = " + url);
            System.out.println("cycle = " + rs.getString("s_refresh_cycle"));
        }
        
        GHTTPRequest.request(url); //TODO 테스트임
        
        rs.close();
        conn.close();
    }
}

/*참고
//stat.executeUpdate("create table people (name, occupation);");
//PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");
//
//prep.setString(1, "Gandhi");
//prep.setString(2, "politics");
//prep.addBatch();
//prep.setString(1, "Turing");
//prep.setString(2, "computers");
//prep.addBatch();
//prep.setString(1, "Wittgenstein");
//prep.setString(2, "smartypants");
//prep.addBatch();

//conn.setAutoCommit(false);
//prep.executeBatch();
//conn.setAutoCommit(true);*/