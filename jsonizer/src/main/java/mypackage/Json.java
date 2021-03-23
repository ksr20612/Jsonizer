package mypackage;

class BasicInfo {
    public final String Language = "KOR";
    public final String Version = "N/A";
    public final String ApplicationCategory = "N/A";
    public final String NumberOfSpeaker = "N/A";
    public final String DataCategory = "mariaDB";
    public String RecordingDate;
    public final String FillingDate = "N/A";
    public final String RevisionHistory = "N/A";
    public final String Distributor = "Mediazen";
}

class AudioInfo {
    public String SamplingRate;
    public final String ByteOrder = "N/A";
    public final String EncodingLaw = "SignedIntegerPCM";
    public String NumberOfBit = "16";
    public String NumberOfChannel = "1";
    public final String SignalToNoiseRatio = "N/A";
}

class TextInfo {
    public String LabelText;
}

class SpeakerInfo {
    public String SpeakerName;
    public String Gender;
    public String Age;
    public String Region;
    public String Dialect;
}

class EnvironInfo {
    public String RecordingEnviron;
    public String NoiseEnviron;
    public String RecordingDevice;
}

class FileInfo {
    public final String FileCategory = "Audio";
    public String FileName;
    public String DirectoryPath = "./metrixA/";
    public final String HeaderSize = "44";
    public String FileLength;
    public final String FileFormat = "PCM";
    public final String NumberOfRepeat = "1";
    public final String TimeInterval = "0";
    public final String Distance = "30";
}

class EtcInfo {
    public final String QualityStatus = "Good";
}

public class Json{
    
    public BasicInfo 기본정보 = new BasicInfo();
    public AudioInfo 음성정보 = new AudioInfo();
    public TextInfo 전사정보 = new TextInfo();
    public SpeakerInfo 화자정보 = new SpeakerInfo();
    public EnvironInfo 환경정보 = new EnvironInfo();
    public FileInfo 파일정보 = new FileInfo();
    public EtcInfo 기타정보 = new EtcInfo();

}
