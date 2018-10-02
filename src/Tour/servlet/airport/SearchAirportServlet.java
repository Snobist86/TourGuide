package Tour.servlet.airport;

import Tour.Dto.airport.FullInfoAirportDto;
import Tour.Dto.airport.SearchAirportDto;
import Tour.service.AirportService;
import Tour.service.CityService;
import Tour.service.CountryService;
import Tour.util.JspPath;
import Tour.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/searchAirport", name = "searchAirport")
public class SearchAirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("countries", CountryService.getInstance().findAll());
        req.setAttribute("cities", CityService.getInstance().findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("search-airport"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchAirportDto searchAirportDto = SearchAirportDto.builder()
                .countryId(StringUtil.defaultParameter(req.getParameter("country")))
                .cityId(StringUtil.defaultParameter(req.getParameter("city")))
                .build();

        List<FullInfoAirportDto> search = AirportService.getInstance().search(searchAirportDto);
        req.setAttribute("airports", search);
        doGet(req, resp);
    }
}
