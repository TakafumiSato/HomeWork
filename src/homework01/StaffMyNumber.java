/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework01;

/**
 *
 * @author 佐藤孝史
 */
public class StaffMyNumber {
    
    private int id;
    private String name;
    private String gender;
    private int birth;
    private int age;
    private long myNumber;
    
    public StaffMyNumber(int id, String name, String gender, int birth, int age, long myNumber) {
        
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
        this.myNumber = myNumber;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public int getBirth() {
        return this.birth;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public long getMyNumber() {
        return this.myNumber;
    }
}
