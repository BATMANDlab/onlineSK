package com.controller;

import com.bean.SC;
import com.dao.SCDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/teacherupdatescore")
public class TeacherUpdateScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("updatescore".equals(method)){
            try {
                this.updateScore(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("update".equals(method)){
            try {
                this.update(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("updateSubmit".equals(method)){
            try {
                this.updateSubmit(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("querycount".equals(method)){
            try {
                this.queryCount(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void queryCount(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tid = req.getParameter("tid");
        List list = SCDao.queryCount(Integer.parseInt(tid));
        double jige = 0,youxiu = 0,count = 0;
        for (Object o : list) {
            count++;
            if (((SC)o).getScore() >=60 && ((SC)o).getScore() <90){jige++;}
            else if (((SC)o).getScore() >= 80){
                jige++;
                youxiu++;
            }
        }
        req.setAttribute("jigelv",String.format("%.2f", jige/count*100)+"%");
        req.setAttribute("youxiulv",String.format("%.2f", youxiu/count*100)+"%");
        req.setAttribute("jige",(int)jige);
        req.setAttribute("youxiu",(int)youxiu);
        req.getRequestDispatcher("/view/queryCount.jsp").forward(req,resp);

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String scid = req.getParameter("scid");
        SC sc = SCDao.queryAllBySCId(Integer.parseInt(scid));
        req.setAttribute("sc", sc);
        req.getRequestDispatcher("/view/TeacherUpdateScore.jsp").forward(req,resp);
    }
    public void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tid = req.getParameter("tid");
        String scid = req.getParameter("scid");
        String score = req.getParameter("score");
        SC sc = new SC();
        sc.setScore(Double.parseDouble(score));
        sc.setScid(Integer.parseInt(scid));
        SCDao.updateScore(sc);
        req.getRequestDispatcher("/teacherupdatescore?method=updatescore&tid="+tid).forward(req,resp);
    }
    public void updateScore(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tid = req.getParameter("tid");
        List list = SCDao.updateScoreList(Integer.parseInt(tid));
        req.setAttribute("list", list);
        req.getRequestDispatcher("/view/updateScore.jsp").forward(req,resp);
    }
}
