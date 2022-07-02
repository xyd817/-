package com.xyd.team.view;

import com.xyd.team.domain.Employee;
import com.xyd.team.domain.Programmer;
import com.xyd.team.service.NameListService;
import com.xyd.team.service.TeamException;
import com.xyd.team.service.TeamService;

/**
 * @author xyd
 * @create 2022-06-26 23:16
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public TeamView() {
    }

    //进入菜单
    public void enterMainMenu(){
        boolean loopFlag = true;
        char key = '0';
        do{
            if(key != '1'){
                this.listAllEmployees();
            }
            System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)： ");
            key = TSUtility.readMenuSelection();
            System.out.println();
            switch (key){
                case '1':
                    //列出所有团队成员
                    getTeam();
                    break;
                case '2':
                    //添加团队成员
                    addMember();
                    break;
                case '3':
                    //删除团队成员
                    deleteMember();
                    break;
                case '4':
                    //退出
                    System.out.println("确认是否退出？");
                    char yn = TSUtility.readConfirmSelection();
                    if(yn == 'Y'){
                        loopFlag = false;
                    }
            }
        }while (loopFlag);
    }
    //以表格形式列出公司所有成员
    private void listAllEmployees(){
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------\n");
        Employee[] employees = listSvc.getAllEmployees();
        if(employees.length == 0){
            System.out.println("没有客户记录");
        }else{
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        }
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }
    //显示团队成员列表操作
    private void getTeam(){
        Programmer[] team = teamSvc.getTeam();
        System.out.println("--------------------团队成员列表---------------------\n");
        System.out.println("TDI/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        int tLen = teamSvc.getTotal();
        if(tLen == 0){
            System.out.println("开发团队目前没有成员");
        }else{
            for (int i = 0; i < tLen; i++) {
                team[i].getTeamInfo();
            }
        }
        System.out.println("-----------------------------------------------------\n");
    }
    //实现添加成员操作
    private void addMember(){
        System.out.println("---------------------添加成员---------------------\n");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();
        try {
            Employee employee = listSvc.getEmployee(id);
            teamSvc.addMember(employee);
            System.out.println("添加成功");
        } catch (Exception e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        TSUtility.readReturn();

    }

    //实现删除成员操作
    private void deleteMember(){
        System.out.println("---------------------删除成员---------------------\n");
        System.out.print("请输入要删除员工的TID：");
        int tId = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char isDelete = TSUtility.readConfirmSelection();
        if(isDelete == 'Y'){
            try {
                teamSvc.removeMember(tId);
                System.out.println("删除成功");
                TSUtility.readReturn();
            } catch (TeamException e) {
                System.out.println("删除失败，原因" + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new TeamView().enterMainMenu();
    }


}
