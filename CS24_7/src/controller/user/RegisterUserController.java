package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.dao.UserDAO;

public class RegisterUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User(request.getParameter("id"), request.getParameter("name"), request.getParameter("phone"),
				request.getParameter("pwd"), request.getParameter("email"), request.getParameter("birth"),
				request.getParameter("gender"));

		try {
			UserDAO userDAO = new UserDAO();
			if (userDAO.existingCuser(user.getId()) == true) {
				throw new ExistingUserException(user.getId() + "�� �����ϴ� ���̵��Դϴ�.");
			}
			userDAO.createCustomer(user);
			return "/user/cLoginForm.jsp";

		} catch (ExistingUserException e) { // ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/chooseRegisterUser.jsp";
		}
	}
}