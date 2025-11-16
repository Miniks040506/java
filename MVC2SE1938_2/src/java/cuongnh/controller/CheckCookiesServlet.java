/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cuongnh.controller;

import cuongnh.registration.RegistrationDAO;
import cuongnh.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckCookiesServlet", urlPatterns = {"/CheckCookiesServlet"})
public class CheckCookiesServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        //1.read all cookies
        Cookie[] cookies = request.getCookies();

        try {
            //2. check existed cookies
            if (cookies != null) {
                //3. Get newest cookie, convert cookie to username(key) and password(value)
                Cookie newest = cookies[cookies.length - 1];
                String username = newest.getName();
                String password = newest.getValue();
                //4. Controller calls methods of model
                //4.1. Controller create new DAzaO object
                RegistrationDAO dao = new RegistrationDAO();
                //4.2. Controller calls methods of DAO object
                RegistrationDTO result = dao.checkLogin(username, password);
                //5. Controller Processes result
                if (result != null) {
                    url = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", result);
                } //end username has existed            
            } //end 
        } catch (SQLException ex) {
            log("CheckCookiesServlet_SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("CheckCookiesServlet_ClassNotFound: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
