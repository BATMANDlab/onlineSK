package com.service;

import com.bean.Page;
import com.bean.Student;
import com.dao.StudentDao;

import java.sql.SQLException;

public class PageService {
    public static Page<Student> getStudentByPage(String pageNum,int pageSize) throws SQLException {
        int pn = 1;
        try {
            pn = Integer.parseInt(pageNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        Page<Student> page = new Page<>();
        page.setPageNum(pn);
        page.setPageSize(pageSize);
        return StudentDao.getStudentByPage(page);
    }
}
