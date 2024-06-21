package app.mobility.Smart_mobility.Service.Imp;

import app.mobility.Smart_mobility.Model.GeoFile;
import app.mobility.Smart_mobility.Repository.GeoFileRepository;
import app.mobility.Smart_mobility.Service.Interf.GeoFileService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GeoFileServiceImp implements GeoFileService {
    private final GeoFileRepository repo;

    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/tiff", "application/x-shapefile", "application/geo+json");

    @Override
    public GeoFile uploadGeoFile(MultipartFile file) throws IOException {
        String fileType = file.getContentType();
        if (fileType == null || !ALLOWED_FILE_TYPES.contains(fileType)) {
            throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        String filename = file.getOriginalFilename();
        GeoFile fileGeoFile = new GeoFile();
        fileGeoFile.setFilename(filename);
        fileGeoFile.setFileType(fileType);
        fileGeoFile.setData(file.getBytes());
        return repo.save(fileGeoFile);
    }

    @Override
    public GeoFile getFileByName(String filename) {
        return repo.findByFilename(filename).orElse(null);
    }

    @Override
    public List<GeoFile> getAllFiles() {
        return repo.findAll();
    }
}
