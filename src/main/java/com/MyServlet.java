package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<table style=\"width:10%\">");
        int k = 0;
        for(int i = 0; i < 10; i++){
            out.print("<tr>");
            for(int j = 0; j < 10; j++){
                k++;
                if((i + j) % 2 == 0) out.print("<th style=\"background-color: gray\">" + k + "</th>");
                else out.print("<th>" + k + "</th>");
            }
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
