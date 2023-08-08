/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vunt.cart.CartObj;
import vunt.utils.MyApplicationConstants;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    //private final String SHOPPING_PAGE = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
//        String url = (String)siteMaps.getProperty(MyApplicationConstants.ShoppingFeature.SHOPPING_PAGE);
        String url = MyApplicationConstants.ShoppingFeature.SHOPPING_PAGE;
//        String url = "shopping.html";
        try {
            //1. Cust goes to cart place(Session Scope)
            //Co place thi` co cart(place bat buoc phai ton tai  => create = true)
            HttpSession session = request.getSession();
            //2. Cust takes cart;
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }//end cart has NOT existed       
            //3. Cust drops item to cart
            String item = request.getParameter("cboBook");
            cart.addItemToCart(item, 1);
            session.setAttribute("CART", cart);
            //4.
        } finally {
            //4. Cust keep shopping
            response.sendRedirect(url); // why
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
