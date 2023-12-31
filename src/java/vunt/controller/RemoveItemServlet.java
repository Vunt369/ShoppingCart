/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vunt.cart.CartObj;

/**
 *
 * @author NguyenTuanVu
 */
@WebServlet(name = "RemoveItemServlet", urlPatterns = {"/RemoveItemServlet"})
public class RemoveItemServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String urlRewriting = "cartController?btAction=View Your Cart";
        try {
            //1. Cust goes to his her cart place 
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2 cust takes his/cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. cust gets all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust remove items from cart
                        String[] selectedItems = request.getParameterValues("checkItem");
                        if (selectedItems != null) {
                            for (String item : selectedItems) {
                                cart.removeItemFromCart(item, 1);
                            }//end traverrse cart
                            session.setAttribute("CART", cart);
                        }//Items had selected
                    }//end items have existed
                }//end cart has existed                
            }//end session has existed

        } finally {
            //5. refresh --> call ther View Cart feature again using urlRewriting
            response.sendRedirect(urlRewriting);
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
