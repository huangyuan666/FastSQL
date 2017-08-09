package com.github.fastsql.util;

/**
 * @author 陈佳志
 */
public class PageSqlUtils {

    public static String DB_TYPE = "mysql";//mysql,postgrsql,oracle


    public static String getRowsSQL(String sql, int pageNumber, int perPageSize) {
        if (DB_TYPE.equals("mysql")) {
            return mysql(sql, pageNumber, perPageSize);
        } else if (DB_TYPE.equals("postgresql")) {
            return postgrsql(sql, pageNumber, perPageSize);
        } else if (DB_TYPE.equals("oracle")) {
            return oracle(sql, pageNumber, perPageSize);
        } else {
            throw new RuntimeException("PageSqlUtils.DB_TYPE 错误");
        }
    }

    public static String getNumberSQL(String sql) {
        return "SELECT COUNT(*) FROM ( " + sql + " )";
    }


    /**
     * @param pageNumber  页数，从第一页开始
     * @param perPageSize 每页条数，大于1
     */
    public static String mysql(String sql, int pageNumber, int perPageSize) {
        //偏移量，即是忽略offset行
        int offset = (pageNumber - 1) * perPageSize;

        return sql + " LIMIT " + offset + "," + perPageSize;
        //return sql + " LIMIT " + perPageSize + " OFFSET " + offset;
    }

    /**
     * @param pageNumber  页数，从第一页开始
     * @param perPageSize 每页条数，大于1
     */
    public static String postgrsql(String sql, int pageNumber, int perPageSize) {
        //偏移量，即是忽略offset行
        int offset = (pageNumber - 1) * perPageSize;

        return sql + " LIMIT " + perPageSize + " OFFSET " + offset;
    }

    /**
     * @param pageNumber  页数，从第一页开始
     * @param perPageSize 每页条数，大于1
     */
    public static String oracle(String sql, int pageNumber, int perPageSize) {
        int limit = (pageNumber - 1) * perPageSize;
        int endRowNum = limit + perPageSize;

        return "SELECT * FROM " +
                " ( SELECT A.*, ROWNUM RN   FROM  " +
                " ( " + sql + " ) A   WHERE ROWNUM " +
                " <= " + endRowNum + ") WHERE RN >=  " + limit;
    }
}
