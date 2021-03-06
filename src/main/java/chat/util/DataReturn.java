package main.java.chat.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//Take information out of the database and put it into a file
public class DataReturn {


        public static void main(String[] args) {
                String url = "jdbc:mysql://cosc304.ok.ubc.ca/db_group5";
                String uid = "oyesufu";
                String pw = "38455101";




                try {// Load driver class
                        Class.forName("com.mysql.jdbc.Driver");
                }
                catch (java.lang.ClassNotFoundException e) {
                        System.err.println("ClassNotFoundException: " +e);
                }


                Connection con = null;
                try {
                        con = DriverManager.getConnection(url, uid, pw);
                        Statement stmt = con.createStatement();
                        ResultSet rst = stmt.executeQuery("SELECT * FROM Keywords, Responses WHERE Keywords.id = Responses.ref_id");
                        System.out.println("id, keywords, type, sentence_location, word_location, weight");
                        int curId = 0;
                        
                        try {
                                PrintWriter out = new PrintWriter("filename.txt");
                                while (rst.next()){
                                        
                                        String s = "";


                                        if (curId != rst.getInt("id")){
                                                s += "\\n"+rst.getString("keywords")+"#"+rst.getString("type")+"#"
                                                                +rst.getString("sentence_location")+"#"+rst.getString("word_location")+"#"+rst.getInt("weight")+"#[R]"
                                                                +rst.getString("Responses.keywords")+"\\\\"+rst.getString("question")+"\\\\"+rst.getString("Responses.response")+"\\\\";
                                                out.print(s);
                                        }else{
                                                s += "[R]"+rst.getString("Responses.keywords")+"\\\\"+rst.getString("question")+"\\\\"+rst.getString("Responses.response")+"\\\\";
                                                out.print(s);
                                        }
                                        
                                        curId = rst.getInt("id");
                                        
                                }
                                
                                out.close();
                                        
                                } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                }
                                
                        
                }
                catch (SQLException ex)
                {
                        System.err.println("SQLException: " + ex);
                }
                finally
                {
                        if (con != null)
                                try
                                {
                                        con.close();
                                }
                                catch (SQLException ex)
                                {
                                        System.err.println("SQLException: " + ex);
                                }
                }
        }


}