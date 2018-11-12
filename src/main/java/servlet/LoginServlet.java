package servlet;

import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import util.VerifyUtils;
import validator.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static Logger LOG = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        LOG.info("go to login page");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //validation
        UserService service = new UserService();
        boolean valid = false;
        String errorString = null;

        if (UserValidator.validate(username, password)) {
            User user = service.findUserSecurely(username, password);
            if (user != null) {
                String gRecaptchaResponse = req.getParameter("g-recaptcha-response");

                System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);

                // Verify CAPTCHA.
                valid = VerifyUtils.verify(gRecaptchaResponse);
                if (!valid) {
                    errorString = "Invalid captcha!";
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("loginedUser", user);
                    session.setMaxInactiveInterval(60000);
                    LOG.info("for user " + username + " created session");
                    resp.sendRedirect("/userInfo");
                }
            } else {
                errorString = "Invalid username or password";
            }

        }
        if (!valid) {
            req.setAttribute("error", errorString);
            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
