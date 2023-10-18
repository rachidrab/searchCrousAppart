package com.example.crous.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@Component
public class ItemChecker {

    @Autowired
    private ItemService itemService;

    @Scheduled(fixedRate = 30000) // Exécute toutes les 30 secondes
    public void checkAndOpenLink() {
        List<Item> items = itemService.fetchItems();

        if (!items.isEmpty()) {
            String id = items.get(0).getId();
            String link = "https://trouverunlogement.lescrous.fr/tools/32/accommodations/" + id;

            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (IOException | java.net.URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
