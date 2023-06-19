package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.Book;

/**
 * プログラム名：書籍管理システムWeb版 Ver2.0
 * プログラムの説明：書籍管理システムWeb版 Ver1.0のデータベース関連を記述した処理
 * 作成者：大城百生
 * 作成日付：2023/06/12
 *
 * @author Globalway
 *
 */
public class BookDAO {

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

	//bookinfoテーブルから全書籍情報を取得するメソッド
	public ArrayList<Book> selectAll(){

		//コネクションを格納するための変数
		Connection con = null;
		//SQL文を実行するための変数
		Statement smt = null;

		ArrayList<Book> bookList = new ArrayList<Book>();

		try{
			String sql = "SELECT isbn, title, price FROM bookinfo ORDER BY isbn";

			//コネクション取得
			con = getConnection();
			//SQLをデータベースに送るための準備
			smt = con.createStatement();

			//SQLをデータベースへ送信し、結果をrs変数に格納
			ResultSet rs = smt.executeQuery(sql);

			//bookオブジェクトにisbn、title、priceを格納し、bookListに追加
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
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
		return bookList;
	}

	//引数のISBNを元にDBのbookinfoテーブルから該当書籍データの検索を行うメソッド
	public Book selectByIsbn(String isbn){

		Connection con = null;
		Statement smt = null;

		//検索した書籍情報を格納するBookオブジェクトを生成
		Book book = new Book();

		try{
			//検索用SQL文
			String sql = "SELECT isbn, title, price FROM bookinfo WHERE isbn = '" + isbn + "'";

			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//結果セットから書籍データを取り出し、Bookオブジェクトに格納
			if(rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return book;
	}

	//引数の書籍データを元にDBのbookinfoテーブルに新規登録処理を行うメソッド
	public void insert(Book book){

		Connection con = null;
		Statement smt = null;

		try{
			String sql = "INSERT INTO bookinfo VALUES('" + book.getIsbn() + "','" + book.getTitle() + "','" + book.getPrice() + "')";

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

	//引数の書籍データ（ISBN）を元にDBのbookinfoテーブルから該当書籍データの削除処理を行うメソッド
	public void delete(String isbn){

		Connection con = null;
		Statement smt = null;

		try{

			String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn + "'";

			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();
			//createStatement（）メソッドを利用してStatementオブジェクトを生成
			smt = con.createStatement();

			//executeUpdate（）メソッドを利用して、書籍データを削除
			smt.executeUpdate(sql);

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
	}

	//引数の各データを元にDBのbookinfoテーブルから該当書籍データの絞込み検索処理を行うメソッド
	public ArrayList<Book> search(String _isbn, String _title, String _price) {
		Connection con = null;
		Statement smt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM bookinfo WHERE isbn LIKE '%" + _isbn + "%' AND title LIKE '%" + _title
					+ "%' AND price LIKE '%" + _price + "%'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return bookList;
	}

	//変更機能
	public void update(Book book) {



		//変数宣言
		Connection con = null;
		Statement smt = null;



		//SQL文
		String sql = "UPDATE bookinfo SET title='"
				+ book.getTitle()
				+ "',price=" + book.getPrice()
				+ " WHERE isbn='"
				+ book.getIsbn() + "'";



		try {
			//getConnection()メソッドを利用してConnectionオブジェクトを生成
			con = getConnection();



			//ConnectionオブジェクトのcreateStatement（）メソッドを利用して
			//Statementオブジェクトを生成
			smt = con.createStatement();



			//SQLをDBへ発行
			smt.executeUpdate(sql);



		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
			if (smt != null) {
				try {
					smt.close(); //Statementオブジェクトをクローズ
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close(); //Connectionオブジェクトをクローズ
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
