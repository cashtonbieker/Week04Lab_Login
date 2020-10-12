/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLoginServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        //If a user does not exist, send them to the login page
        if (user == null){
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        //If a user exists, display the home page
        else{
            request.setAttribute("hello", user.getUsername());
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       
       //Display the user
       User user = (User) session.getAttribute("user"); 
       request.setAttribute("hello", user.getUsername());
       getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
    }

}
