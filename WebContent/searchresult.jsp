<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page session = "false" %>
	<%@ page import = "in.library.persistence.*"%>
	<%@ page import="in.library.dto.*" %>
	<%@ page import="java.util.*" %>
	<% HttpSession session = request.getSession(false);
		if(session==null) {
			Cookie ck=new Cookie("msg","LoginFirst"); 
			response.addCookie(ck);
			response.sendRedirect("/BookManagementSystem/validate/login");
			return;
		}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	background: -webkit-linear-gradient(left, #a445b2, #fa4299);
}

/* table {
	margin-top: 60px;
	background: white;
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
} */
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
	<a href="/BookManagementSystem/library" style="color: white; text-decoration: none">
	<button class="logoutLblPos">
		Back
	</button>
	</a>
	<center>
		<div style="width: 70%">
			<table>
				<tr style="font-size: 20px">
					<th>ISBN NO</th>
			    	<th>Book Name</th>
					<th>Book AuthorName</th>
				</tr>
				<%
				
				String query=request.getParameter("query");
					Service service=new ServiceImpl();
					ArrayList<book> result=service.search(query);
					if(result.size()<1){
						out.println("Sorry No record found!");
					}
					else{
						for(int i=0;i<result.size();i++){
							%><tr>
							<td><%=result.get(i).getISBN()%></td>
							<td><%= result.get(i).getName()%></td>
							<td><%=result.get(i).getAuthorname()%></td>
						</tr><% 
						}
					}
				%>
				
				
			</table>
		</div>
	</center>
</body>
</html>