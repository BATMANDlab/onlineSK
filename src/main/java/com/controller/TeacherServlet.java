package com.controller;

import com.bean.Page;
import com.bean.Teacher;
import com.dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
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
        String tid = req.getParameter("tid");
        TeacherDao.delete(Integer.parseInt(tid));
        resp.sendRedirect("/pageTeacher?pageNum=1");
    }

    private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String tname = req.getParameter("tname");
        String taccount = req.getParameter("taccount");
        String tpwd = req.getParameter("tpwd");
        String tid = req.getParameter("tid");
        Teacher teacher = new Teacher();
        teacher.setTid(Integer.parseInt(tid));
        teacher.setTaccount(taccount);
        teacher.setTname(tname);
        teacher.setTpwd(tpwd);
        TeacherDao.update(teacher);
        resp.sendRedirect("/pageTeacher?pageNum=1");
    }

    private void findById(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tid = req.getParameter("tid");
        Teacher teacher = TeacherDao.findById(Integer.parseInt(tid));
        req.setAttribute("teacher",teacher);
        req.getRequestDispatcher("/view/updateTeacher.jsp").forward(req,resp);
    }

    private void findByName(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String tname = req.getParameter("username");
        List<Teacher> list = TeacherDao.findByName(tname);
        Page<Teacher> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/view/teacherList.jsp").forward(req,resp);
    }



    private void list(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<Teacher> list = TeacherDao.list(null);
        Page<Teacher> page = new Page<>();
        page.setData(list);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pageTeacher?pageNum=1").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String tname = req.getParameter("tname");
        String tid = req.getParameter("tid");
        String taccount = req.getParameter("taccount");
        String tpwd = req.getParameter("tpwd");
        Teacher teacher = new Teacher();
        teacher.setTpwd(tpwd);
        teacher.setTaccount(taccount);
        teacher.setTname(tname);
        teacher.setTid(Integer.parseInt(tid));
        TeacherDao.add(teacher);
        resp.sendRedirect("/pageTeacher?pageNum=1");
    }
}