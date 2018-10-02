package Tour.servlet.category;

import Tour.Dto.category.CategoryDto;
import Tour.service.CategoryService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createCategory", name = "createCategory")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-category"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryDto categoryDto = CategoryDto.builder()
                .name(req.getParameter("name"))
                .build();

        CategoryService.getInstance().save(categoryDto);

        resp.sendRedirect("/createCategory");
    }
}