package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.Book;
import bean.User;

/**
 * プログラム名：書籍管理システムWeb版 Ver1.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0のデータベース関連を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/05/23
 *
 * @author Globalway
 *
 */
public class UserDAO {

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

	//userinfoテーブルから指定ユーザーとパスワードの条件に合致する情報を取得するメソッド
	public User selectByUser(String userid, String password) {

		//コネクションを格納するための変数
		Connection con = null;
		//SQL文を実行するための変数
		Statement smt = null;

		User user = new User();

		try{
			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "' AND password ='" + password +"'";

			//コネクション取得
			con = getConnection();
			//SQLをデータベースに送るための準備
			smt = con.createStatement();

			//SQLをデータベースへ送信し、結果をrs変数に格納
			ResultSet rs = smt.executeQuery(sql);

			//bookオブジェクトにisbn、title、priceを格納し、bookListに追加
			while(rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setAuthority(rs.getString("authority"));
			}

		}catch(Exception e){
			System.out.println(e);
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}
			if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		//bookListを呼び出し元に返す
		return user;

	}


}
