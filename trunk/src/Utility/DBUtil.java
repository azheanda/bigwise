
package BigWise.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static String DbDriver = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://127.0.0.1:3306/BigWise";
	private static String User = "root";              //���ݿ�ĵ�½��
	private static String Pwd = "880827";              //���ݿ�ĵ�½����
	/*
	 *�õ�һ��Connection����
	 *@return java.sql.Connection
	 */
	public static Connection getDbConn() {
		Connection conn=null;
		try
		{
			Class.forName(DbDriver);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
		try {
			conn = DriverManager.getConnection(URL, User, Pwd);
			//System.out.println("�������ݿ�ɹ�");
			return conn;
		} catch (Exception e) {
			System.out.println("��������ʧ�ܣ�");
			return null;
		}
	}

	//�ر�ָ���Ľ����rs
	   public static void closeResultSet(ResultSet rs){
	       if(rs!=null){
	           try{
	               rs.close();
	           }catch(SQLException e){
	               e.printStackTrace();
	           }
	       }
	   }

	   //�ر�ָ����Statement
	   public static void closeStatement(Statement stmt){
	       if(stmt!=null){
	           try{
	               stmt.close();
	           }catch(SQLException e){
	               e.printStackTrace();
	           }
	       }
	   }

	   //�ر�����conn
	   public static void closeConnection(Connection conn){
	       if(conn!=null){
	           try{
	               conn.close();
	               //System.out.println("�ر����ݿ�ɹ���");
	           }catch(SQLException e){
	               e.printStackTrace();
	           }
	       }
	   }

	public static void main(String[] args){
		Connection conn = DBUtil.getDbConn();
		DBUtil.closeConnection(conn);
	}
}