package Tour.servlet.review;

import Tour.Dto.review.ViewReviewDto;
import Tour.service.ReviewService;
import Tour.util.JspPath;
import Tour.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/viewReview", name = "viewReview")
public class ReviewBySightServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("view-review"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bySight = req.getParameter("id");

        List<ViewReviewDto> search = ReviewService.getInstance().search(bySight);
        req.setAttribute("reviews", search);
        doGet(req, resp);

    }
}
