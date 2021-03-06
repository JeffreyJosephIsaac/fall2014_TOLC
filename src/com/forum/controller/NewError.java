package com.forum.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.olc.MongoUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class NewError
 */
@WebServlet("/NewError")
public class NewError extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewError() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer data = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    	data.append(line);
		  } catch (Exception e){
			  
		  }
		  JsonParser parser = new JsonParser();
		  JsonObject jsonObj = parser.parse(data.toString()).getAsJsonObject();
		  String moderatorId = jsonObj.get("user_id").getAsString();
		  String commentId = jsonObj.get("commentId").getAsString();
		  String errorType = jsonObj.get("errorType").getAsString();
		  String errorUrl = jsonObj.get("errorUrl").getAsString();
		  
		  MongoUtils.addError(moderatorId, commentId, errorType, errorUrl);
	}

}
