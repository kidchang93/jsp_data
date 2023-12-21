package com.example.ajaxtest01.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDAO(){
        try{
            String dbURL = "jdbc:mariadb://localhost:3306/ajax";
            String dbID = "root";
            String dbPassword = "12345";
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<UserDTO> search(String userName){
        String SQL = "select * from user where user_name like ?";
        ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,"%" + userName + "%");
            rs = pstmt.executeQuery();
            while (rs.next()){
                UserDTO dto = new UserDTO();
                dto.setUserName(rs.getString(1));
                dto.setUserAge(rs.getInt(2));
                dto.setUserGender(rs.getString(3));
                dto.setUserEmail(rs.getString(4));
                userList.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        } return userList;
    }
}
