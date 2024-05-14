package com.ohgiraffers.employee.dao;

import java.util.Properties;
import com.mysql.cj.jdbc.JdbcConnection;
import com.ohgiraffers.common.JDBCtemplate;
import com.ohgiraffers.employee.dto.EmpInsertDTO;
import com.ohgiraffers.employee.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import static com.ohgiraffers.common.JDBCtemplate.*;

public class EmployeeRepository {

    /*
     * repository란?
     * 데이터를 등록하고 수정하고 삭제하고 조회하는 등의
     * 데이터베이스와 연결되어 동작하는 로직을 수행하는 계층이다.
     * */


        private Properties pros =new Properties();
        private Connection con = null;
        private PreparedStatement pstmt = null;
        private ResultSet rset = null;

        public EmployeeRepository() {
            try {
                this.pros.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/employee/mapper/employee-query.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public ArrayList employeeViewAll(){
            ArrayList arrayList = new ArrayList();
            String query = pros.getProperty("employeeAll");
            con = getConnection();
            try {
                pstmt = con.prepareStatement(query);
                rset = pstmt.executeQuery();
                while (rset.next()){
                    EmployeeDTO emp = new EmployeeDTO();
                    emp.setEmpId(rset.getString("EMP_ID"));
                    emp.setEmpName(rset.getString("EMP_NAME"));
                    emp.setPhone(rset.getString("PHONE"));
                    emp.setDeptCode(rset.getString("DEPT_CODE"));
                    emp.setJobCode(rset.getString("JOB_CODE"));
                    emp.setSalary(rset.getInt("SALARY"));
                    emp.setEntYn(rset.getString("ENT_YN"));
                    arrayList.add(emp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                close(rset);
                close(pstmt);
                close(con);
            }

            return arrayList;
        }

        public EmployeeDTO employeeFindByName(String name) {
            String query = pros.getProperty("employeeFindByName");
            con = getConnection();
            EmployeeDTO emp = new EmployeeDTO();

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                rset = pstmt.executeQuery();
                if(rset.next()){
                    emp.setEmpId(rset.getString("EMP_ID"));
                    emp.setEmpName(rset.getString("EMP_NAME"));
                    emp.setPhone(rset.getString("PHONE"));
                    emp.setDeptCode(rset.getString("DEPT_CODE"));
                    emp.setJobCode(rset.getString("JOB_CODE"));
                    emp.setSalary(rset.getInt("SALARY"));
                    emp.setEntYn(rset.getString("ENT_YN"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                close(rset);
                close(pstmt);
                close(con);
            }

            return emp;
        }

        public EmployeeDTO empFindById(String index){
            String query = pros.getProperty("employeeFindById");
            con = getConnection();
            EmployeeDTO emp = null;

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, index);
                rset = pstmt.executeQuery();
                if(rset.next()){
                    emp = new EmployeeDTO();
                    emp.setEmpId(rset.getString("EMP_ID"));
                    emp.setEmpName(rset.getString("EMP_NAME"));
                    emp.setPhone(rset.getString("PHONE"));
                    emp.setDeptCode(rset.getString("DEPT_CODE"));
                    emp.setJobCode(rset.getString("JOB_CODE"));
                    emp.setSalary(rset.getInt("SALARY"));
                    emp.setEntYn(rset.getString("ENT_YN"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                close(con);
                close(pstmt);
                close(rset);
            }

            return emp;
        }

        public int empInsert(EmpInsertDTO emp) {
            // 값을 추가
            // 쿼리 가져옴
            String query = pros.getProperty("empInsert");
            // connection
            con = getConnection();
            int result = 0;
            // 쿼리를 사용하기 위함
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,emp.getEmpId());
                pstmt.setString(2,emp.getEmpName());
                pstmt.setString(3,emp.getEmpNo());
                pstmt.setString(4,emp.getJobCode());
                pstmt.setString(5,emp.getSalLevel());

                result = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                close(con);
                close(pstmt);
            }

            return result;
        }
    }

