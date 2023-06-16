package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import bean.Book;
import bean.Order;
import dao.UserDAO;
import dao.BookDAO;

public class ShowCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
		throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		ArrayList<Order> order_list;

		try {
			//セッションから"user"を取得
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if(null != user) {

				// 文字コードを設定する
				request.setCharacterEncoding("UTF-8");

				//削除対処の配列要素番号パラメータを取得
				String delno = request.getParameter("delno");

				//セッションから"order_list"を取得
				order_list = (ArrayList<Order>)session.getAttribute("order_list");

				//delnoが「null」で無い場合order_listから該当の書籍情報を削除
				if(delno != null) {
					order_list.remove(Integer.parseInt(delno));
				}

				//BookDAOをインスタンス化し、引数のISBNデータを検索するメソッドをorder_list分だけ呼び出す
				ArrayList<Book> list = new ArrayList<Book>();
				BookDAO bookDao = new BookDAO();
				if(order_list != null) {
					for(int i = 0; i < order_list.size(); i++) {
						Order order = order_list.get(i);
						Book Book = bookDao.selectByIsbn(order.getIsbn());
						list.add(Book);
					}
				}

				//リクエストスコープに"book_list"で格納し、showCart.jspにフォワード
				request.setAttribute("book_list", list);
				request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
			}else {
				//セッションから"user"を取得できなかった場合のエラー処理
				error = "セッション切れの為、カート状況は確認出来ません。";
				cmd = "logout";
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}catch (IllegalStateException e) {
			//エラー処理
			error = "DB接続エラーの為、カート状況は確認出来ません。";
			cmd = "logout";
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
