/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.cs313.discussionforum;

import java.io.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author JL5372
 */
@WebServlet(name = "CreatePost", urlPatterns = {"/CreatePost"})
public class CreatePost extends HttpServlet {

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
        Date date = new Date();
        PrintWriter out = response.getWriter();
        String discussfile = "discussfile.json";
        JSONArray masterjarray = new JSONArray();
        try {
            JSONObject post = new JSONObject();
            post.put("datetime", date.toString());
            post.put("username", request.getSession().getAttribute("username").toString());
            post.put("post", newpost);

            masterjarray.add(post);
            
            JSONParser parser = new JSONParser();
            JSONArray jarray = (JSONArray) parser.parse(new FileReader(getServletContext().getRealPath(discussfile)));
            for (Object oarray : jarray ) {
                //JSONObject discussthread = (JSONObject) oarray;
                masterjarray.add(oarray);
            }
        } catch(FileNotFoundException ex) {
            out.println("Unable to open file '" + discussfile + "'");
        } catch(ParseException ex){
            out.println(ex.getMessage());
        }
    
        out.println(masterjarray.toJSONString());
        
        try (FileWriter file = new FileWriter(getServletContext().getRealPath("/") + discussfile)){
            file.write(masterjarray.toJSONString());

        }
        
        request.getRequestDispatcher("/LoadPosts").forward(request, response);
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
