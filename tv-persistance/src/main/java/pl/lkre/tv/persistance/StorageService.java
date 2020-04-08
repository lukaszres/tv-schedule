package pl.lkre.tv.persistance;

import pl.lkre.tv.schedule.constants.StationNames;
import pl.lkre.tv.schedule.model.Station;

public interface StorageService {

    void save(Station station);

    boolean exists(StationNames stationName);

    Station load(StationNames stationName);
}
