package ru.clevertec.edu.ykv.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.clevertec.edu.ykv.util.Constants;

import java.io.IOException;

@WebServlet(name = "login-servlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter(Constants.PARAM_USERNAME);
        String password = req.getParameter(Constants.PARAM_PASSWORD);

        if (Constants.AUTH_USER.equals(user)
                && Constants.AUTH_PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(Constants.PARAM_USERNAME, Constants.AUTH_USER);
            //setting session to expiry in 30 minutes
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie(Constants.PARAM_USERNAME, user);
            userName.setMaxAge(30 * 60);
            resp.addCookie(userName);
            resp.getWriter().write("Login Successful");
        } else {
            resp.getWriter().write("Login was unsuccessful");
        }
    }
}
