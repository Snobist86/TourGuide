package Tour.servlet.review;

import Tour.Dto.review.ReviewDto;
import Tour.entity.User;
import Tour.service.ReviewService;
import Tour.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createReview", name = "createReview")
public class ReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("view-sight"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        ReviewDto reviewDto = ReviewDto.builder()
                .value(req.getParameter("value"))
                .userId(user.getId())
                .sightId(Long.valueOf(req.getParameter("id")))
                .build();

        ReviewService.getInstance().save(reviewDto);

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
