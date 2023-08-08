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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vunt.registration.RegistrationDAO;
import vunt.utils.MyApplicationConstants;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "UpdateRecordServlet", urlPatterns = {"/UpdateRecordServlet"})
public class UpdateRecordServlet extends HttpServlet {

    //private final String UPDATE_ERROR_PAGE = "updateErr.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateFeature.UPDATE_ERROR_PAGE);
        //String url = UPDATE_ERROR_PAGE;

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String isAdmin = request.getParameter("checkRole");

        try {
            boolean role = false;
            if (isAdmin != null) {
                role = true;
            }

            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateRecord(username, password, role);
            String searchValue = request.getParameter("lastSearchValue");
            if (result) {
                url = "searchController"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch (SQLException ex) {
            log("SQLException_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_Naming" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
