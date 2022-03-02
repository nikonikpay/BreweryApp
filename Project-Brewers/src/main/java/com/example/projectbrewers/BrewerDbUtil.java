package com.example.projectbrewers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BrewerDbUtil {

    private DataSource dataSource;

    public BrewerDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Brewer> getBrewers() throws Exception {
        List<Brewer> brewers = new ArrayList<>();

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        //1-get a connection
        //2-create a sql statement
        //3- exceute query
        //4- process result Set
        //5-close JDBC object

        try{

            //1
            myConnection = dataSource.getConnection();

            //2
            String sql ="select * from Brewers";
            myStatement = myConnection.createStatement();

            //3
            myResultSet = myStatement.executeQuery(sql);

            //4
            while(myResultSet.next()){
                //1 retrieve data from ResultSet
                //2-create new Brewer object
                //3- add to the list of brewers

                //1-
                int id = myResultSet.getInt("id");
                String name = myResultSet.getString("Name");
                String address = myResultSet.getString("Address");
                String zipCode = myResultSet.getString("ZipCode");
                String city= myResultSet.getString("City");
                int turnOver = myResultSet.getInt("Turnover");

                //2
                Brewer brewer = new Brewer(id,name,address,zipCode,city,turnOver);

                //3
                brewers.add(brewer);



            }

            return brewers;

        }finally {
            close(myConnection,myStatement,myResultSet);
        }

    }

    private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {

        try{
            if(myResultSet!=null){
                myConnection.close();
            }
            if(myStatement!=null){
                myStatement.close();
            }
            if(myConnection != null){
                myConnection.close();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addBrewer(Brewer theBrewer) throws Exception {

        //1- get db connection and create sql insert
        //2-set the param values for the brewer
        //3- execute sql insert
        //4-clean up JDBC objects

        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {

            //1
            myConnection = dataSource.getConnection();
            String sql = "insert into Brewers " + "(Name, Address, Zipcode, City, Turnover) " + "values (?, ?, ?,?,?)";

            //2-
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setString(1,theBrewer.getName());
            myStatement.setString(2, theBrewer.getAddress());
            myStatement.setString(3, theBrewer.getZipCode());
            myStatement.setString(4, theBrewer.getCity());
            myStatement.setInt(5,theBrewer.getTurnOver());

            //3
            myStatement.execute();


        } finally {
            //4
            close(myConnection, myStatement, null);

        }

    }

    public Brewer getBrewer(String theBrewerId) throws Exception {

        Brewer theBrewer = null;
        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;
        int brewerId;


        try {
            //1-convert brewer id to int
            // 2-get connection to database
            //3- create sql to get selected brewer
            //4-create prepaid statement
            //5-set params
            //6- execute statement
            //7- retrieve data from result set
            //8- clean up and close connections


            //1
            brewerId = Integer.parseInt(theBrewerId);

            //2
            myConnection = dataSource.getConnection();


            //3
            String sql = "select * from Brewers where id=?";


            //4
            myPreparedStatement = myConnection.prepareStatement(sql);


            //5
            myPreparedStatement.setInt(1, brewerId);

            //6
            myResultSet = myPreparedStatement.executeQuery();

            //7
            if (myResultSet.next()) {
                String name = myResultSet.getString("Name");
                String address = myResultSet.getString("Address");
                String zipCode = myResultSet.getString("Zipcode");
                String city = myResultSet.getString("City");
                int turnOver = myResultSet.getInt("Turnover");

                //use the Student id during construction
                theBrewer = new Brewer(brewerId, name, address, zipCode,city,turnOver);

            } else {
                throw new Exception("Could not find brewer id: " + brewerId);
            }


            return theBrewer;
        } finally {

            //8
            close(myConnection, myPreparedStatement, myResultSet);
        }

    }

    public void updateBrewer(Brewer brewer) throws Exception {

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;

        //1-get db connection
        //2- create Sql update statement
        //3- prepare statement
        //4- set params
        //5-execute SQL statement

        try {
            //1
            myConnection = dataSource.getConnection();

            //2
            String sql = "update Brewers " + "set Name=?, Address=?, Zipcode=?, City=?, Turnover=? " + "where id=?";


            //3
            myPreparedStatement = myConnection.prepareStatement(sql);

            //4
            myPreparedStatement.setString(1, brewer.getName());
            myPreparedStatement.setString(2, brewer.getAddress());
            myPreparedStatement.setString(3, brewer.getZipCode());
            myPreparedStatement.setString(4, brewer.getCity());
            myPreparedStatement.setInt(5,brewer.getTurnOver());
            myPreparedStatement.setInt(6, brewer.getId());


            //5
            myPreparedStatement.execute();
        } finally {
            close(myConnection, myPreparedStatement, null);
        }

    }

    public void deleteStudent(String brewerId) throws Exception {

        Connection myConnection = null;
        PreparedStatement myPreparedStatement = null;

        try {
            //1-convert brewer id to int
            //2-get connection to database
            //3-create sql to delete brewer
            //4-prepare statement
            //5-set params
            //6-execute sql statement
            //7-clean up jdbc


            //1
            int theBrewerId = Integer.parseInt(brewerId);

            //2-
            myConnection = dataSource.getConnection();

            //3-
            String sql = "delete from Brewers where id=?";

            //4
            myPreparedStatement = myConnection.prepareStatement(sql);

            //5
            myPreparedStatement.setInt(1, theBrewerId);


            //6
            myPreparedStatement.execute();


        } finally {
            //7-

            close(myConnection, myPreparedStatement, null);
        }

    }
}
