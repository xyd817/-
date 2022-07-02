package com.xyd.team.domain;

/**
 * @author xyd
 * @create 2022-06-26 19:58
 */
public class Architect extends Designer{
    private int stock;//股票

    @Override
    public String toString() {
        return this.getDetail() + "\t架构师\t" + this.getStatus() + "\t" + this.getBonus() + "\t"
                + this.getStock() + "\t" + this.getEquipment().getDescription();
    }

    public Architect(int id, String name, int age, double salary, double bonus, int stock) {
        super(id, name, age, salary, bonus);
        this.stock = stock;
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public void getTeamInfo(){
        System.out.println(getMemberId() + "/" + getId() + "\t\t" + getName() + "\t" + getAge() + "\t\t"
                + getSalary() + "\t" + "架构师\t"  + getBonus() + "\t" + getStock());
    }
}
