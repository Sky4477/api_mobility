package app.mobility.Smart_mobility.Service.Interf;

import app.mobility.Smart_mobility.Model.GeoFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GeoFileService {
    GeoFile uploadGeoFile(MultipartFile file) throws IOException;
    GeoFile getFileByName(String filename);
    List<GeoFile> getAllFiles();
}
