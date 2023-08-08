/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vunt.registration.RegistrationDAO;
import vunt.registration.RegistrationDTO;
import vunt.utils.MyApplicationConstants;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {
    //private final String SEARCH_RESULT_PAGE = "searchPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");

        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.SEARCH_PAGE);
        try {
            String searchValue = request.getParameter("txtSearchValue");
            if (!searchValue.trim().isEmpty()) {
                //4. call model
                //4.1
                RegistrationDAO dao = new RegistrationDAO();
                //4.2
                dao.searchLastName(searchValue);
                //4.3 get DTO(if any)
                List<RegistrationDTO> result = dao.getAccounts();
                request.setAttribute("SEARCH_RESULT", result);
                        
            }//end search value has valid value

         } catch (NamingException ex) {
            log("NamingException_Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException_SQL" + ex.getMessage());
        } finally {
            //khi ta muon duy tri request, hoac che nhin thay duong truyen
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
