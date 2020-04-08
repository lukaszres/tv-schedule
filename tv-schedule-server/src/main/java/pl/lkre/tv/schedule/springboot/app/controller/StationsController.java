package pl.lkre.tv.schedule.springboot.app.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lkre.tv.persistance.StorageService;
import pl.lkre.tv.schedule.model.Station;
import pl.lkre.tv.teleman.StationFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
@RequestMapping(value = "/stations/{stationName}")
public class StationsController {

    private final StorageService storageService;

    @Autowired
    public StationsController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    @ResponseBody
    public String station(@PathVariable("stationName") String stationName, Model model) throws IOException {
        StationFactory stationFactory = new StationFactory();


        final URL url = new URL("https://www.teleman.pl/program-tv/stacje/" + stationName);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        final StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }

        final Document document = Jsoup.parse(stringBuilder.toString());
        final Station station = stationFactory.build(document);

        storageService.save(station);

        return station.toString();
    }
}
