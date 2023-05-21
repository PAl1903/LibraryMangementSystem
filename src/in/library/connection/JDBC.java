package in.library.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {
	private JDBC() {}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException c) {
			System.out.println("Sql Driver is not loaded properly");
		}
	}
	public static Connection getConnection() throws IOException, SQLException {
//		FileInputStream is=new FileInputStream("/Users/pal/CollegeJavaProject/BookManagementSystem/application.properties");
//		Properties properties =new Properties();
//		properties.load(i
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem",
				"root", "");
		System.out.println("Connection object was created ");
		return connection;
		
	}
	
	public static void cleanUp(Connection con, PreparedStatement pstmt, ResultSet resultSet) throws SQLException {
		// Step6. Close the resources
		if (con != null) {
			con.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
