package com.sjl.dbtest;

import java.util.Date;

/**
 * 测试表(test_table)，类型映射
 *
 * @author song
 */
public class TestTable {
    private long id;//主键
    //驼峰字段测试
    //基本类型
    private int testId1;
    private int testId2;//对应数据库short类型，不能用short接收，否则出现cannot convert java.lang.Integer to short Query
    private long testId3;
    private float testMoney1;
    private double testMoney2;
    //对象类型
    private String testName;
    private String headBase64;
    private Integer testAge1;
    private Integer testAge2;//对应数据库short类型，不能用short接收，否则出现cannot convert java.lang.Integer to short Query
    private Long testAge3;
    private Float price1;
    private Double price2;

    //映射blob
    private byte[] testImg;//头像数据

    //映射日期
    private java.util.Date birthDate;
    //映射时间戳
    private java.util.Date createTime;

    //下划线字段测试
    private String test_my_remark;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTestId1() {
        return testId1;
    }

    public void setTestId1(int testId1) {
        this.testId1 = testId1;
    }

    public int getTestId2() {
        return testId2;
    }

    public void setTestId2(int testId2) {
        this.testId2 = testId2;
    }

    public long getTestId3() {
        return testId3;
    }

    public void setTestId3(long testId3) {
        this.testId3 = testId3;
    }

    public float getTestMoney1() {
        return testMoney1;
    }

    public void setTestMoney1(float testMoney1) {
        this.testMoney1 = testMoney1;
    }

    public double getTestMoney2() {
        return testMoney2;
    }

    public void setTestMoney2(double testMoney2) {
        this.testMoney2 = testMoney2;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getHeadBase64() {
        return headBase64;
    }

    public void setHeadBase64(String headBase64) {
        this.headBase64 = headBase64;
    }

    public Integer getTestAge1() {
        return testAge1;
    }

    public void setTestAge1(Integer testAge1) {
        this.testAge1 = testAge1;
    }

    public Integer getTestAge2() {
        return testAge2;
    }

    public void setTestAge2(Integer testAge2) {
        this.testAge2 = testAge2;
    }

    public Long getTestAge3() {
        return testAge3;
    }

    public void setTestAge3(Long testAge3) {
        this.testAge3 = testAge3;
    }

    public Float getPrice1() {
        return price1;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public byte[] getTestImg() {
        return testImg;
    }

    public void setTestImg(byte[] testImg) {
        this.testImg = testImg;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTest_my_remark() {
        return test_my_remark;
    }

    public void setTest_my_remark(String test_my_remark) {
        this.test_my_remark = test_my_remark;
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "testId1=" + testId1 +
                ", testId2=" + testId2 +
                ", testId3=" + testId3 +
                ", testMoney1=" + testMoney1 +
                ", testMoney2=" + testMoney2 +
                ", testName='" + testName + '\'' +
                ", headBase64='" + headBase64 + '\'' +
                ", testAge1=" + testAge1 +
                ", testAge2=" + testAge2 +
                ", testAge3=" + testAge3 +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", testImg=" + testImg +
                ", birthDate=" + birthDate +
                ", createTime=" + createTime +
                ", test_my_remark='" + test_my_remark + '\'' +
                '}';
    }
}
