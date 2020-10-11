/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyLoginServlets;


/**
 *
 * @author Cashton
 */
public class AccountServices {
    
    public User login(String username, String password){
         
        if((username.equalsIgnoreCase("adam") || username.equalsIgnoreCase("betty")) && password.equals("password")){
               
                
                return new User(username, null);
        }
            
        return null;
    }
}
