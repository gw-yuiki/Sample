package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request ,HttpServletResponse response)
			throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定する
			request.setCharacterEncoding("UTF-8");

			//入力パラメータを取得
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");

			//UserDAOをインスタンス化し、関連メソッドを呼び出す
			UserDAO userDao = new UserDAO();
			User user = userDao.selectByUser(userid, password);

			//User情報取得の有無でフォワード先変更
			if(null != user.getUserid() || null != user.getPassword()) {
				//ある場合、セッションスコープに"user"という名前で登録
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}else {
				//リクエストにエラーメッセージを設定
				error = "入力データが間違っています。";
				request.setAttribute("error", error);
				request.setAttribute("userid", userid);
				request.setAttribute("password", password);
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}

		}catch (IllegalStateException e) {
				error = "DB接続エラーの為、ログインは出来ません。";
				cmd = "logout";
		}finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はmenu.jspにフォワード
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
