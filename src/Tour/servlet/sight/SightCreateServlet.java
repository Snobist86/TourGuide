package Tour.servlet.sight;

import Tour.Dto.sight.SightDto;
import Tour.service.CategoryService;
import Tour.service.CityService;
import Tour.service.CountryService;
import Tour.service.SightService;
import Tour.service.StyleService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createSight", name = "createSight")
public class SightCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cities", CityService.getInstance().findAll());
        req.setAttribute("categories", CategoryService.getInstance().findAll());
        req.setAttribute("styles", StyleService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-sight"))
                .forward(req, resp);
            }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SightDto sightDto = SightDto.builder()
                .name(req.getParameter("name"))
                .cityId(Long.valueOf(req.getParameter("city")))
                .yearOfBuilding(Integer.valueOf(req.getParameter("yearOfBuilding")))
                .categoryId(Integer.valueOf(req.getParameter("category")))
                .styleId(Integer.valueOf(req.getParameter("style")))
                .isListUNESCO(Boolean.valueOf(req.getParameter("isListUNESCO")))
                .ratingOfTripAdvisor(Double.valueOf(req.getParameter("ratingOfTripAdvisor")))
                .build();

        SightService.getInstance().save(sightDto);
        resp.sendRedirect("/createSight");
    }
}
