package com.dao;

import com.bean.Admin;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AdminDao {
    /**添加管理员*/
    public static void add(Admin admin) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into admin(aid,aaccount,aname,apwd) values(?,?,?)";
        queryRunner.update(sql, admin.getAaccount(), admin.getAname(), admin.getApwd());
    }

    public static void delete(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "delete from admin where aid = ?";
        queryRunner.update(sql, id);
    }
    public static void update(Admin admin) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update admin set aaccount = ?, aname = ?, apwd = ? where id = ?";
        queryRunner.update(sql, admin.getAaccount(), admin.getAname(), admin.getApwd(),admin.getAid());
    }
    public static List<Admin> list(Admin admin) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from admin";
        List<Admin> list = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
        return list;
    }
    public static Admin findById(int id) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from admin where aid = ?";
        Admin admin = queryRunner.query(sql, new BeanHandler<>(Admin.class), id);
        return admin;
    }

    public static Admin login(Admin admin) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from admin where aaccount = ? and apwd = ?";
        Admin entity = queryRunner.query(sql, new BeanHandler<>(Admin.class), admin.getAaccount(), admin.getApwd());
        return entity;
    }

}
