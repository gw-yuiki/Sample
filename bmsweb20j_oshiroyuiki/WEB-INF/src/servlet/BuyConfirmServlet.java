package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import bean.Book;
import bean.Order;
import dao.OrderDAO;
import dao.BookDAO;

public class BuyConfirmServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
		throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		ArrayList<Order> order_list;

		try {
			//セッションから"user"を取得
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			//セッションが存在しているか確認処理
			if(user != null) {

				//セッションから"order_list"を取得
				order_list = (ArrayList<Order>)session.getAttribute("order_list");

				//カートの中身が存在するか確認処理
				if(order_list != null) {

					//BookDAOとOrderDAOをインスタンス化し、メソッドを呼び出す。
					ArrayList<Book> list = new ArrayList<Book>();
					BookDAO bookDao = new BookDAO();
					OrderDAO orderDao = new OrderDAO();

					for(int i = 0; i < order_list.size(); i++) {
						Order order = order_list.get(i);
						Book Book = bookDao.selectByIsbn(order.getIsbn());
						orderDao.insert(order);
						list.add(Book);
					}

					//リクエストスコープに"book_list"で格納
					request.setAttribute("book_list", list);
					//セッションの"order_list"情報をクリア
					session.setAttribute("order_list", null);

					//buyConrirm.jspにフォワード
					request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);

				}else {
					//カートの中身が無い場合の処理
					error = "カートの中に何も無かったので購入は出来ません。";
					cmd = "menu";
					request.setAttribute("error", error);
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}

			}else {
				//セッションが無い場合の処理
				error = "セッション切れの為、購入は出来ません。";
				cmd = "logout";
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}catch (IllegalStateException e) {
			//エラー処理
			error = "DB接続エラーの為、購入は出来ません";
			cmd = "logout";
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
