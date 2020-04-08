package pl.lkre.tv.persistance;

import org.springframework.stereotype.Component;
import pl.lkre.tv.schedule.constants.StationNames;
import pl.lkre.tv.schedule.model.Station;

@Component
public class StorageServiceImpl implements StorageService {
    @Override
    public void save(Station station) {
        System.out.println("===Done---");
    }

    @Override
    public boolean exists(StationNames stationName) {
        return false;
    }

    @Override
    public Station load(StationNames stationName) {
        return null;
    }
}
