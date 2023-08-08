/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vunt.registration.RegistrationDAO;
import vunt.registration.RegistrationDTO;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "StartTimeServlet", urlPatterns = {"/StartTimeServlet"})
public class StartTimeServlet extends HttpServlet {

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
        Cookie[] cookies = null;
        try {
            //1. get all Cookies
            cookies = request.getCookies();
            if (cookies != null) {
                //2.get last cookies
                Cookie lastCookie = cookies[cookies.length - 1];
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                //3. call model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO result = dao.checkLogin(username, password);
                
                if (result != null) {
                    url = SEARCH_PAGE;
                }
                // call Login feature by using url Rewriting
//             url = DispatchController?btAction=Login&txtUsername= +username &txtPassword + password: cach nay se sai vi tra ve errPage neu dang nhap sai
            }//set cookies had existed!!
        } catch (SQLException ex) {
            log("Errors occur in processing", ex.getCause());
        } catch (NamingException ex) {
            log("Errors occur in processing", ex.getCause());
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
