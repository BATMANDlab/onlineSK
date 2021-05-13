package com.dao;

import com.bean.Admin;
import com.bean.Course;
import com.bean.Page;
import com.bean.Teacher;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CourseDao {
    public static synchronized void add(Course course) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into course(cid,cname,tid,cstock) values(?,?,?,?)";
        queryRunner.update(sql, course.getCid(),course.getCname(),course.getTid(),course.getCstock());
    }

    public static synchronized void delete(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from course where cid = ?";
        queryRunner.update(sql, id);
    }
    public static synchronized void update(Course course) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update course set cname = ?, tid = ?, cstock = ? where cid = ?";
        queryRunner.update(sql, course.getCname(),course.getTid(),course.getCstock(),course.getCid());
    }
    public static synchronized List<Course> list(Course course) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from course";
        List<Course> list = queryRunner.query(sql, new BeanListHandler<>(Course.class));
        return list;
    }
    public static synchronized Course findById(int id) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from course where cid = ?";
        Course course = queryRunner.query(sql, new BeanHandler<>(Course.class), id);
        return course;
    }

    public static synchronized List<Course> findByName(String sn) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from course where cname like ?";
        List<Course> list = queryRunner.query(sql, new BeanListHandler<>(Course.class),"%"+sn+"%");
        return list;
    }

    public static synchronized Page<Course> getCourseByPage(Page<Course> page) throws SQLException {
        String sql = "select count(*) from course";
        long totalCount = PageDao.getTotalCount(sql);
        page.setTotalCount((int) totalCount);
        sql = "select * from course limit ?,?";
        List<Course> list = list(sql, page.getIndex(), page.getPageSize());
        page.setData(list);
        return page;
    }

    public static synchronized List<Course> list(String sql, int index, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        List<Course> list = queryRunner.query(sql, new BeanListHandler<>(Course.class),index,pageSize);
        return list;
    }

    public static synchronized void selectCourse(Course course) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update course set cstock = ? where cid = ?";
        queryRunner.update(sql, course.getCstock()-1,course.getCid());
    }
    public static synchronized int queryStock(int cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT COUNT(cid) FROM sc WHERE cid = ?";
        long query =(long) queryRunner.query(sql, new ScalarHandler<>(), cid);
        return (int) query;
    }

    public static synchronized int querycstock(int cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT cstock FROM course WHERE cid = ?";
        Object query = queryRunner.query(sql, new ScalarHandler<>(), cid);
        return (int) query;
    }

}
