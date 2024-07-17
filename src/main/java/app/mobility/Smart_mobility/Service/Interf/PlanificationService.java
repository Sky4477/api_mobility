package app.mobility.Smart_mobility.Service.Interf;

import app.mobility.Smart_mobility.Model.HorraireData;
import app.mobility.Smart_mobility.Model.Station;

import java.util.List;

public interface PlanificationService {
    List<HorraireData> getPlanification();
    List<HorraireData> getAllHorraireData();
    List<HorraireData> getHorraireDataByStation(String stationName);
    List<Station> getEstimationTimeFormUserLocation(String latitude, String longitude);


}
