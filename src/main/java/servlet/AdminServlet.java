package servlet;

import model.Message;
import org.apache.log4j.Logger;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "adminServlet",urlPatterns = {"/admin"})

public class AdminServlet extends HttpServlet {

    private static Logger LOG = Logger.getLogger(AdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageService messageService = new MessageService();
        ArrayList<Message> messages= messageService.findAll();
        req.setAttribute("messages",messages);

        req.getRequestDispatcher("/WEB-INF/views/adminPage.jsp").forward(req,resp);
        LOG.info("get to admin page");
    }

}

