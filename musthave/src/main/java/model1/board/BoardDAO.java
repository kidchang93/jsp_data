package model1.board;

import common.JDBConnect;

import javax.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
public BoardDAO(ServletContext application){
  super(application);
}
}
