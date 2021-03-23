package mypackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMaker {
    
    private static JsonMaker instance;

    private JsonMaker(){}

    public static JsonMaker getInstance() {

        if (instance == null){
            instance = new JsonMaker();
        }

        return instance;
    } 

    public void createJson(String[] list, String jsonDir) throws JsonProcessingException {

        if(list.length != 14){
            System.out.println("전달받은 변수의 개수가 다릅니다.\n 프로그램을 종료합니다.");
            System.exit(0);
        }

        // assign json values
        /*
            index
            0 : fileName.wav
            1 : recordingDate
            2 : samplingRate;
            3 : numberOfBit
            4 : numberOfChannel
            5 : SpeakerName
            6 : gender
            7 : age
            8 : residence
            9 : dialect 
            10 : place where recording was hold
            11 : device used
            12 : fileLength
        */

        Json json = new Json();
        String fileName = list[0].replace(".wav",".json");
        json.기본정보.RecordingDate = list[1];
        json.음성정보.SamplingRate = list[2];
        json.음성정보.NumberOfBit = list[3];
        json.음성정보.NumberOfChannel = list[4];
        json.전사정보.LabelText = list[5];
        json.화자정보.SpeakerName = list[6];
        json.화자정보.Gender = list[7];
        json.화자정보.Age = list[8];
        json.화자정보.Region = list[9];
        json.화자정보.Dialect = list[10];
        json.환경정보.RecordingEnviron = list[11];
        json.환경정보.NoiseEnviron = list[11];
        json.환경정보.RecordingDevice = list[12];
        json.파일정보.FileLength = list[13];
        json.파일정보.FileName = list[0];
        json.파일정보.DirectoryPath = json.파일정보.DirectoryPath + "/" + list[0].split("-")[0] + "/" + list[0];
        
        File file = new File(jsonDir+"/"+fileName);

        // serialization by "JACKSON"
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(json);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(result);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
