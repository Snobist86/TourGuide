package Tour.servlet.sight;

import Tour.Dto.sight.BasicInfoSightDto;
import Tour.service.SightService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/listUnesco", name = "listUnesco")
public class ListUnescoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BasicInfoSightDto> sights = SightService.getInstance().listUnesco();
        req.setAttribute("sights", sights);
        getServletContext()
                .getRequestDispatcher(JspPath.get("list-unesco"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BasicInfoSightDto> search = SightService.getInstance().listUnesco();
        req.setAttribute("sights", search);
        getServletContext()
                .getRequestDispatcher(JspPath.get("listUnesco"))
                .forward(req, resp);
    }
}
