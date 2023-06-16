package dao;

import java.sql.*;
//import java.util.ArrayList;

import bean.Order;
//import bean.User;

/**
 * プログラム名：書籍管理システムWeb版 Ver1.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0のデータベース関連を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/05/23
 *
 * @author Globalway
 *
 */
public class OrderDAO {

	//データベース接続情報
	private static String RDB_DRIVE ="com.mysql.jdbc.Driver";
	private static String URL ="jdbc:mysql://localhost/mybookdb";
	private static String USER ="root";
	private static String PASS ="root123";

	//データベースとの接続を行うためのオブジェクト生成
	private static Connection getConnection(){
		Connection con = null;
		try{
			//JDBCドライバーをロード
			Class.forName(RDB_DRIVE);
			//Connectionオブジェクトを生成
			con = DriverManager.getConnection(URL, USER, PASS);
			//Connectionオブジェクトをリターン
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	//引数の購入データを元にDBのorderinfoテーブルに新規登録処理を行うメソッド
	public void insert(Order order) {

		//コネクションを格納するための変数
		Connection con = null;
		//SQL文を実行するための変数
		Statement smt = null;

		try{
			String sql = "INSERT INTO orderinfo VALUES(NULL, '" + order.getUserid() + "','" + order.getIsbn() + "','" + order.getQuantity() + "', CURDATE())";

			con = getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}
			if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
	}


}
