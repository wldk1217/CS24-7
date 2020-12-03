package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dao.UserDAO;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("id");
		String password = request.getParameter("pwd");

		try {
			UserDAO musdao = new UserDAO();
			User muser = new User();
			muser = musdao.findmaster(userId);

			if (muser == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}

			if (!muser.matchPassword(password)) {
				throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}

			if (musdao.existingMuser(userId)) {
				// ���ǿ� ����� ���̵� ����
				HttpSession session = request.getSession();
				session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
			}

			return "redirect:/master/main";
		} catch (Exception e) {
			/*
			 * UserNotFoundException�̳� PasswordMismatchException �߻� �� �ٽ� login form�� ����ڿ���
			 * �����ϰ� ���� �޼����� ���
			 */
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			return "/user/chooseLoginUser.jsp";
		}
	}
}