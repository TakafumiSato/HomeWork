/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework01;

import static homework01.HomeWork01.staffList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 *
 * @author 佐藤孝史
 */
public class FileController {
    
    public static String[] loadTextFile(String path) {
        
        String[] textList = null;
        int size;
        char[] cbuf = new char[2];
        
        try {
            // ファイルの読込
            FileInputStream fs = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
            StringWriter sWriter = new StringWriter();
            
            while((size = isr.read(cbuf)) != -1) {
                sWriter.write(cbuf, 0, size);
            }
            
            String s = sWriter.toString();
            // テキストファイル内にある特殊文字を","に変換
            s = s.replace("\r\n", ",");
            s = s.replace("\r", ",");
            s = s.replace("\n", ",");
            // ","で切り分け
            textList = s.split("[,\n\r]", 0);

            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        if (textList == null) {
            System.out.println("読込失敗");
        }
        
        return textList;
    }
    
    public static void outputStaffMaster(ArrayList<StaffMyNumber> dataList, String fileName) {
        try {
            File file = new File(fileName);//"./src/homework01/textFiles/test.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(fileName);//"./src/homework01/textFiles/test.txt");
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            
            pw.println("社員コード,名前,性別,生年月日,年齢,マイナンバー");
            for (int i = 0; i < dataList.size(); i++) {
                pw.println(dataList.get(i).getID() + ","                                    // 従業員コード
                            + dataList.get(i).getName() + ","                               // 名前
                            + dataList.get(i).getGender() + ","                             // 性別
                            + dataList.get(i).getBirth() + ","                              // 生年月日
                            + dataList.get(i).getAge() + ","                                // 年齢
                            + String.format("%012d", dataList.get(i).getMyNumber()));       // 年齢)
            }
            
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
