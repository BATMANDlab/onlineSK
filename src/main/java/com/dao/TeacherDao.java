package com.dao;

import com.bean.Page;
import com.bean.Teacher;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TeacherDao {
    public static synchronized void add(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into teacher(tid,tname,taccount,tpwd) values(?,?,?,?)";
        queryRunner.update(sql,teacher.getTid(),teacher.getTname(),teacher.getTaccount(),teacher.getTpwd());
    }

    public static synchronized void delete(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from teacher where tid = ?";
        queryRunner.update(sql, id);
    }
    public static synchronized void update(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update teacher set tname = ?, taccount = ?,tpwd = ? where tid = ?";
        queryRunner.update(sql,teacher.getTname(),teacher.getTaccount(),teacher.getTpwd(),teacher.getTid());
    }
    public static synchronized List<Teacher> list(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from teacher";
        List<Teacher> list = queryRunner.query(sql, new BeanListHandler<>(Teacher.class));
        return list;
    }
    public static synchronized Teacher findById(int id) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from teacher where tid = ?";
        Teacher teacher = queryRunner.query(sql, new BeanHandler<>(Teacher.class), id);
        return teacher;
    }


    public static synchronized Teacher login(String account,String pwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from teacher where taccount = ? and tpwd = ?";
        Teacher entity = queryRunner.query(sql, new BeanHandler<>(Teacher.class), account, pwd);
        return entity;
    }

    public static synchronized List<Teacher> findByName(String sn) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from teacher where tname like ?";
        List<Teacher> list = queryRunner.query(sql, new BeanListHandler<>(Teacher.class),"%"+sn+"%");
        return list;
    }

    public static synchronized Page<Teacher> getTeacherByPage(Page<Teacher> page) throws SQLException {
        String sql = "select count(*) from teacher";
        long totalCount = PageDao.getTotalCount(sql);
        page.setTotalCount((int) totalCount);
        sql = "select * from teacher limit ?,?";
        List<Teacher> list = list(sql, page.getIndex(), page.getPageSize());
        page.setData(list);
        return page;
    }

    public static synchronized List<Teacher> list(String sql, int index, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        List<Teacher> list = queryRunner.query(sql, new BeanListHandler<>(Teacher.class),index,pageSize);
        return list;
    }
}
