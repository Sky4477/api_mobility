package app.mobility.Smart_mobility;

import app.mobility.Smart_mobility.Model.Station;
import app.mobility.Smart_mobility.Service.Interf.StationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SmartMobilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMobilityApplication.class, args);
	}

	@Bean
	CommandLineRunner start(StationService stationService) {
		return args -> {

			List<String> filePaths = new ArrayList<String>();
			filePaths.add("src/main/resources/data/busTunis.geojson");
			filePaths.add("src/main/resources/data/metroTunis.geojson");
			filePaths.add("src/main/resources/data/Station_tgm.geojson");
			List<Station> stations = stationService.getAllStationsFromFiles("Bus",filePaths.get(0));
			stations.addAll(stationService.getAllStationsFromFiles("Metro",filePaths.get(1)));
			stations.addAll(stationService.getAllStationsFromFiles("TGM",filePaths.get(2)));



		};
	}

}
