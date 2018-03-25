package Tour.servlet.user;

import Tour.Dto.user.UserDto;
import Tour.service.UserService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/registration", name = "registration")
public class RegistrationUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .eMail(req.getParameter("eMail"))
                .build();

        UserService.getInstance().save(userDto);

        resp.sendRedirect("registration");
    }
}
