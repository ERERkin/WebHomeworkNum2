package com;

import com.DB.DB;
import com.DB.User;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import static com.DB.DB.connect;

public class MyServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/tt.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req,resp);
        DB DB = new DB();
        Connection connection = connect();
        User user = new User("Aza","Aza@za","aza123");
        User user1 = new User("EA","EA@za","EA123");
        //DB.register(user1);
        DB.authorize("Aza","aza12");
        //DB.deleteBlockedUser("Aza");
        if (connection != null) connection.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String[] login = req.getParameterValues("login");
        String[] email = req.getParameterValues("email");
        String[] password = req.getParameterValues("password");
        User user = new User(login[0],email[0],password[0]);
        DB.register(user);
        writer.print(login[0] + " ");
        writer.print(email[0] + " ");
        writer.print(password[0]);
    }
}
