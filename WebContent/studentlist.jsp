<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import = "in.library.persistence.*"%>
	<%@ page session = "false" %>
	<%@ page import="in.library.dto.*" %>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
</head>
<style>
@import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');
        *{
            
            font-family: 'Poppins', sans-serif;
        }
body {
	background:white;
}
table{
 position: absolute;
 z-index: 2;
 left: 50%;
 top: 50%;
 transform: translate(-50%,-50%);
 width: 60%; 
 border-collapse: collapse;
 border-spacing: 0;
 box-shadow: 0 2px 15px rgba(64,64,64,.7);
 border-radius: 12px 12px 0 0;
 overflow: hidden;

}
td , th{
 padding: 15px 20px;
 text-align: center;
 

}
th{
 background-color: #ba68c8;
 color: #fafafa;
 font-family: 'Open Sans',Sans-serif;
 font-weight: 200;
 text-transform: uppercase;

}
tr{
 width: 100%;
 background-color: #fafafa;
 font-family: 'Montserrat', sans-serif;
}

tr:nth-child(even){
 background-color: #eeeeee;
}

.logoutLblPos {
	position: fixed;
	right: 30px;
	top: 25px;
	background-color: #4CAF50; /* Green */
	border: none;
	border-radius: 20px;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 20px;
}
</style>
<body>
		<center>
		<div style="width: 70%">
			<table>
				<tr style="font-size: 20px">
					<th>ID</th>
					<th>Email Id</th>
			    	<th>Password</th>
				</tr>
				<%
					Service service=new ServiceImpl();
					ArrayList<student> result=service.studentlist();
					if(result.size()<1){
						out.println("Sorry No record found!");
					}
					else{
						for(int i=0;i<result.size();i++){
							%><tr>
							<td><%= result.get(i).getId() %></td>
							<td><%=result.get(i).getUserid()%></td>
							<td><%= result.get(i).getPassword()%></td>
						</tr><% 
						}
					}
				%>
				
				
			</table>
		</div>
	</center>
</body>
</html>