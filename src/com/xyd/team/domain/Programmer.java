package com.xyd.team.domain;

/**
 * @author xyd
 * @create 2022-06-26 19:52
 */
public class Programmer extends Employee{
    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment ;
    @Override
    public String toString() {
        return this.getDetail() + "\t程序员\t" + status + "\t\t\t\t\t" +  equipment.getDescription();
    }

    public Programmer(int id, String name, int age, double salary, int memberId, Status status, Equipment equipment) {
        super(id, name, age, salary);
        this.memberId = memberId;
        this.status = status;
        this.equipment = equipment;
    }

    public Programmer(int id, String name, int age, double salary) {
        super(id, name, age, salary);
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }


    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }
    public void getTeamInfo(){
        System.out.println(memberId + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t"
         + getSalary() + "\t" + "程序员\t");
    }
}
