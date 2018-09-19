/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework01;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 佐藤孝史
 */
public class StaffMaster {
    
    private Integer id;
    private String name;
    private String gender;
    private int birth;
    private int age;
    private long myNumber;
    
    
    public StaffMaster(Integer id, String name, String gender, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        
        birthToAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
        
        birthToAge();
    }
    
    private void birthToAge() {
        Date now = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        age = (Integer.parseInt(sdf.format(now)) - this.birth) / 10000;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(long myNumber) {
        this.myNumber = myNumber;
    }
}
