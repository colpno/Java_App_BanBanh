
package datalayer;

import java.sql.*;

public class MySQLConnect {
    String Host="";
    String UserName="";
    String Password="";
    String Database="";
    
    Connection connect=null;
    Statement statement=null;
    ResultSet result=null;
    
    public MySQLConnect(String Host,String UserName,String Password,String Database){
        this.Host=Host;
        this.UserName="root";
        this.Password="X2FD39.PpSqA5IhK";
        this.Database=Database;
    }
    protected void driverTest() throws Exception{
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
       }
        catch(java.lang.ClassNotFoundException e){
           throw new Exception("MySQL JDBC Driver not found");
       }
    }
    protected Connection getConnect() throws Exception{
        if(this.connect==null){
            driverTest();
            String url="jdbc:mysql://"+this.Host+":3306/"+this.Database;
            try{
                this.connect=DriverManager.getConnection(url,this.UserName,this.Password);
            }
            catch(java.sql.SQLException e){
                throw new Exception("Cannot connect to Database");

            }
            
        }
        return this.connect;
    }
    
    protected Statement getStatement() throws Exception{
        if(this.statement==null?true:this.statement.isClosed()){
            this.statement=this.getConnect().createStatement();
        }
        return this.statement;
    }
    
    public ResultSet executeQuery(String Query) throws Exception{
        try{
            this.result=getStatement().executeQuery(Query);
        }
        catch(Exception e){
            throw new Exception("Error: "+e.getMessage()+"-"+Query);
        }
        return this.result;
    }
    
    public int executeUpdate(String Query) throws Exception{
        int res=Integer.MIN_VALUE;
        try{
            res=getStatement().executeUpdate(Query);
            
        }
        catch(Exception e){
            throw new Exception("Error: "+e.getMessage()+"-"+Query);
        }
        finally{
            this.Close();
        }
        return res;
    }
    public void Close() throws SQLException{
        if(this.result!=null&&!this.result.isClosed()){
            this.result.close();
            this.result=null;
        }
        if(this.statement!=null&&!this.statement.isClosed()){
            this.statement.close();
            this.statement=null;
        }
        if(this.connect!=null&&!this.connect.isClosed()){
            this.connect.close();
            this.connect=null;
        }
    }
    
    
}
    
