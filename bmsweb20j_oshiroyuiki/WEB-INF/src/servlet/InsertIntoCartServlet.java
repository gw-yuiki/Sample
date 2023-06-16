package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import bean.Book;
import bean.Order;
import dao.BookDAO;

public class InsertIntoCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
		throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		ArrayList<Order> list;

		try {
			//セッションから"user"のUserオブジェクトを取得
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if(user != null) {

				// 文字コードを設定する
				request.setCharacterEncoding("UTF-8");

				//入力パラメータを取得
				String isbn = request.getParameter("isbn");

				//③BookDAOをインスタンス化し、引数のISBNデータを検索するメソッドを呼び出し
				BookDAO bookDao = new BookDAO();
				Book Book = bookDao.selectByIsbn(isbn);

				//リクエストスコープに"Book"という名前で格納
				request.setAttribute("Book", Book);

				//Orderのインスタンスを生成し、各setterメソッドを利用し、isbn、userid(ログイン者)、数量(1固定)を設定
				Order order = new Order();
				order.setIsbn(isbn);
				order.setUserid(user.getUserid());
				order.setQuantity(1);

				//セッションから"order_list"のList配列を取得
				list = (ArrayList<Order>)session.getAttribute("order_list");
				//無い場合は新規作成
				if(list == null) {
					list = new ArrayList<Order>();
				}

				//セッションスコープに"order_list"という名前で登録
				list.add(order);
				session.setAttribute("order_list", list);

				}else {
					error = "セッション切れの為、カートに追加出来ません。";
					cmd = "logout";
					request.setAttribute("error", error);
				}

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加は出来ません。";
			cmd = "logout";
		}finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				//エラーが無い場合はinsertIntoCart.jspにフォワード
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			} else {
				//エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
