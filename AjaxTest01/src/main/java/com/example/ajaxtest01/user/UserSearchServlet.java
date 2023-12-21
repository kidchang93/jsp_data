package com.example.ajaxtest01.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html;charset=UTF-8");
        String userName = req.getParameter("userName");
        resp.getWriter().write(getJSON(userName));
    }
    public String getJSON(String userName){
        if (userName == null) userName = "";
        StringBuffer result = new StringBuffer("");
        result.append("{\"result\":[");
        UserDAO userDAO = new UserDAO();
        ArrayList<UserDTO> userList = userDAO.search(userName);

        for (int i = 0; i < userList.size(); i++){
            result.append("[{\"value\": \"" + userList.get(i).getUserName() + "\"},");
            result.append("{\"value\": \"" + userList.get(i).getUserAge() + "\"},");
            result.append("{\"value\": \"" + userList.get(i).getUserGender() + "\"},");
            result.append("{\"value\": \"" + userList.get(i).getUserEmail() + "\"}],");
        }
        result.append("]}");
        return result.toString();
    }
}
