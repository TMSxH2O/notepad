package com.xhu.tms.mybatis.cfg;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/19
 * Description: 用于封装执行的SQL语句和结果类型的全限定类名
 * Version: V1.0
 */
public class Mapper {
    private String queryString; //SQL语句
    private String resultType; //实体类的全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
