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
        
        User user = (User) session.getAttribute("user");
        
        if(user.getUsername() == null){
            user = new User();
            session.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountServices validate = new AccountServices();
        User user;
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username != null && password != null){
        
            if(validate.login(username, password) == null){
                request.setAttribute("error", "Invalid entry. Try again");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            
            user = validate.login(username, password);
            session.setAttribute("user", user.getUsername());
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
        }
        
        request.setAttribute("error", "Invalid entry. Try again");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }


}
