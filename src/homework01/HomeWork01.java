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

/**
 *
 * @author 佐藤孝史
 */
public class HomeWork01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        loadTextFile();
    }
    
    static void loadTextFile() {
        
        System.out.println("\n--- LoadFileTest ---\n");
        
        // インスタンス生成時、データファイル読込登録
        StaffMasterList staffList = new StaffMasterList("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/従業員マスタ.txt");
        
        // ファイルのマージ
        mergeFile(staffList);
    }
    
    static void mergeFile(StaffMasterList staffList) {
        
        int size;
        char[] cbuf = new char[2];
        
        try {
            // マージするファイルを読込
            FileInputStream fs = new FileInputStream("C:/Users/佐藤孝史/Documents/NetBeansProjects/TestJava/src/testjava/TextFile/個人番号マスタ.txt");
            InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
            StringWriter sWriter = new StringWriter();
            
            // InputStreamReaderクラスのreadメソッドでファイルを1文字ずつ読み込む
            while((size = isr.read(cbuf)) != -1) {
                sWriter.write(cbuf, 0, size);
            }
            
            String s = sWriter.toString();
            // テキストファイル内にある特殊文字を","に変換
            s = s.replace("\r\n", ",");
            s = s.replace("\r", ",");
            s = s.replace("\n", ",");
            // ","で切り分け
            String[] list = s.split("[,\n\r]", 0);
            
            // マージ
            for (int i = 2; i < list.length; i+=2) {
                staffList.getData(staffList.searchID(Integer.parseInt(list[i]))).setMyNumber(Long.parseLong(list[i+1]));
            }
            
            staffList.outputList();
            
            System.out.println("\n--- SortFileTest ---\n");
            
            // 年齢順でソート
            staffList.sortAge(StaffMasterList.SORT_MODE_SELECT);
            
            staffList.outputList();

            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
