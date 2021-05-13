package com.controller;

import com.bean.Course;
import com.bean.Page;
import com.bean.Teacher;
import com.dao.CourseDao;
import com.dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("list".equals(method)){
            try {
                this.list(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("add".equals(method)){
            try {
                this.add(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if (("update".equals(method))){
            try {
                this.findById(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if (("updateSubmit".equals(method))){
            try {
                this.updateSubmit(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("delete".equals(method)){
            try {
                this.delete(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("findByName".equals(method)){
            try {
                this.findByName(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private void delete(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String cid = req.getParameter("cid");
        CourseDao.delete(Integer.parseInt(cid));
        resp.sendRedirect("/pageCourse?pageNum=1");
    }

    private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String cname = req.getParameter("cname");
        String cid = req.getParameter("cid");
        String tid = req.getParameter("tid");
        String cstock = req.getParameter("cstock");
        Course course = new Course();
        course.setTid(Integer.parseInt(tid));
        course.setCid(Integer.parseInt(cid));
        course.setCname(cname);
        course.setCstock(Integer.parseInt(cstock));
        CourseDao.update(course);
        resp.sendRedirect("/pageCourse?pageNum=1");
    }

    private void findById(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String cid = req.getParameter("cid");
        Course course = CourseDao.findById(Integer.parseInt(cid));
        req.setAttribute("course",course);
        req.getRequestDispatcher("/view/updateCourse.jsp").forward(req,resp);
    }

    private void findByName(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String cname = req.getParameter("username");
        List<Course> list = CourseDao.findByName(cname);
        Page<Course> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/courseList.jsp").forward(req,resp);
    }



    private void list(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Course> list = CourseDao.list(null);
        Page<Course> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/courseList.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String cname = req.getParameter("cname");
        String cid = req.getParameter("cid");
        String tid = req.getParameter("tid");
        String cstock = req.getParameter("cstock");
        Course course = new Course();
        course.setTid(Integer.parseInt(tid));
        course.setCid(Integer.parseInt(cid));
        course.setCname(cname);
        course.setCstock(Integer.parseInt(cstock));
        CourseDao.add(course);
        resp.sendRedirect("/pageCourse?pageNum=1");
    }
}