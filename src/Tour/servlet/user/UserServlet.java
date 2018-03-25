package Tour.servlet.user;

import Tour.util.JspPath;
import Tour.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login", name = "login")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        if (!StringUtil.isEmpty(login) && !StringUtil.isEmpty(password)) {
//            if (USERS.containsKey(login) && USERS.get(login).equals(password)) {
//                req.getSession().setAttribute("currentUser", new User(1L, login));
//                resp.sendRedirect("/heroesList");
//            } else {
//                resp.sendRedirect("/login");
//            }
//        } else {
//            resp.sendRedirect("/login");
//        }
//    }
}
