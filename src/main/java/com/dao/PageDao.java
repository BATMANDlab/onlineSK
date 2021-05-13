package com.dao;

import com.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class PageDao {
    /**查询总数*/
    public static synchronized long getTotalCount(String sql) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        long query = (long) queryRunner.query(sql,new ScalarHandler<>());
        return query;
    }
}
