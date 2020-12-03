package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.dao.UserDAO;

public class cUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (request.getMethod().equals("GET")) {
			// GET request: ȸ������ ���� form ��û
			// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
			String updateId = request.getParameter("id");

			UserDAO cusdao = new UserDAO();
			User cuser = new User();

			cuser = cusdao.findcustomer(updateId);

			request.setAttribute("user", cuser);
			String birth = cuser.getBirth().split(" ")[0];
			cuser.setBirth(birth);

			return "/user/cUpdateForm.jsp"; // �˻��� ����� ������ update form���� ����

		}

		if (request.getMethod().equals("POST")) {
			// POST request (ȸ�������� parameter�� ���۵�)
			String updateId = request.getParameter("id");

			UserDAO cusdao = new UserDAO();

			User cuser = new User(updateId, request.getParameter("name"), request.getParameter("phone"),
					request.getParameter("pwd"), request.getParameter("email"), request.getParameter("birth"),
					request.getParameter("gender"));

			cusdao.cupdate(cuser);

			return "redirect:/user/customer/mypage";
		}

		return null;
	}
}