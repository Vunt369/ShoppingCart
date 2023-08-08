/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vunt.registration.RegistrationDAO;
import vunt.registration.RegistrationDTO;
import vunt.utils.MyApplicationConstants;

/**
 *
 * @author TuanVu113
 */
public class LoginSevlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalidPage";
//    private final String SEARCH_PAGE = "searchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //prinwiter = setvalue
        PrintWriter out = response.getWriter();

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.INVALID_PAGE);

        //mac dinh luon la trang loi
//        String url = INVALID_PAGE;

        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");

        try {
            //1.call check login
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);

            if (result != null) {
//                url = SEARCH_PAGE;//khi result dung, chuyen den searchPage
                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_PAGE);
                //Ghi cookies sau khi login thanh cong
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 3);
//                tra cookies ve client, nen dung response
//                response.addCookie(cookie);
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", result);
            }//end if user click LOGIN BUTTON    

        } catch (SQLException ex) {
            log("SQLException_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_Naming" + ex.getMessage());
        } finally {
//            response.sendRedirect(url); Mong muon giau ten page tinh~
            RequestDispatcher rq = request.getRequestDispatcher(url);
            rq.forward(request, response);
            out.close();
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
