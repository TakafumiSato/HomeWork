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
public class HomeWork01 {
    
    public static final int SORT_MODE_SELECT = 1;
    public static final int SORT_MODE_BUCKET = 2;
    
    static ArrayList<StaffMaster> staffList = new ArrayList<StaffMaster>();
    static ArrayList<MyNumber> myNumberList = new ArrayList<MyNumber>();
    static ArrayList<StaffMyNumber> staffMyNumberList = new ArrayList<StaffMyNumber>();
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // テキストファイルの読込
        loadFile();
        
        // 従業員マスタと個人番号マスタのマージ
        mergeData();
        
        // マージしたファイルを年齢順にソート
        sortAge(SORT_MODE_SELECT);
        
        // ソートしたファイルをテキストデータとして出力
        FileController.outputStaffMaster(staffMyNumberList, "./src/homework01/textFiles/staffMyNumber.txt");
    }
    
    public static void loadFile() {
        
        // 従業員マスタデータを読込
        loadStaffMasterFile();
        
        // 個人番号データを読込
        loadMyNumberFile();
    }
    
    public static void loadStaffMasterFile() {
        
        // テキストデータを読込
        String[] dataList = FileController.loadTextFile("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/従業員マスタ.txt");
        
        // StaffMasterクラスに落し込み
        for (int i = 4; i < dataList.length; i+=4) {
            staffList.add(new StaffMaster((Integer.parseInt(dataList[i])),          // 従業員コード
                                            dataList[i+1],                          // 名前
                                            dataList[i+2],                          // 性別
                                            Integer.parseInt(dataList[i+3])));      // 生年月日
        }
    }
    
    public static void loadMyNumberFile() {
        
        // テキストデータを読込
        String[] dataList = FileController.loadTextFile("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/個人番号マスタ.txt");
        
        // MyNumberクラスに落し込み
        for (int i = 2; i < dataList.length; i+=2) {
            myNumberList.add(new MyNumber((Integer.parseInt(dataList[i])),          // 従業員コード
                                        Long.parseLong(dataList[i+1])));            // マイナンバー
        }
    }
    
    static void mergeData() {
        
        // StaffMasterのIDと照らし合わせて整列させた個人番号を配列で確保
        long[] numbers = new long[staffList.size()];
        for (int i = 0; i < myNumberList.size(); i++) {
            
            numbers[searchID(myNumberList.get(i).getID())] = myNumberList.get(i).getMyNumber();
        }
        
        // StaffMasterの従業員番号から年齢までと整列させたnumbers(MyNumber)を
        // StaffMyNumberへと落し込み
        for (int i = 0; i < staffList.size(); i++) {
            staffMyNumberList.add(new StaffMyNumber(staffList.get(i).getID(),
                                                    staffList.get(i).getName(),
                                                    staffList.get(i).getGender(),
                                                    staffList.get(i).getBirth(),
                                                    staffList.get(i).getAge(),
                                                    numbers[i]));
        }
    }
    
    static int searchID(int id) {
        
        int index = 99999;

        // 該当するIDを抽出
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getID() == id) {
                index = i;
                break;
            }
        }

        return index;
    }
    
    public static void sortAge(int sortMode) {
        
        // ソートの種類を選択
        switch (sortMode) {
            // 選択ソート
            case SORT_MODE_SELECT:
                selectSortAge();
                break;
            // バケットソート
            case SORT_MODE_BUCKET:
                bucketSortAge();
                break;
            default:
                System.out.println("そんなモードはありません");
                break;
        }
    }
    
    public static void selectSortAge() {
        
        System.out.println("*選択ソート*");
        
        int[] array = new int[staffMyNumberList.size()];
        
        for (int i = 0; i < staffMyNumberList.size(); i++) {
            array[i] = staffMyNumberList.get(i).getAge();
        }

        for(int i = 0; i < staffMyNumberList.size(); i++ ){
            int index = i;
            for(int j = i + 1; j < staffMyNumberList.size(); j++){
                if(staffMyNumberList.get(j).getAge() < staffMyNumberList.get(index).getAge()){
                    index = j;
                }
            }
            if(i != index){
                StaffMyNumber tmp = staffMyNumberList.get(index);
                staffMyNumberList.set(index, staffMyNumberList.get(i));
                staffMyNumberList.set(i,tmp);
            }
        }
    }
    
    public static void bucketSortAge() {
        
        System.out.println("*バケットソート*");
        
        List<List> bucket = new ArrayList<List>();
        for (int i = 0; i < 150; i++) {
            bucket.add(new ArrayList<StaffMaster>());
        }
        
        for (int i = 0; i < staffMyNumberList.size(); i++) {
            bucket.get(staffMyNumberList.get(i).getAge()).add(staffMyNumberList.get(i));
        }
        
        int j = 0;
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i) != null) {
                while (bucket.get(i).size() > 0) {
                    StaffMyNumber tmp = (StaffMyNumber)bucket.get(i).get(0);
                    bucket.get(i).remove(0);
                    staffMyNumberList.set(j, tmp);
                    j++;
                }
            }
        }
    }
}