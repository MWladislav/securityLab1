package filter;


import model.Role;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/admin" }, servletNames = { "adminServlet" })
public class AdminPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginedUser");
        if (user == null) {
            RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/login");
            dispatcher.forward(req, resp);
            return;
        }
        else if (user.getRole()!=Role.ADMIN){
            RequestDispatcher dispatcher=servletRequest.getServletContext().getRequestDispatcher("/userInfo");
            dispatcher.forward(req,resp);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
