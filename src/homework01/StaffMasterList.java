/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 佐藤孝史
 */
public class StaffMasterList {
    
    private ArrayList<StaffMaster> dataList;
    public static final int SORT_MODE_SELECT = 1;
    public static final int SORT_MODE_BUCKET = 2;
    
    public StaffMasterList() {
        dataList = new ArrayList<StaffMaster>();
    }
    
    public StaffMasterList(String fileName) {
        dataList = new ArrayList<StaffMaster>();
        
        int size;
        char[] cbuf = new char[2];
        
        try {
            FileInputStream fs = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
            StringWriter sWriter = new StringWriter();
            
            //InputStreamReaderクラスのreadメソッドでファイルを1文字ずつ読み込む
            while((size = isr.read(cbuf)) != -1) {
                //System.out.println((char)data);
                sWriter.write(cbuf, 0, size);
            }
            
            System.out.println(sWriter.toString());
            String s = sWriter.toString();
            String[] list = s.split("[,\n]", 0);
            
            for (int i = 4; i < list.length; i+=4) {
                addData(Integer.parseInt(list[i]), list[i+1], list[i+2], Integer.parseInt(list[i+3]));
            }
            
            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addData(int id, String name, String gender, int birth) {
        dataList.add(new StaffMaster(id, name, gender, birth));
    }
    
    public ArrayList getDataList() {
        return dataList;
    }
    
    public StaffMaster getData(int index) {
        return dataList.get(index);
    }
    
    public int searchID(int id) {
        
        int index = 99999;
        
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public void sortAge(int sortMode) {
        
        switch (sortMode) {
            case SORT_MODE_SELECT:
                selectSortAge();
                break;
            case SORT_MODE_BUCKET:
                bucketSortAge();
                break;
            default:
                System.out.println("そんなモードはありません");
                break;
        }
    }
    
    private void selectSortAge() {
        
        System.out.println("*選択ソート*");
        
        int[] array = new int[dataList.size()];
        
        for (int i = 0; i < dataList.size(); i++) {
            array[i] = dataList.get(i).getAge();
        }

        for(int i = 0; i < dataList.size(); i++ ){
            int index = i;
            for(int j = i + 1; j < dataList.size(); j++){
                if(dataList.get(j).getAge() < dataList.get(index).getAge()){
                    index = j;
                }
            }
            if(i != index){
                StaffMaster tmp = dataList.get(index);
                dataList.set(index, dataList.get(i));
                dataList.set(i,tmp);
            }
        }
    }
    
    private void bucketSortAge() {
        
        System.out.println("*バケットソート*");
        
        List<List> bucket = new ArrayList<List>();
        for (int i = 0; i < 150; i++) {
            bucket.add(new ArrayList<StaffMaster>());
        }
        
        for (int i = 0; i < dataList.size(); i++) {
            bucket.get(dataList.get(i).getAge()).add(dataList.get(i));
        }
        
        int j = 0;
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i) != null) {
                while (bucket.get(i).size() > 0) {
                    StaffMaster tmp = (StaffMaster)bucket.get(i).get(0);
                    bucket.get(i).remove(0);
                    dataList.set(j, tmp);
                    j++;
                }
            }
        }
    }
    
    public void outputList() {
        for (StaffMaster data: dataList) {
            System.out.println("ID: " + data.getId()
                            + "\n名前: " + data.getName()
                            + "\n性別: " + data.getGender()
                            + "\n年齢: " + data.getAge()
                            + "\nマイナンバー: " + data.getMyNumber());
        }
        System.out.println("データ数: " + dataList.size());
    }
}
