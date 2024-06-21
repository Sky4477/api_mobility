package app.mobility.Smart_mobility.Web.Controller;

import app.mobility.Smart_mobility.Model.GeoFile;
import app.mobility.Smart_mobility.Service.Interf.GeoFileService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/geofiles")
@AllArgsConstructor
public class GeoFileController {

    private static final Logger logger = LoggerFactory.getLogger(GeoFileController.class);
    private final GeoFileService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Received file upload request: " + file.getOriginalFilename());
            GeoFile savedFile = service.uploadGeoFile(file);
            logger.info("File uploaded successfully: " + savedFile.getFilename());
            return new ResponseEntity<>("File uploaded successfully: " + savedFile.getFilename(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Error uploading file: " + e.getMessage());
            return new ResponseEntity<>("Unsupported file type: " + file.getContentType(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error uploading file: " + e.getMessage(), e);
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<GeoFile> getFileByName(@PathVariable String filename) {
        GeoFile file = service.getFileByName(filename);
        if (file != null) {
            return new ResponseEntity<>(file, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GeoFile>> getAllFiles() {
        List<GeoFile> files = service.getAllFiles();
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}
