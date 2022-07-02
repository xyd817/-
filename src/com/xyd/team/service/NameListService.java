package com.xyd.team.service;

import com.xyd.team.domain.*;
import com.xyd.team.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xyd
 * @create 2022-06-26 20:36
 */
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        this.employees = new Employee[Data.EMPLOYEES.length];
        //获取数据库连接
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "SELECT type,id,name,age,salary,bonus,stock FROM employee WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        for (int i = 0; i < employees.length; i++) {
            //  创建对应的对象
            int label = 0 , id = 0, age = 0, bonus = 0, stock = 0;
            String name = "";
            Double salary = 0.0;
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, i + 1);
                rs = ps.executeQuery();
                if(rs.next()){
                    label = rs.getInt("type");
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    age = rs.getInt("age");
                    salary = rs.getDouble("salary");
                    bonus = rs.getInt("bonus");
                    stock = rs.getInt("stock");
//                    System.out.println("type = " + label + "\tid = " + id + "\tname = " + name
//                            + "\tage = " + age + "\t salary = " + salary + "\tbonus = " + bonus + "\t stock = " + stock );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //设备配置
            Equipment eq;
            switch (label) {
                case Data.EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case Data.PROGRAMMER:
                    eq = createEquipment(i + 1);
                    employees[i] = new Programmer(id, name, age, salary, eq);
                    break;
                case Data.DESIGNER:
                    eq = createEquipment(i + 1);
                    employees[i] = new Designer(id, name, age, salary, eq, bonus);
                    break;
                case Data.ARCHITECT:
                    eq = createEquipment(i + 1);
                    System.out.println("eq = " + eq);
                    employees[i] = new Architect(id, name, age, salary, eq, bonus, stock);
            }

        }
    }

    private Equipment createEquipment(int id) {
        String sql  = "SELECT type, brand, remark FROM equipment WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                int type = rs.getInt("type");
                switch (type) {
                    case Data.PC:
                        String model = rs.getString("brand");
                        String display = rs.getString("remark");
                        return new PC(model, display);
                    case Data.NOTEBOOK:
                        model = rs.getString("brand");
                        String price = rs.getString("remark");
                        return new NoteBook(model, price);
                    case Data.PRINTER:
                        String name = rs.getString("brand");
                        String ty = rs.getString("remark");
                        return new Printer(name, ty);
                    default:
                        return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws Exception {
        for (Employee employee : employees) {
            if (id == employee.getId()) {
                return employee;
            }
        }
        throw new TeamException("找不到指定的员工");
    }


    public static void main(String[] args) {
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
