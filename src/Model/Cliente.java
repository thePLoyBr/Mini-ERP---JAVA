/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author felip
 */
public class Cliente {
    private int id;
    private String name;
    private int age;
    private String tel;
    
    public Cliente(){
    }
    
    public Cliente (String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public Cliente (String name, int age, String tel){
        this.name = name;
        this.age = age;
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
