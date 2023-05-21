package in.library.persistence;
import java.text.ParseException;
import java.sql.Statement;
import java.text.SimpleDateFormat;  

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import in.library.connection.JDBC;
import in.library.dto.book;
import in.library.dto.student;

public class ServiceImpl implements Service {
	Connection connection;
	PreparedStatement pstmt;
	ResultSet res;
	String username;
	String Password;
	@Override
	public int[] login(String username, String Password) {
		try {
			connection=JDBC.getConnection();
			String sql="SELECT * FROM login_detail WHERE emailid= ? and password=?";
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, Password);
			 res = pstmt.executeQuery();
			 System.out.println(res);
			 while(res.next()) {
				 if(res.getString("emailid").equals(username)&&res.getString("password").equals(Password) ) {
					int id=Integer.parseInt(res.getString("id"));
					System.out.println("role is "+res.getString("role"));
					int role=Integer.parseInt(res.getString("role"));
					int []res= {id,role};
					 return res;
				 }
				 
				 
			 }
			return new int[2];
			} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					JDBC.cleanUp(connection, pstmt, res);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return new int[2];
		
	}
	public int signupvalidate(String username) {
		try {
			connection=JDBC.getConnection();
			String sql="SELECT * FROM login_detail WHERE emailid= ? ";
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, username);
			 res = pstmt.executeQuery();
			 System.out.println(res);
			 while(res.next()) {
				 if(res.getString("emailid").equals(username) ) {
					
					 return 0;
				 }
				 
				 
			 }
			return 1;
			} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return 1;
		
	}

	@Override
	public int signup(String useremailid, String Password) {
		 try {
			connection=JDBC.getConnection();
			if(signupvalidate(useremailid)==0)return 0;
			String sql="INSERT INTO login_detail(emailid,password) VALUES (?,?)";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, useremailid);
			pstmt.setString(2, Password);
			
			int result = pstmt.executeUpdate();
			
			return result;
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 try {
		 
			JDBC.cleanUp(connection, pstmt,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 
		 return 0;
	}

	@Override
	public ArrayList<book> search(String query) {
		ArrayList<book>result=new ArrayList<>();
		book b;
		try {
			Connection connection=JDBC.getConnection();
			String sql="select * from book_detail where name like ? OR authorname like ? order by isbn";
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,query+"%");
			pstmt.setString(2,query+"%");
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				b=new book();
				System.out.println(res.getString("isbn"));
				b.setISBN(Integer.parseInt(res.getString("isbn")));
				b.setName(res.getString("name"));
				b.setAuthorname(res.getString("authorname"));
				result.add(b);
				
			}
			return result;
			
			
		}catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 try {
		 
			JDBC.cleanUp(connection, pstmt,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

	}

	@Override
	public ArrayList<book> booklist() {
		ArrayList<book>result=new ArrayList<>();
		book b;
		try {
			Connection connection=JDBC.getConnection();
			String sql="select * from book_detail";
			pstmt=connection.prepareStatement(sql);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				b=new book();
				System.out.println(res.getString("isbn"));
				b.setISBN(Integer.parseInt(res.getString("isbn")));
				b.setName(res.getString("name"));
				b.setAuthorname(res.getString("authorname"));
				b.setStatus(Integer.parseInt(res.getString("status")));
				result.add(b);
				
			}
			return result;
			
			
		}catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 try {
		 
			JDBC.cleanUp(connection, pstmt,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	}

	@Override
	public ArrayList<student> studentlist() {
		ArrayList<student>result=new ArrayList<>();
		student s;
		try {
			Connection connection=JDBC.getConnection();
			String sql="select id,emailid,password from login_detail where role=0";
			pstmt=connection.prepareStatement(sql);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				s=new student();
				s.setUserid(res.getString("emailid"));
				s.setPassword(res.getString("password"));
				s.setId(Integer.parseInt(res.getString("id")));;
				result.add(s);
				
			}
			return result;
			
			
		}catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally{
			 try {
		 JDBC.cleanUp(connection, pstmt,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}

	@Override
	public int issuebook(int isbn, int sid) {
		
		String sql="Insert into Issuebook(isbn,sid,issuedate) values(?,?,?)";
		
		 try {
		     System.out.print("HII1");

				connection=JDBC.getConnection();
			     System.out.print("HII1");

				pstmt = connection.prepareStatement(sql);

				 long millis=System.currentTimeMillis();  
			     System.out.print("HII1");

			     java.sql.Date date=new java.sql.Date(millis);  
			     
			     System.out.print("HII1");
				pstmt.setInt(1, isbn);
				pstmt.setInt(2, sid);
				System.out.println("sucessfully executed");

				pstmt.setDate(3,date);

				System.out.println("after date");
				int result = pstmt.executeUpdate();
				return result;
				
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 finally{
				 try {
			 
				JDBC.cleanUp(connection, pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			 
			 return 0;
		}

	@Override
	public long returnbook(int isbn, int sid) {
		
		String sql="select * from Issuebook where isbn="+isbn+" and sid="+sid;
		String sql2="delete from Issuebook where isbn="+isbn+" and sid="+sid;

		
		 try {

				connection=JDBC.getConnection();

				Statement stmt = connection.createStatement();
				 long millis=System.currentTimeMillis();  
			     
				


				  ResultSet res = stmt.executeQuery(sql);
				  long days_difference=0L;
				  java.sql.Date date2;
				while(res.next()) {
					  date2 = res.getDate("issuedate");
//					  res.deleteRow();
					  	long time_difference = date2.getTime() - millis;  
						 days_difference = (time_difference / (1000*60*60*24)) % 365; 
				  } 
				  stmt.executeUpdate(sql2);

			


				return days_difference;
				
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 finally{
				 try {
			 
				JDBC.cleanUp(connection, pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			 
			 return -1;

	}

	@Override
	public int addbook(book b) {

		 try {
				connection=JDBC.getConnection();
				String sql="INSERT INTO book_detail(isbn,name,authorname) VALUES (?,?,?)";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1,b.getISBN() );
				pstmt.setString(2,b.getName());
				pstmt.setString(3, b.getAuthorname());
				
				int result = pstmt.executeUpdate();
				
				return result;
				
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 finally{
				 try {
			 
				JDBC.cleanUp(connection, pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			 
			 return 0;
	}

	@Override
	public int deletebook(int isbn) {
		 try {
				connection=JDBC.getConnection();
				String sql="delete from book_detail where isbn=?";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1,isbn);
				
				
				int result = pstmt.executeUpdate();
				
				return result;
				
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 finally{
				 try {
			 
				JDBC.cleanUp(connection, pstmt,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			 
			 return 0;	
			}
}
