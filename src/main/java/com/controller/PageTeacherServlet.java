package com.controller;

import com.bean.Page;
import com.bean.Teacher;
import com.dao.TeacherDao;
import com.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pageTeacher")
public class PageTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5;
        String pageNum = null;
        if (req.getParameter("pageNum") != null){
            pageNum = req.getParameter("pageNum");
        }
        Page page1 = null;
        try {
            Page<Teacher> page = new Page<>();
            page.setPageNum(Integer.parseInt(pageNum));
            page.setPageSize(pageSize);
            page1 = TeacherDao.getTeacherByPage(page);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("page",page1);
        req.getRequestDispatcher("/view/teacherList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
