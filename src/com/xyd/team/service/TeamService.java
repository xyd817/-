package com.xyd.team.service;

import com.xyd.team.domain.*;

/**
 * @author xyd
 * @create 2022-06-26 21:39
 */
public class TeamService {
    private static int counter = 1; //记录团队id
    private static final int MAX_MEMBER = 5; //最大团队成员数
    private Programmer[] team = new Programmer[MAX_MEMBER];//记录团队人数
    private int total;//记录团队成员实际人数

    public TeamService() {
    }

    public Programmer[] getTeam(){
        return team;
    }
    public void addMember(Employee e) throws TeamException{
        if(total == MAX_MEMBER){
            throw new TeamException("成员已满，无法添加！");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //判断是否在开发团队中
        for (int i = 0; i < total; i++) {
            if(e.getId() == team[i].getId()){
                throw new TeamException("该成员已在本开发团队中");
            }
        }
        //判断是否为其他团队成员
        if(((Programmer) e).getMemberId() != 0){
            throw new TeamException("该成员已是某团队成员");
        }
        if(((Programmer) e).getStatus() == Status.VOCATION){
            throw new TeamException("该成员正在休假，无法添加");
        }
        //计算团队中架构师、设计师、程序员的数量
        int architectNum = 0, designerNum = 0, programmerNum = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                architectNum++;
            }else if(team[i] instanceof Designer){
                designerNum++;
            }else if(team[i] instanceof Programmer){
                programmerNum++;
            }
        }
        if(e instanceof Architect && architectNum >= 1){
            throw  new TeamException("团队中至多只能有一名架构师");
        }
        if(e instanceof Designer && designerNum >= 2){
            throw new TeamException("团队中至多只能有两名设计师");
        }

        if(e instanceof Programmer && programmerNum >= 3){
            throw new TeamException("团队中至多只能有三名程序员");
        }
        Programmer p = (Programmer) e;
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;

    }
    public void removeMember(int memberId) throws TeamException{
        for (int i = 0; i < total; i++) {
            if(team[i].getMemberId() == memberId){
                team[i] = null;
                //删除成员
                for (int j = i + 1; j < total; j++) {
                    team[j - 1] = team[j];
                }
                total--;
                return;
            }
        }
        throw new TeamException("找不到指定memberId的员工，删除失败");
    }

    public int getTotal() {
        return total;
    }

    public static void main(String[] args) throws Exception {
        NameListService service = new NameListService();
        Employee employee1 = service.getEmployee(2);
        TeamService teamService = new TeamService();
        teamService.addMember(employee1);
        Employee employee2 = service.getEmployee(3);
        teamService.addMember(employee2);

        Programmer[] team = teamService.getTeam();
        for (int i = 0; i < teamService.getTotal(); i++) {
            System.out.println(team[i]);
        }
        teamService.removeMember(1);
        System.out.println();
        for (int i = 0; i < teamService.getTotal(); i++) {
            System.out.println(team[i]);
        }
    }


}
