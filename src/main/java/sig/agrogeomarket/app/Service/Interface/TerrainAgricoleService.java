package sig.agrogeomarket.app.Service.Interface;

import org.springframework.web.multipart.MultipartFile;
import sig.agrogeomarket.app.Model.TerrainAgricole;

import java.util.List;

public interface TerrainAgricoleService{
    TerrainAgricole create(TerrainAgricole terrainAgricole);
    TerrainAgricole createFromESRIFile(MultipartFile file);

    TerrainAgricole update(TerrainAgricole terrainAgricole);

    void delete(Long id);

    TerrainAgricole findById(Long id);

    List<TerrainAgricole> findAll();
    List<List<TerrainAgricole>> isPossede();


}
