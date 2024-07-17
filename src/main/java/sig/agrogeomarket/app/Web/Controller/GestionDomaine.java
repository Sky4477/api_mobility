package sig.agrogeomarket.app.Web.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sig.agrogeomarket.app.Model.TerrainAgricole;
import sig.agrogeomarket.app.Service.Interface.TerrainAgricoleService;

import java.util.List;

@RestController
@RequestMapping("/api/domaine")
@AllArgsConstructor
public class GestionDomaine {

    private final TerrainAgricoleService terrainAgricoleService;
    @GetMapping
    public ResponseEntity<List<TerrainAgricole>> getAllDomaines() {
        return ResponseEntity.ok().body(terrainAgricoleService.findAll());
    }
    @PostMapping("/create")
    public ResponseEntity<TerrainAgricole> createTerrainAgricole(@RequestBody MultipartFile file) {
      try {
            TerrainAgricole terrainAgricole = terrainAgricoleService.createFromESRIFile(file);
            if (terrainAgricole != null) {
                return ResponseEntity.ok().body(terrainAgricole);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
