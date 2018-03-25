package Tour.servlet.city;

import Tour.Dto.city.CityDto;
import Tour.service.CityService;
import Tour.service.CountryService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createCity", name = "createCity")
public class CityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countries", CountryService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-city"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CityDto cityDto = CityDto.builder()
                .name(req.getParameter("name"))
                .countryId(Long.valueOf(req.getParameter("country")))
                .build();

        CityService.getInstance().save(cityDto);

        resp.sendRedirect("/createCity");
    }
}
