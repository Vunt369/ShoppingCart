///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package vunt.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import vunt.utils.MyApplicationConstants;
//
///**
// *
// * @author NguyenTuanVu
// */
//@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
//public class DispatchController extends HttpServlet {
//
//    //private final String LOGIN_PAGE = "login.html";
//    //private final String LOGIN_CONTROLLER = "LoginSevlet";
//    //private final String SEARCH_LAST_CONTROLLER = "SearchLastNameServlet";
////    private final String DELETE_CONTROLLER = "DeleteRecordServlet";
////    private final String UPDATE_CONTROLLER = "UpdateRecordServlet";
////    private final String START_CONTROLLER = "StartTimeServlet";
////    private final String ADD_TO_CART = "AddToCartServlet";
////    private final String VIEW_CART_PAGE = "viewCart.jsp";
////    private final String REMOVE_ITEM_CONTROLLER = "RemoveItemServlet";
////    private final String BUY_BOOK = "BookStoreServlet";
////    private final String CREATE_NEW_ACCOUNT_SERVLET = "CreateNewAcountServlet";
////    private final String LOGOUT_SERVLET = "LogoutServlet";
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        //which button did user cliked?
//        String button = request.getParameter("btAction");
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
//        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
//        try {
//            if (button == null) {
//                //
//                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.START_CONTROLLER);
//            } else if (button.equals("Login")) {
//                //url = LOGIN_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
//            } else if (button.equals("Search")) {
//                url = siteMaps.getProperty(MyApplicationConstants.SearchFeature.SEARCH_CONTROLLER);
//            } else if (button.equals("Delete")) {
//                url = siteMaps.getProperty(MyApplicationConstants.DeleteFeature.DELETE_CONTROLLER);
//            } else if (button.equals("Update")) {
//                url = siteMaps.getProperty(MyApplicationConstants.UpdateFeature.UPDATE_CONTROLLER);
//            } else if (button.equals("Add Book To Your Cart")) {
//                url = siteMaps.getProperty(MyApplicationConstants.ShoppingFeature.ADD_TO_CART_CONTROLLER);
//            } else if (button.equals("View Your Cart")) {
//                url = siteMaps.getProperty(MyApplicationConstants.ShoppingFeature.VIEW_CART_PAGE);
//            } else if (button.equals("Remove Items")) {
//                url = siteMaps.getProperty(MyApplicationConstants.ShoppingFeature.REMOVE_CART_CONTROLLER);
//            } else if (button.equals("Create New Account")) {
//                url = siteMaps.getProperty(MyApplicationConstants.AccountFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Logout")) {
//                url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
//            }
//
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
