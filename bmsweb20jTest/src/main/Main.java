package main;

import org.openqa.selenium.*;


import org.openqa.selenium.chrome.ChromeDriver;

import methodTest.InsertTest;
import methodTest.UpdateTest;
import methodTest.ListTest;
import methodTest.DetailTest;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// ドライバ指定
		System.setProperty("webdriver.chrome.driver", "./exe/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// 開きたいサイトのURLを取得
		driver.get("http://localhost:8080/bmsweb20j_oshiroyuiki/view/login.jsp");

		// ログイン
		Thread.sleep(500);
		Login(driver);


		// 登録機能をテストするクラスをインスタンス化
		Thread.sleep(500);
		InsertTest inserttest = new InsertTest(driver);
		inserttest.Insert();

		// 一覧機能をテストするクラスをインスタンス化
		Thread.sleep(500);
		ListTest listtest = new ListTest(driver);
		listtest.List();

		// 詳細機能をテストするクラスをインスタンス化
		Thread.sleep(500);
		DetailTest detailtest = new DetailTest(driver);
		detailtest.Detail();

		// 変更機能をテストするクラスをインスタンス化
		Thread.sleep(500);
		UpdateTest updatetest = new UpdateTest(driver);
		updatetest.Update();

		driver.quit();
	}

	public static void Login(WebDriver driver) {

		// ユーザー欄とパスワード欄にkandaitと入力
		WebElement user = driver.findElement(By.name("userid"));
		//user.sendKeys("kandait");

		WebElement password = driver.findElement(By.name("password"));
		//password.sendKeys("kandait");

		// ログインボタンを押す
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();
	}

}
