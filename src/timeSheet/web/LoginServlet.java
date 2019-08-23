package timeSheet.web;

import timeSheet.dao.LoginDao;
import timeSheet.dao.LoginDaoImpl;
import timeSheet.model.Login;
import timeSheet.service.LoginService;
import timeSheet.service.LoginServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(name = "LoginServlet", urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);
        String address = "";

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("LoginServet - username="+username + " password=" + password);
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                Login login = loginService.login(username, password);
                if(login != null){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login", login);
                    address = "index.jsp";
                } else {
                    request.setAttribute("invalid", "Username or Password is invalid! ");
                    address = "login.jsp";
                }
            } else {
                request.setAttribute("invalid", "Username or Password is empty!");
                address = "login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}