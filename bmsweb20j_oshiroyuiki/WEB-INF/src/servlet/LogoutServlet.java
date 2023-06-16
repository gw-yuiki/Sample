package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request ,HttpServletResponse response)
			throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定する
			request.setCharacterEncoding("UTF-8");

			//セッション情報をクリア
			HttpSession session = request.getSession();
			session.invalidate();

			//login.jspにフォワード
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		}catch (IllegalStateException e) {
			// エラーが有る場合はerror.jspにフォワードする
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "menu";
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}
}
