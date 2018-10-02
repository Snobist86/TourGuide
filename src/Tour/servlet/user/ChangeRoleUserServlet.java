package Tour.servlet.user;

import Tour.service.RoleService;
import Tour.service.UserService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/changeRole", name = "changeRole")
public class ChangeRoleUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RoleService.getInstance().findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("change-role"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String roleId = req.getParameter("role");
        UserService.getInstance().changeRole(login, roleId);

        resp.sendRedirect("/changeRole");
    }
}
