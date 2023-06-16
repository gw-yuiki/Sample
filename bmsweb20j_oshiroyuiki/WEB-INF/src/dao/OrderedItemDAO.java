package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.OrderedItem;

/**
 * プログラム名：書籍管理システムWeb版 Ver1.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0のデータベース関連を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/05/23
 *
 * @author Globalway
 *
 */
public class OrderedItemDAO {

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

	//orderinfoとbookinfoテーブルを結合して購入情報を取得するメソッド
	public ArrayList<OrderedItem> selectAll() {

		//コネクションを格納するための変数
		Connection con = null;
		//SQL文を実行するための変数
		Statement smt = null;

		ArrayList<OrderedItem> orderedList = new ArrayList<OrderedItem>();

		try{
			String sql = "SELECT o.user, b.title, o.date FROM bookinfo b INNER JOIN orderinfo o ON b.isbn= o.isbn";

			//コネクション取得
			con = getConnection();
			//SQLをデータベースに送るための準備
			smt = con.createStatement();

			//SQLをデータベースへ送信し、結果をrs変数に格納
			ResultSet rs = smt.executeQuery(sql);

			//orderedオブジェクトにisbn、title、priceを格納し、orderedListに追加
			while(rs.next()) {
				OrderedItem ordered = new OrderedItem();
				ordered.setUserid(rs.getString("user"));
				ordered.setTitle(rs.getString("title"));
				ordered.setDate(rs.getString("date"));
				orderedList.add(ordered);
			}

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
		return orderedList;
	}


}
