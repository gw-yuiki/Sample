package servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import bean.Book;
import bean.Order;
import dao.OrderDAO;
import dao.BookDAO;

public class InsertIniDataServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
		throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		try {
			//BookDAOを新スタンス化し、全件取得メソッドを呼び出す
			BookDAO bookDao = new BookDAO();
			ArrayList<Book> list = bookDao.selectAll();

			//listに1件でも書籍データが存在するか確認する処理
			if(list.size() < 1) {
				//初期データ用CSVファイルよりデータを取得
				String path = getServletContext().getRealPath("file\\initial_data.csv");

				//Bookオブジェクトを生成し、setterを使用して初期データ数分設定
				if(path != null) {
					Scanner sin = new Scanner(new File(path));
					try {
						while (sin.hasNextLine()) {
				            Book book = new Book();
				            String[] csv_data = sin.nextLine().split(",");
				            int i = 0;
				            book.setIsbn(csv_data[i++]);
				            book.setTitle(csv_data[i++]);
				            book.setPrice(Integer.parseInt(csv_data[i]));
				            list.add(book);
				        }

						// 設定した各Bookのオブジェクトを引数として、BookDAOオブジェクトを生成し、関連メソッドを呼び出す
				        for (int i = 0; i < list.size(); i++) {
				            bookDao.insert(list.get(i));
				        }

				        //リクエストスコープに"list"で格納し、insertIniData.jspにフォワード
				        request.setAttribute("book_list", list);
						request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);

					}catch(NumberFormatException e) {
						error = "初期データファイルが不備がある為、登録は行いません。";
						cmd = "menu";
						request.setAttribute("error", error);
						request.setAttribute("cmd", cmd);
						request.getRequestDispatcher("/view/error.jsp").forward(request, response);
					}
				}else {
					error = "初期データファイルが無い為、登録は行いません。";
					cmd = "menu";
					request.setAttribute("error", error);
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}
			}else {
				error = "DBにはデータが存在するので、初期データは登録できません。";
				cmd = "menu";
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、初期データ登録は行えません。";
			cmd = "logout";
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
