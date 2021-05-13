package com.dao;

import com.bean.SC;
import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SCDao {
    public static synchronized void add(SC sc) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into sc(sid,cid,score) values(?,?,?)";
        queryRunner.update(sql, sc.getSid(),sc.getCid(),null);
    }

    public static synchronized List<SC> queryAllBySId(int sid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from sc where sid = ?";
        List<SC> list = queryRunner.query(sql, new BeanListHandler<>(SC.class), sid);
        return list;
    }

    public static synchronized SC queryAllBySCId(int scid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from sc where scid = ?";
        SC sc = queryRunner.query(sql, new BeanHandler<>(SC.class), scid);
        return sc;
    }

    public static synchronized void update(SC sc) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update sc set sid = ?, cid = ?, score = ? where scid = ?";
        queryRunner.update(sql,sc.getSid(),sc.getCid(),sc.getScore(),sc.getScid());
    }
    public static synchronized void updateScore(SC sc) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update sc set score = ? where scid = ?";
        queryRunner.update(sql,sc.getScore(),sc.getScid());
    }
    public static synchronized SC findBySidCid(int sid,int cid) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from sc where sid = ? and cid = ?";
        SC sc = queryRunner.query(sql, new BeanHandler<>(SC.class), sid,cid);
        return sc;
    }

    public static synchronized List querySelected(int sid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT c.*,t.tname FROM course c\n" +
                "\tLEFT JOIN teacher t on c.tid = t.tid\n" +
                "WHERE c.cid in(SELECT cid FROM sc WHERE sid = ?)\n" +
                ";";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), sid);
        return list;
    }

    public static synchronized List updateScoreList(int tid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT a.scid,a.cid,c.cname,s.snum,s.sname,a.score FROM \n" +
                "sc a \n" +
                "LEFT JOIN student s on a.sid = s.sid\n" +
                "LEFT JOIN course c on a.cid = c.cid\n" +
                "WHERE a.cid in\n" +
                "(SELECT course.cid from course where tid = ?);\n";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), tid);
        return list;
    }

    public static synchronized List queryCount(int tid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT a.* FROM\n" +
                "sc a\n" +
                "LEFT JOIN course c on a.cid = c.cid\n" +
                "WHERE a.cid in \n" +
                "(SELECT course.cid from course where tid = ?)";
        List<SC> list = queryRunner.query(sql, new BeanListHandler<>(SC.class), tid);
        return list;
    }

    public static synchronized List queryScoreBySId(int sid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT c.cname,a.score FROM sc a LEFT JOIN course c on a.cid = c.cid\n" +
                "WHERE a.sid = ?";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), sid);
        return list;
    }

}
