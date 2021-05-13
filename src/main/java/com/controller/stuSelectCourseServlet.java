package com.controller;

import com.bean.Course;
import com.bean.Page;
import com.bean.SC;
import com.dao.CourseDao;
import com.dao.SCDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/selectCourse")
public class stuSelectCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("list".equals(method)){
            try {
                this.list(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if ("findByName".equals(method)){
            try {
                this.findByName(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("select".equals(method)){
            try {
                this.selectCourse(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    private void findByName(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String cname = req.getParameter("username");
        List<Course> list = CourseDao.findByName(cname);
        Page<Course> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/stuSelectCourseList.jsp").forward(req,resp);
    }



    private void list(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Course> list = CourseDao.list(null);
        Page<Course> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/courseList.jsp").forward(req,resp);
    }
    public void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        String sid = req.getParameter("sid");
        String cid = req.getParameter("cid");
        SC sc = new SC();
        sc.setCid(Integer.parseInt(cid));
        sc.setSid(Integer.parseInt(sid));
        Course course = new Course();
        course.setCid(Integer.parseInt(cid));
        course.setCstock(CourseDao.querycstock(Integer.parseInt(cid)));
        if (CourseDao.querycstock(Integer.parseInt(cid)) > 0){
            if (SCDao.findBySidCid(Integer.parseInt(sid),Integer.parseInt(cid)) != null) {
                req.getSession().setAttribute("msg","不能选重复的课程");
                req.getRequestDispatcher("/pageSelectCourse?pageNum=1&sid="+sid).forward(req,resp);
                return;
            }else {
                SCDao.add(sc);
                CourseDao.selectCourse(course);
                req.getSession().setAttribute("msg","选课成功");
                req.getRequestDispatcher("/pageSelectCourse?pageNum=1&sid="+sid).forward(req,resp);
                return;
            }
        }else {
            req.getSession().setAttribute("msg","余量不足,选课失败!");
            req.getRequestDispatcher("/pageSelectCourse?pageNum=1&sid="+sid).forward(req,resp);
        }
    }
}
