package epam.project.util;

import epam.project.entity.Bicycle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

   private static final Logger LOGGER = LogManager.getLogger(Parser.class);
   private List<Bicycle> bicycles;
   
   public List<Bicycle> parse() {
       bicycles = new ArrayList<>();
       parseFromSpeedyGo();
       parseFromProstoProkat();
        return bicycles;
   }

    private void parseFromSpeedyGo() {
       
        try {
            Document doc = Jsoup.connect("https://speedygo.by/bicycle-rental/").get();
            Elements bicycleNames = doc.select(".caption-item-bicycle");
            Elements links = doc.select(".item-bicycle").select("a[href]");

            for (Element names : bicycleNames) {
                Bicycle bicycle = new Bicycle();
                bicycle.setPointHireId(1);
                bicycle.setName(names.text());
                bicycles.add(bicycle);
            }
            for (int i = 0; i < links.size(); i++) {
                bicycles.get(i).setLink(links.get(i).attr("abs:href"));
                Document descriptionDoc = Jsoup.connect(links.get(i).attr("abs:href")).get();
                Element description = descriptionDoc.getElementsByClass("clear-container product-description").first();
                bicycles.get(i).setDescription(description.text());
            }
        }
        catch (IOException e) {
            LOGGER.error("Failed to parse site",e);
        }
    }
    private void parseFromProstoProkat() {
     
        try {
            Document doc = Jsoup.connect("https://prostoprokat.by").get();

            Elements bicycleNames = doc.select(".sp-smart-title");
            Elements bicycleDescription = doc.select(".slider-text");

            for (int i=0;i<bicycleNames.size();i++) {
                Bicycle bicycle = new Bicycle();
                bicycle.setName(bicycleNames.get(i).text());
                bicycle.setPointHireId(2);
                bicycle.setLink("https://prostoprokat.by");
                bicycle.setDescription(bicycleDescription.get(i).text());
                bicycles.add(bicycle);
            }

        }
        catch (IOException e) {
            LOGGER.error("Failed to parse site",e);
        }
     
    }
}
