package mypackage;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.List;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.exceptions.CsvException;

public class App extends JFrame{

    String csvPath;
    String resultPath;
    JButton csvBtn = new JButton("CSV 열기");
    JButton resultBtn = new JButton("저장 폴더 선택");
    JButton goBtn = new JButton("실행");
    JLabel csvLabel = new JLabel("");
    JLabel resultLabel = new JLabel("");

    private App() {

        this.init();
        this.addEvent();
        this.setTitle("JSONIZER");
        this.setSize(400,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    
    }

    private void init(){

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        // 추가
        container.add(csvBtn);
        container.add(resultBtn);
        container.add(goBtn);
        container.add(csvLabel);
        container.add(resultLabel);

    }

    private void addEvent(){

        csvBtn.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){

                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("./"));
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                FileNameExtensionFilter filter= new FileNameExtensionFilter("csv files (*.csv)","csv");
                jfc.setFileFilter(filter);
                jfc.addChoosableFileFilter(filter);
                jfc.setMultiSelectionEnabled(false);
                
                int returnVal = jfc.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    csvPath = jfc.getSelectedFile().toString();
                    csvLabel.setText("선택된 CSV :" + csvPath);
                }
            }

        }); 

        resultBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){

                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("./"));
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                jfc.setMultiSelectionEnabled(false);
                
                int returnVal = jfc.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    resultPath = jfc.getSelectedFile().toString();
                    resultLabel.setText("저장 위치 :" + resultPath);
                }
            }

        });

        goBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(csvPath.isEmpty() || resultPath.isEmpty()){
                    JOptionPane.showMessageDialog(null, "먼저 경로를 선택해주세요.");
                }else {

                    CSVCatcher csvCatcher = CSVCatcher.getInstance();

                    List<String[]> csvList = null;
                    try {
                        csvList = csvCatcher.readCSV(csvPath);
                    } catch (IOException | CsvException e1) {
                        e1.printStackTrace();
                    }

                    JsonMaker jsonMaker = JsonMaker.getInstance();
                    
                    if(csvList == null) {
                        JOptionPane.showMessageDialog(null, "CSV 파일을 읽어오지 못했습니다. 다시 한번 실행해주세요.");
                        System.exit(0);
                    }

                    for(int i=1;i<csvList.size();i++){
                        try {
                            jsonMaker.createJson(csvList.get(i), resultPath);
                        } catch (JsonProcessingException e1) {
                            e1.printStackTrace();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "모든 파일이 정상적으로 생성되었습니다!");

                }

            }

        });
    }

    public static void main( String[] args ) {

        // GUI Application 
        App app = new App();

    }

}
