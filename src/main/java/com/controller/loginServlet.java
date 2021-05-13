package com.controller;
import com.bean.Admin;
import com.bean.Student;
import com.bean.Teacher;
import com.dao.AdminDao;
import com.dao.StudentDao;
import com.dao.TeacherDao;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        HttpSession session = req.getSession();

        if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(type)){
            req.setAttribute("error","输入信息不能为空");
            req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
        }

        if (StringUtils.isNotBlank(type)){
            if ("0".equals(type)){
                //学生登录验证
                try {
                    Student student = StudentDao.login(username, password);
                    if (student != null){
                        session.setAttribute("user", student);
                        session.setAttribute("type", type);
                        req.setAttribute("student",student);
                        req.getRequestDispatcher("/view/student.jsp").forward(req,resp);
                    }else {
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
                    }
                } catch (SQLException | ServletException throwables) {
                    throwables.printStackTrace();
                }
            }else if("1".equals(type)){
                //老师登录验证
                try {
                    Teacher teacher = TeacherDao.login(username, password);
                    if (teacher != null){
                        session.setAttribute("user", teacher);
                        session.setAttribute("type", type);
                        req.setAttribute("teacher",teacher);
                        req.getSession().setAttribute("teacher",teacher);
                        req.getRequestDispatcher("/view/teacher.jsp").forward(req,resp);
                    }else {
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
                    }
                } catch (SQLException | ServletException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                Admin admin = new Admin();
                admin.setAaccount(username);
                admin.setApwd(password);
                try {
                    Admin admin1 = AdminDao.login(admin);
                    if (admin1 != null){
                        session.setAttribute("user", admin1);
                        session.setAttribute("type", type);
                        resp.sendRedirect("/view/admin.jsp");
                    }else{
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
                    }
                } catch (SQLException | ServletException throwables) {
                    throwables.printStackTrace();
                }
            }
        }else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
