/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.cs313.discussionforum;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;


/**
 *
 * @author JL5372
 */
@WebServlet(name = "LoadPosts", urlPatterns = {"/LoadPosts"})
public class LoadPosts extends HttpServlet {

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
                String newpost = request.getParameter("newpost");
        PrintWriter out = response.getWriter();
        String discussfile = "discussfile.json";
        JSONArray masterjarray = new JSONArray();
        ArrayList<String[]> allposts = new ArrayList();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jarray = (JSONArray) parser.parse(new FileReader(getServletContext().getRealPath(discussfile)));
            Iterator<JSONObject> iterator = jarray.iterator();
            while (iterator.hasNext()) {
                JSONObject opost = (JSONObject) iterator.next();
                String[] post = {opost.get("datetime").toString(),opost.get("post").toString(),opost.get("username").toString()};
                allposts.add(post);
            }
            request.getSession().setAttribute("allposts", allposts);
        } catch(FileNotFoundException ex) {
            out.println("Unable to open file '" + discussfile + "'");
        } catch(ParseException ex){
            out.println(ex.getMessage());
        }
        request.getRequestDispatcher("/discussion.jsp").forward(request, response);
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
