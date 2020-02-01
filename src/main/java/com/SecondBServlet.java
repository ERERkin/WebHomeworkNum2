package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class SecondBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Random rnd = new Random();
        int a = rnd.nextInt(11);
        int b = rnd.nextInt(11);
        out.print("<h1>" + a + " " + b + " " + (a + b) + "</h1>");
    }
}
