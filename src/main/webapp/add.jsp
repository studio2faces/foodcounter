
<html>
   <head><title>Hello World</title></head>

   <body>
      <%
            out.println("<h1> Hello, " + request.getAttribute("login") + "!</h1>");
            out.println("<p> UUID: " + request.getAttribute("uuid").toString() + "</p>");
      %>

      <h1>Add a product to the fridge:</h1>
      <br>
      <form action="AddAndShowServlet" method="post">
          Product name <input type="text" name="name">
          <br>
          <br>
          Weight, g <input type="text" name="weight">
           <br>
           <br>
          Price, Rub <input type="text" name="price">
           <br>
           <br>
          Calories/100g <input type="text" name="kcal">
           <input type="hidden" name="users_uuid" value=<%=request.getAttribute("uuid")%>>
           <br>
           <br>
          <input type="submit" value="Add to fridge">
      </form>

      <br>
      <a href="AddAndShowServlet">Show all products</a>

   </body>
</html>