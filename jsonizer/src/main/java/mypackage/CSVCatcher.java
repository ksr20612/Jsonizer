package mypackage;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVCatcher {
    
    private static CSVCatcher instance;

    private CSVCatcher(){};

    public static CSVCatcher getInstance(){

        if(instance == null){
            instance = new CSVCatcher();
        }

        return instance;
    }

    public List<String[]> readCSV(String csvPath) throws IOException, CsvException {

        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvPath),"EUC-KR"));

        List<String[]> csvList = reader.readAll();
            
        if(csvList.get(0).length != 14) {
            System.out.println("csv파일의 칼럼 수가 다릅니다.\n 프로그램을 종료합니다.");
            System.exit(0);
        }

        return csvList;

    }

}
