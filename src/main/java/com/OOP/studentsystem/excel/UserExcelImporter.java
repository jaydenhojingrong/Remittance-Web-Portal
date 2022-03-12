package com.OOP.studentsystem.excel;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.OOP.studentsystem.model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserExcelImporter {

    public List<Student> excelImport() {
        List<Student> listTest = new ArrayList<Student>();
        String excelFilePath = "C:\\Users\\Shawn\\Desktop\\Book2.csv";


        try {
            File file = new File(excelFilePath);
            Scanner fileScan = new Scanner(file);
            int count = 0;
            while (fileScan.hasNext()) {
                if (count == 0) {

                    count++;
                    fileScan.nextLine();
                    continue;
                }
                count++;

                String currentLine = fileScan.nextLine();
                Scanner scanToSplit = new Scanner(currentLine).useDelimiter(",");

                int id = Integer.parseInt(scanToSplit.next());

                String name = scanToSplit.next();

                String address = scanToSplit.next();

                Student newEntry = new Student();
                newEntry.setId(id);
                newEntry.setName(name);
                newEntry.setAddress(address);
                listTest.add(newEntry);
                scanToSplit.close();
            }
            System.out.printf("\n Number of rows processed %d  \n ", count);

            fileScan.close();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        return listTest;
    }
}
