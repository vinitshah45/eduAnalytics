package com.eduanalytics.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.eduanalytics.connector.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author abc
 */
public class ReportsDAO {
    
    public JSONArray getDisciplineWiseStudentCount(int year) throws SQLException {
        DBConnector connector = new DBConnector();
        Statement statement = null;
        JSONArray array = new JSONArray();
        try {
            Connection connection = connector.getDBConnection();
            statement = connection.createStatement();
            String sql = " SELECT discipline.name,COUNT(1) FROM student "
                         + "INNER JOIN institute_course ON student.attached_course= institute_course.id "
                         + "INNER JOIN course ON institute_course.course_id=course.id  INNER JOIN"
                         + " discipline ON course.discipline_id=discipline.id ";
            if(year != 0){
                sql+=" WHERE student.passing_year= "+year;
            }
            sql+=" GROUP BY discipline.name";
            System.out.println(sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                JSONArray obj = new JSONArray();
                obj.put(res.getString(1));
                obj.put(Integer.parseInt(res.getString(2)));
                array.put(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } finally {
            connector.closeDBConnection();
        }
        return array;
    }
    
    public JSONArray getSubDisciplineWiseStudentCount(String discipline,int year) throws SQLException {
        DBConnector connector = new DBConnector();
        Statement statement = null;
        JSONArray array = new JSONArray();
        System.out.println(discipline);
        try {
            Connection connection = connector.getDBConnection();
            statement = connection.createStatement();
            String sql = " SELECT course.course_name,COUNT(1) FROM student INNER JOIN institute_course ON "
                         + "student.attached_course= institute_course.id INNER JOIN course ON institute_course.course_id=course.id"
                         + "  INNER JOIN discipline ON course.discipline_id=discipline.id "
                         + "WHERE discipline.name='"+discipline+"'";
            if(year != 0){
                sql+=" AND student.passing_year= "+year;
            }
            sql+="  GROUP BY course.course_name";
            System.out.println(sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                JSONArray obj = new JSONArray();
                obj.put(res.getString(1));
                obj.put(Integer.parseInt(res.getString(2)));
                array.put(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } finally {
            connector.closeDBConnection();
        }
        return array;
    }
    
    public JSONArray getTrainingSchemeWiseStudentCount(int year) throws SQLException {
        DBConnector connector = new DBConnector();
        Statement statement = null;
        JSONArray array = new JSONArray();
        try {
            Connection connection = connector.getDBConnection();
            statement = connection.createStatement();
            String sql = " SELECT trainingscheme.schemetype,COUNT(1) FROM student INNER JOIN institute_course"
                        + " ON student.attached_course= institute_course.id " +
                        " INNER JOIN institute ON institute_course.institute_id=institute.id " +
                        " INNER JOIN trainingscheme ON institute.training_scheme=trainingscheme.id " ;                        
            if(year != 0){
                sql+=" WHERE student.passing_year= "+year;
            }
            sql+=" GROUP BY trainingscheme.schemetype";
            System.out.println(sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                JSONArray obj = new JSONArray();
                obj.put(res.getString(1));
                obj.put(Integer.parseInt(res.getString(2)));
                array.put(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } finally {
            connector.closeDBConnection();
        }
        return array;
    }
    
    public JSONArray getSubTrainingSchemeWiseStudentCount(String schemetype,int year) throws SQLException {
        DBConnector connector = new DBConnector();
        Statement statement = null;
        JSONArray array = new JSONArray();
        System.out.println(schemetype);
        try {
            Connection connection = connector.getDBConnection();
            statement = connection.createStatement();
            String sql = "SELECT trainingscheme.name,COUNT(1) FROM student INNER JOIN institute_course ON "
                        + "student.attached_course= institute_course.id " +
                        " INNER JOIN institute ON institute_course.institute_id=institute.id " +
                        " INNER JOIN trainingscheme ON institute.training_scheme=trainingscheme.id " +
                        " WHERE trainingscheme.schemetype='"+schemetype+"'";
            if(year != 0){
                sql+=" AND student.passing_year= "+year;
            }
            sql+=" GROUP BY trainingscheme.name";
            System.out.println(sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                JSONArray obj = new JSONArray();
                obj.put(res.getString(1));
                obj.put(Integer.parseInt(res.getString(2)));
                array.put(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } finally {
            connector.closeDBConnection();
        }
        return array;
    }
    
    
    public JSONArray getYearWiseInstituteCount() throws SQLException {
        DBConnector connector = new DBConnector();
        Statement statement = null;
        JSONArray array = new JSONArray();
        try {
            Connection connection = connector.getDBConnection();
            statement = connection.createStatement();
            String sql = "SELECT DISTINCT(schemetype) as schemetype FROM trainingscheme";
            System.out.println(sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                String schemetype=res.getString(1);
                System.out.println(schemetype);
                JSONObject jSONObject = new JSONObject();
                JSONArray yearArray = new JSONArray();
                for(int i=2000;i<=2016;i++){
                    String yearSQL = "SELECT COUNT(1) FROM institute INNER JOIN trainingscheme ON "
                                     + "institute.training_scheme = trainingscheme.id WHERE schemetype='"+schemetype+"' AND established_year=2000";
                    res = statement.executeQuery(sql);
                    while(res.next()){
                        
                    }
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } finally {
            connector.closeDBConnection();
        }
        return array;
    }
    
    
    public static void main(String[] args) throws Exception{
        System.out.println(new ReportsDAO().getYearWiseInstituteCount());
    }
    
}