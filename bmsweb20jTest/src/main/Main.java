package main;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;

import methodTest.InsertTest;

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
		//driver.quit();
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
