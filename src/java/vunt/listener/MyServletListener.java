/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author NguyenTuanVu
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteMapPath = context.getInitParameter("SITE_MAP_FILE_PATH");

        //Read siteMapPath
        Properties properties = new Properties();
        InputStream is = null;

        try {
            is = context.getResourceAsStream(siteMapPath);
            properties.load(is);
            context.setAttribute("SITEMAPS", properties);
        } catch (IOException ex) {
            context.log("MyServletListener_I0" + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    context.log("MyServletListener_I0" + e.getMessage());
                }
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is deptroying......");
    }
}
