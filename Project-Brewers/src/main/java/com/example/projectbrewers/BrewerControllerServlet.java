package com.example.projectbrewers;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BrewerControllerServlet", value = "/BrewerControllerServlet")
public class BrewerControllerServlet extends HttpServlet {

    private BrewerDbUtil brewerDbUtil;

    @Resource(name = "jdbc/brewersApp")
    private DataSource dataSource;


    @Override
    public void init() throws ServletException {
        super.init();

        // create a brewer databaseUtil and pass it to the connection pool
        try {
            brewerDbUtil = new BrewerDbUtil(dataSource);
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //list brewers in MVC pattern
        try {
            String theCommand = request.getParameter("command");


            //if the command is missing , then default to listing student
            if(theCommand == null) {
                theCommand = "LIST";
            }


            //route to appropiate method
            switch(theCommand) {
                case "LIST":
                    listBrewer(request, response);
                    break;

                case "ADD":
                    addBrewer(request, response);
                    break;

                case "LOAD":
                    loadBrewer(request, response);

                case "UPDATE":
                    updateBrewer(request, response);

                case "DELETE":
                    deleteBrewer(request, response);

                default:
                    listBrewer(request, response);
            }

        }catch(Exception e){
            throw new ServletException(e);
        }


    }

    private void deleteBrewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1-read Brewer info from form data
        //2- delete Brewer from db
        //3-send them back to the list Brewer page

        //1
        String brewerId = request.getParameter("brewerId");

        //2
        brewerDbUtil.deleteStudent(brewerId);

        listBrewer(request, response);
    }

    private void updateBrewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1-read brewer info from form data
        //2- create a new brewer object
        //3-perform update on database
        //4-send them back to the list students page

        //1
        int id = Integer.parseInt(request.getParameter("brewerId"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        int turnOver = Integer.parseInt(request.getParameter("turnOver"));



        //2-
        Brewer brewer = new Brewer(id, name, address, zipCode,city,turnOver);


        //3
        brewerDbUtil.updateBrewer(brewer);

        //4
        listBrewer(request, response);

    }

    private void loadBrewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1- read brewer id from form data
        //2-get brewer from database(db util)
        //3-place brewer in the request attribute
        //4-send to jsp page: update-brewer-form.jsp


        //1
        String theBrewerId = request.getParameter("brewerId");

        //2-
        Brewer theBrewer = brewerDbUtil.getBrewer(theBrewerId);

        //3-
        request.setAttribute("THE_BREWER", theBrewer);

        //4-
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-brewer-form.jsp");
        dispatcher.forward(request, response);


    }

    private void addBrewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1-read brewer info from form data
        //2- create a new brewer object
        //3-add the brewer to the database
        //4-send back to main  page(the brewer list)

        //1
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");
        int turnOver = Integer.parseInt(request.getParameter("turnOver")) ;

        //2
        Brewer theBrewer = new Brewer(name,address,zipCode,city,turnOver);

        //3
        brewerDbUtil.addBrewer(theBrewer);

        //4
        listBrewer(request, response);
    }

    private void listBrewer(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1- get brewer from dbutil
        //2- add brewer to the request
        //3-send to JSP page (view)

        //1
        List<Brewer> brewers = brewerDbUtil.getBrewers();

        //2-
        request.setAttribute("BREWER_LIST",brewers);

        //3
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-brewers.jsp");
        dispatcher.forward(request,response);


    }


}
