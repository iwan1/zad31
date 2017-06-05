package pl.edu.uksw.zad31;

import java.sql.*;

public class App 
{
	static final String DB_URL = "jdbc:mysql://ricky.heliohost.org/cackoa_database";
	static final String USER = "cackoa_user";
	static final String PASS = "cackoa_password";
	
    public static void main( String[] args )
    {
    	 Connection conn = null;
         Statement stmt = null;
         
         
         try{
             // Połączenie z bazą danych
             System.out.println("Connecting to database...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
  
             // Stworzenie zapytania oraz jego wykonanie
             System.out.println("Creating statement...");
             stmt = conn.createStatement();
             String sql, sql2;
             sql = "SELECT * FROM  student";
             sql2 = "INSERT INTO student VALUES (99999, 'Maciej', 'Iwanski', 4)";
             stmt.executeUpdate(sql2);
             ResultSet rs = stmt.executeQuery(sql);
  
             // Ekstrakcja wyników
             while(rs.next()){
                 //Retrieve by column name
                 int id  = rs.getInt("id");
                 String name = rs.getString("name");
                 String surname = rs.getString("surname");
                 int student_group = rs.getInt("student_group");
  
                 //Wyświetlenie danych:
                 System.out.print("ID: " + id);
                 System.out.print(", name: " + name);
                 System.out.print(", surname: " + surname);
                 System.out.println(", student_group: " + student_group);
             }
             // Czyszczenie po sobie
             rs.close();
             stmt.close();
             conn.close();
         }catch(SQLException se){
             //Errory JDBC
             se.printStackTrace();
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             //finally block - by pozamykać resources
             try{
                 if(stmt!=null)
                     stmt.close();
             }catch(SQLException se2){
             }// nothing we can do
             try{
                 if(conn!=null)
                     conn.close();
             }catch(SQLException se){
                 se.printStackTrace();
             }//end finally try
         }//end try
         System.out.println("Koniec!");
    }
}
