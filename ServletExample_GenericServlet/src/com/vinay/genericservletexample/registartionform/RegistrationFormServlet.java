package com.vinay.genericservletexample.registartionform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegistrationFormServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("eMail");
		String address = request.getParameter("address");
		PrintWriter printWriter = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practisedb", "admin", "admin");
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO practisedb.tblregistrationform(id,firstName,lastName,age,eMail,address)VALUES(?,?,?,?,?,?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setInt(4, age);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, address);
			int recordsInserted = preparedStatement.executeUpdate();
			if (recordsInserted != 0) {
				printWriter.print(recordsInserted + " record Inserted.Registration Successful!!!!! Changes upadted user 2 ;)");
			} else
				printWriter.print(recordsInserted + " record Inserted.Registration Failed!! ;(!!!!");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}

	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	

	
	
	
}
