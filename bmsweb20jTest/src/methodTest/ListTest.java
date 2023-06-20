package methodTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;

import dao.BookDAO;
import bean.Book;

public class ListTest {

	private WebDriver driver;

	public ListTest(WebDriver driver) {
		this.driver = driver;
	}

	//一覧機能をテストするメソッドを呼び出す
	public void List() throws InterruptedException {
		ListTest listtest = new ListTest(driver);

		// 正常動作確認
		listtest.list100();
		Thread.sleep(500);

		// 既に登録されたISBNで登録した場合エラー画面に遷移するか
		listtest.list202();
		Thread.sleep(500);
		// ISBNが未入力の場合エラー画面に遷移するか
		listtest.InsertEmptyIsbn();
		Thread.sleep(500);
		// タイトルが未入力の場合エラー画面に遷移するか
		listtest.InsertEmptyTitle();
		Thread.sleep(500);
		// 価格が未入力の場合エラー画面に遷移するか
		listtest.InsertEmptyPrice();
		Thread.sleep(500);
	}



	// 正常に一覧表示処理が行われるか
	public void list100() {

		// No.101 書籍一覧画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		// ISBNボタンをクリック
		BookDAO bookdao = new BookDAO();
		ArrayList<Book> book_list = bookdao.selectAll();

		// No.102 既に登録されているISBNを入力
		String isbn = book_list.get(1).getIsbn();
		driver.findElement(By.linkText(isbn)).click();
		driver.findElement(By.linkText("書籍一覧")).click();

		// No.103 更新ボタンをクリック
		driver.findElement(By.linkText("更新")).click();
		driver.findElement(By.linkText("書籍一覧")).click();

		// No.104 削除ボタンをクリック
		driver.findElement(By.linkText("削除")).click();

		// No.105 登録ボタンをクリック
		driver.findElement(By.linkText("書籍登録")).click();
		driver.findElement(By.linkText("書籍一覧")).click();

		// 正常処理終了
		// No.106 メニュー画面に戻る
		driver.findElement(By.linkText("メニュー")).click();
	}

	// 異常処理
	// リンク押下する予定の書籍情報をMySQL上から削除する
	public void list202() throws InterruptedException {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		BookDAO bookdao = new BookDAO();
		ArrayList<Book> book_list = bookdao.selectAll();

		// 既に登録されているISBNを入力
		//String isbn = book_list.get(1).getIsbn();
		String isbn = book_list.get(1).getIsbn();
		bookdao.delete(isbn);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.co.jp");
		driver.findElement(By.linkText(driver)).click();;
		Thread.sleep(500);

		// メニューボタンをクリック
		driver.findElement(By.linkText("[メニュー]")).click();

	}

	// ISBNを未入力で登録した場合エラーが出るか
	public void InsertEmptyIsbn() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("メニュー")).click();
		}

		// ISBNを未入力にする
		WebElement inputIsbn = driver.findElement(By.name("isbn"));
		inputIsbn.sendKeys("");

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();
	}

	// タイトルを未入力で登録した場合エラーが出るか
	public void InsertEmptyTitle() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("メニュー")).click();
		}

		// まだ登録されていないISBNを探す
		Book book;
		BookDAO bookdao = new BookDAO();
		String isbn = "";
		for (int i = 0; i < 10000; i++) {
			isbn = String.format("%5s", i).replace(" ", "0");
			book = bookdao.selectByIsbn(isbn);
			if (book.getIsbn() == null) {
				break;
			}
		}

		// ISBN入力
		WebElement InputIsbn = driver.findElement(By.name("isbn"));
		InputIsbn.sendKeys(isbn);

		// タイトルを未入力にする
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();
	}

	// 価格を未入力で登録した場合エラーが出るか
	public void InsertEmptyPrice() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			try {
				driver.findElement(By.linkText("書籍登録")).click();
			} catch (NoSuchElementException f) {
				driver.findElement(By.linkText("【一覧表示に戻る】")).click();
				driver.findElement(By.linkText("書籍登録")).click();
			}
		}

		// まだ登録されていないISBNを探す
		Book book;
		BookDAO bookdao = new BookDAO();
		String isbn = "";
		for (int i = 0; i < 10000; i++) {
			isbn = String.format("%5s", i).replace(" ", "0");
			book = bookdao.selectByIsbn(isbn);
			if (book.getIsbn() == null) {
				break;
			}
		}

		// ISBN入力
		WebElement InputIsbn = driver.findElement(By.name("isbn"));
		InputIsbn.sendKeys(isbn);

		// タイトルを未入力にする
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("メニュー")).click();
	}


}
