package Tour.servlet.sight;

import Tour.Dto.sight.BasicInfoSightDto;
import Tour.Dto.sight.SearchSightDto;
import Tour.Dto.sight.SightDto;
import Tour.Dto.sight.ViewSightDto;
import Tour.service.SightService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/viewSight", name = "viewSight")
public class ViewSightServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id !=null) {
            ViewSightDto sightDto = SightService.getInstance().findById(Long.valueOf(id));
            req.setAttribute("sight", sightDto);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("view-sight"))
                    .forward(req, resp);
        }
    }
}
