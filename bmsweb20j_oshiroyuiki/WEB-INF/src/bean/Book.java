package bean;

/**
 * プログラム名：書籍管理システムWeb版 Ver1.0
 * プログラムの説明：書籍情報のDTOクラス
 * 作成者：大城百生
 * 作成日付：2023/05/23
 *
 * @author Globalway
 *
 */
public class Book {

	//書籍番号を格納する変数
	private String isbn;
	//タイトルを格納する変数
	private String title;
	//価格を格納する変数
	private int price;

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
