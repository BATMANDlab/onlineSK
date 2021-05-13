package com.dao;

import com.bean.*;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {

    public static synchronized void add(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into student(sname,snum,spwd) values(?,?,?)";
        queryRunner.update(sql,student.getSname(),student.getSnum(),student.getSpwd());
    }

    public static synchronized void delete(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from student where sid = ?";
        queryRunner.update(sql, id);
    }
    public static synchronized void update(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update student set sname = ?, snum = ?,spwd = ? where sid = ?";
        queryRunner.update(sql,student.getSname(),student.getSnum(),student.getSpwd(),student.getSid());
    }
    public static synchronized List<Student> list(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from student";
        List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        return list;
    }
    public static synchronized Student findById(int id) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from student where sid = ?";
        Student student = queryRunner.query(sql, new BeanHandler<>(Student.class), id);
        return student;
    }


    public static synchronized List<Student> findByName(String sn) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from student where sname like ?";
        List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),"%"+sn+"%");
        return list;
    }


    public static synchronized Student login(String account, String pwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from student where snum = ? and spwd = ?";
        Student entity = queryRunner.query(sql, new BeanHandler<>(Student.class), account, pwd);
        return entity;
    }

    public static synchronized Page<Student> getStudentByPage(Page<Student> page) throws SQLException {
        String sql = "select count(*) from student";
        long totalCount = PageDao.getTotalCount(sql);
        page.setTotalCount((int) totalCount);
        sql = "select * from student limit ?,?";
        List<Student> list = list(sql, page.getIndex(), page.getPageSize());
        page.setData(list);
        return page;
    }

    public static synchronized List<Student> list(String sql, int index, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),index,pageSize);
        return list;
    }
}
