package com.example.projectbrewers;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.System.out;

@WebServlet(name = "ServletTest", value = "/ServletTest")
public class ServletTest extends HttpServlet {

    @Resource(name = "jdbc/brewersApp")
    private DataSource dataSource;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1- setup printer writer
        //2- get a connection database
        //3-create a sql statement
        //4-execute sql
        //5-process the result set


        //1
        PrintWriter writer = response.getWriter();
        response.setContentType("text/plain");


        //2
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {

            myConnection = dataSource.getConnection();

            //3
            myStatement = myConnection.createStatement();
            String sql = "select * from Brewers";

            //4
            myResultSet = myStatement.executeQuery(sql);

            while(myResultSet.next()) {
                String city = myResultSet.getString("City");
                writer.println(city);
            }


        } catch(Exception e) {
            e.printStackTrace();
        }


    }


}
