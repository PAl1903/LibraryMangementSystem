<!DOCTYPE html>
<html>
<head>
  <title>Book Return Status</title>
  <style>
    /* Internal CSS */
    body {
      font-family: Arial, sans-serif;
      background-color:#fff;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    
    .container {
      max-width: 600px;
      padding: 20px;
      background-color: hotpink;
      border-radius: 5px;
    }
    
    h1 {
      text-align: center;
      color: #fff; /* Custom font color for heading */
    }
    
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
      margin-bottom: 20px;
    }
    
    th, td {
      padding: 8px;
      text-align: left;
    }
    
    th {
      font-weight: bold;
      color: black; /*color for column headers */
    }
    
    .success {
      color: #fff; /*color for success message */
      font-weight: bold;
      text-align: center;
    }
    
    .container table tr:nth-child(odd) {
      background-color: #fff; /* Stylish background color for odd rows */
    }
    
    .container table tr:hover {
      background-color: #fff; /* Stylish background color on hover */
    }
    

  </style>
</head>
<body>
  <div class="container">
    <h1>Book Returned Successfully</h1>
    <table>
      <tr>
        <th>Total Day</th>
        <th>Charges</th>
      </tr>
      <tr>
        <td><%=request.getAttribute("day") %></td>
        <td><%=request.getAttribute("charge") %>RS.</td>
      </tr>
    </table>
    <p class="success">Thank you for returning the book.</p>
  </div>
</body>
</html>
