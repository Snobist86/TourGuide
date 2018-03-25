package Tour.servlet.airport;

import Tour.Dto.airport.AirportDto;
import Tour.service.AirportService;
import Tour.service.CityService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createAirport", name = "createAirport")
public class AirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cities", CityService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-airport"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AirportDto airportDto = AirportDto.builder()
                .name(req.getParameter("name"))
                .cityId(Long.valueOf(req.getParameter("city")))
                .build();

        AirportService.getInstance().save(airportDto);

        resp.sendRedirect("/createAirport");
    }
}
