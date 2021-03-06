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
    
    private int id;
    private String name;
    private String gender;
    private int birth;
    private int age;
    
    
    public StaffMaster(int id, String name, String gender, int birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        
        birthToAge();
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getBirth() {
        return birth;
    }
    
    private void birthToAge() {
        Date now = new Date();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        age = (Integer.parseInt(sdf.format(now)) - this.birth) / 10000;
    }

    public int getAge() {
        return age;
    }
}
