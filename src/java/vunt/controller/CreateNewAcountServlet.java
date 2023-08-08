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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vunt.registration.RegistrationCreateError;
import vunt.registration.RegistrationDAO;
import vunt.utils.MyApplicationConstants;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "CreateNewAcountServlet", urlPatterns = {"/CreateNewAcountServlet"})
public class CreateNewAcountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "creatAccount.jsp";
//    private final String LOGIN_PAGE = "login.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.AccountFeature.CREATE_ERR_ACCOUNT_PAGE);
        //        String url = ERROR_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean foundError = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            //4.1 Check all user errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUserNameLengthError("Username required 6 - 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password requird 6 - 30 characters");
            } else {
                if (!confirm.trim().equals(password.trim())) {
                    foundError = true;
                    errors.setConfirmNotMatch("Confirm is not matched");
                }
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthError("Fullname requird 2 - 50 characters");
            }

            if (foundError) {
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createNewAccount(username, password, fullname, false);
                if (result) {
                    //url = LOGIN_PAGE;
                    url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
                } else {
                    errors.setUsenameIsExisted(username + " is existed");
                    request.setAttribute("CREATE_ERRORS", errors);
                }
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_ClassNotFound" + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateNewAccountServlet_SQL" + ex.getMessage());
        } finally {
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
