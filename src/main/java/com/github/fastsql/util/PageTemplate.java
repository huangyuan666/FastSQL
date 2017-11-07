package com.github.fastsql.util;

import com.github.fastsql.config.DbType;
import com.github.fastsql.dto.ResultPage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

/**
 * @author 陈佳志
 * 2017-08-15
 */
public class PageTemplate {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PageTemplate(NamedParameterJdbcTemplate template) {
        this.namedParameterJdbcTemplate = template;
    }

//    public <T> ResultPage<T> queryPage(String sql, int page, int perPage, SqlParameterSource paramSource, RowMapper<T> rowMapper) {
//        String rowsSQL = PageUtils.getRowsSQL(sql, page, perPage);
//        List<T> list = namedParameterJdbcTemplate.query(
//                rowsSQL,
//                paramSource,
//                rowMapper);
//
//        //查询数量
//        String numberSQL = PageUtils.getNumberSQL(sql);
//        Integer number = namedParameterJdbcTemplate.queryForObject(
//                numberSQL,
//                paramSource,
//                Integer.class);
//        return new ResultPage<T>(list, number);
//    }
//
    public <T> ResultPage<T> queryPage(String sql, int page, int perPage, SqlParameterSource paramSource, RowMapper<T> rowMapper, DbType dbType) {
        String rowsSQL = PageUtils.getRowsSQL(sql, page, perPage, dbType);
        List<T> list = namedParameterJdbcTemplate.query(
                rowsSQL,
                paramSource,
                rowMapper);

        //查询数量
        String numberSQL = PageUtils.getNumberSQL(sql);
        Integer number = namedParameterJdbcTemplate.queryForObject(
                numberSQL,
                paramSource,
                Integer.class);
        return new ResultPage<T>(list, number);
    }

//    public <T> ResultPage<T> queryPage(String sql, int page, int perPage, Object[] objects, RowMapper<T> rowMapper) {
//        String rowsSQL = PageUtils.getRowsSQL(sql, page, perPage);
//        List<T> list = namedParameterJdbcTemplate.getJdbcOperations().query(
//                rowsSQL,
//                objects,
//                rowMapper);
//
//        //查询数量
//        String numberSQL = PageUtils.getNumberSQL(sql);
//        Integer number = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
//                numberSQL,
//                objects,
//                Integer.class);
//        return new ResultPage<T>(list, number);
//    }

    public <T> ResultPage<T> queryPage(String sql, int page, int perPage, Object[] objects, RowMapper<T> rowMapper, DbType dbType) {
        String rowsSQL = PageUtils.getRowsSQL(sql, page, perPage, dbType);
        List<T> list = namedParameterJdbcTemplate.getJdbcOperations().query(
                rowsSQL,
                objects,
                rowMapper);

        //查询数量
        String numberSQL = PageUtils.getNumberSQL(sql);
        Integer number = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                numberSQL,
                objects,
                Integer.class);
        return new ResultPage<T>(list, number);
    }
}
