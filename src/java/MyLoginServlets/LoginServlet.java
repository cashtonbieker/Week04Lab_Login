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



public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        //Invalidate old session if user has logged out
        String logout = request.getParameter("logout");
        if(logout != null){
            session.invalidate();
            session = request.getSession();
            request.setAttribute("error", "You have successfully logged out");
        }
        
        //If there is no current user, require them to login
        User user = (User) session.getAttribute("user");
        if (user == null){
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        //If a user exists, send them to the home page
        session.setAttribute("user", user);
        response.sendRedirect(getServletContext().getContextPath().concat("/home"));
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        AccountServices validate = new AccountServices();
        
        //Pass input parameters into variables
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        //Check if parameters passed in have a value
        if (username == null || password == null){
            request.setAttribute("error", "Invalid entry. Try again");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        //Push parameters through validation method
        if(validate.login(username, password) == null){
            request.setAttribute("error", "Invalid entry. Try again");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        //Set a session attribute with the varified parameters
        if(validate.login(username, password) != null){
            User user = new User();
            user.setUsername(username);
            
            session.setAttribute("user", user);
            response.sendRedirect(getServletContext().getContextPath().concat("/home"));
            
        }
        
    }


}
