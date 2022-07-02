package com.xyd.team.domain;

/**
 * @author xyd
 * @create 2022-06-26 19:55
 */
public class Designer extends Programmer{
    private double bonus;//奖金

    public Designer(int id, String name, int age, double salary, double bonus) {
        super(id, name, age, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return this.getDetail() + "\t设计师\t" + this.getStatus() + "\t" + this.getBonus()
        + "\t\t\t" + this.getEquipment().getDescription();
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }
    @Override
    public void getTeamInfo(){
        System.out.println(getMemberId() + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t"
                + getSalary() + "\t" +  "设计师\t"  + getBonus() );
    }
}
