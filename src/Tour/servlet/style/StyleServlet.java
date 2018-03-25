package Tour.servlet.style;

import Tour.Dto.style.StyleDto;
import Tour.entity.Style;
import Tour.service.StyleService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/createStyle", name = "createStyle")
public class StyleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-style"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StyleDto styleDto = StyleDto.builder()
                .name(req.getParameter("name"))
                .build();

        StyleService.getInstance().save(styleDto);

        resp.sendRedirect("/createStyle");
    }
}
