package com.controller;

import com.bean.Course;
import com.bean.Page;
import com.dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pageSelectCourse")
public class PageSelectCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5;
        String pageNum = null;
        String sid = req.getParameter("sid");
        if (req.getParameter("pageNum") != null){
            pageNum = req.getParameter("pageNum");
        }
        Page page1 = null;
        try {
            Page<Course> page = new Page<>();
            page.setPageNum(Integer.parseInt(pageNum));
            page.setPageSize(pageSize);
            page1 = CourseDao.getCourseByPage(page);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("page",page1);
        req.setAttribute("sid",sid);
        req.getRequestDispatcher("/view/stuSelectCourseList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
