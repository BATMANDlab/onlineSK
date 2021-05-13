package com.controller;

import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/teacherquerycoursestudent")
public class TeacherQueryCourseStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String tid = req.getParameter("tid");
        if ("query1".equals(method)){
            try {
                this.queryStudent(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void queryStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tid = req.getParameter("tid");
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT snum,sname FROM student \n" +
                "WHERE sid in(\n" +
                "SELECT a.sid FROM\n" +
                "sc a LEFT JOIN course c ON c.cid = a.cid\n" +
                "WHERE c.tid = ?)";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), Integer.parseInt(tid));
        req.setAttribute("list",list);
        req.getRequestDispatcher("/view/querySelectedStudent.jsp").forward(req,resp);
    }
}
