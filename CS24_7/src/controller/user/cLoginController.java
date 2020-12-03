package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dao.UserDAO;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class cLoginController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("id");
		String password = request.getParameter("pwd");

		try {
			UserDAO cusdao = new UserDAO();
			User cuser = new User();
			cuser = cusdao.findcustomer(userId);

			if (cuser == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}

			if (!cuser.matchPassword(password)) {
				throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}

			if (cusdao.existingCuser(userId)) {
				// ���ǿ� ����� ���̵� ����
				HttpSession session = request.getSession();
				session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
			}

			return "redirect:/customer/main";
		} catch (Exception e) {
			/*
			 * UserNotFoundException�̳� PasswordMismatchException �߻� �� �ٽ� login form�� ����ڿ���
			 * �����ϰ� ���� �޼����� ���
			 */
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
			return "/user/chooseRegisterUser.jsp";
		}
	}
}
