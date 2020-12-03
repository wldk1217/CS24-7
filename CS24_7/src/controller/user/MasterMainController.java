package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class MasterMainController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ��
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/master/form"; // login form ��û���� redirect
		}

		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/master/main_m.jsp";

	}

}
