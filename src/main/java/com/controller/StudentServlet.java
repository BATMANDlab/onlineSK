package com.controller;

import com.bean.Page;
import com.bean.Student;
import com.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
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
        String sid = req.getParameter("sid");
        StudentDao.delete(Integer.parseInt(sid));
        resp.sendRedirect("/page?pageNum=1");
    }

    private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String sname = req.getParameter("sname");
        String snum = req.getParameter("snum");
        String spwd = req.getParameter("spwd");
        String sid = req.getParameter("sid");
        Student student = new Student();
        student.setSname(sname);
        student.setSnum(snum);
        student.setSpwd(spwd);
        student.setSid(Integer.parseInt(sid));
        StudentDao.update(student);
        resp.sendRedirect("/page?pageNum=1");
    }

    private void findById(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String sid = req.getParameter("sid");
        Student student = StudentDao.findById(Integer.parseInt(sid));
        req.setAttribute("student",student);
        req.getRequestDispatcher("/view/updateStudent.jsp").forward(req,resp);
    }

    private void findByName(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String sname = req.getParameter("username");
        List<Student> list = StudentDao.findByName(sname);
        Page<Student> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/studentList.jsp").forward(req,resp);
    }



    private void list(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Student> list = StudentDao.list(null);
        Page<Student> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/page?pageNum=1").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String sname = req.getParameter("sname");
        String snum = req.getParameter("snum");
        String spwd = req.getParameter("spwd");
        Student student = new Student();
        student.setSname(sname);
        student.setSnum(snum);
        student.setSpwd(spwd);
        StudentDao.add(student);
        resp.sendRedirect("/page?pageNum=1");
    }
}
