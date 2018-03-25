package Tour.servlet.country;

import Tour.Dto.country.CountryDto;
import Tour.entity.Country;
import Tour.service.CountryService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/createCountry", name = "createCountry")
public class CountryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-country"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CountryDto countryDto = CountryDto.builder()
                .name(req.getParameter("name"))
                .build();

        CountryService.getInstance().save(countryDto);

        resp.sendRedirect("/createCountry");
    }
}
