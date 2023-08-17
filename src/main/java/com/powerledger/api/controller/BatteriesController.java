package com.powerledger.api.controller;


import com.powerledger.api.model.Battery;
import com.powerledger.api.model.BatteryData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


@RestController
@EnableWebMvc
public class BatteriesController {
    @RequestMapping(path = "/batteries", method = RequestMethod.POST)
    public Battery createBattery(@RequestBody Battery newBattery) {
        if (newBattery.getName() == null || newBattery.getPostcode() == null) {
            return null;
        }

        Battery dbBattery = newBattery;
        dbBattery.setId(UUID.randomUUID().toString());
        return dbBattery;
    }

    @RequestMapping(path = "/batteries", method = RequestMethod.GET)
    public Battery[] listBatteries(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        int queryLimit = 10;
        if (limit.isPresent()) {
            queryLimit = limit.get();
        }

        Battery[] outputBatteries = new Battery[queryLimit];

        for (int i = 0; i < queryLimit; i++) {
            Battery newBattery = new Battery();
            newBattery.setId(UUID.randomUUID().toString());
            newBattery.setName(BatteryData.getRandomName());
            newBattery.setPostcode(BatteryData.getRandomPostcode());
            newBattery.setCapacity(BatteryData.getRandomCapacity());
            outputBatteries[i] = newBattery;
        }

        return outputBatteries;
    }

    @RequestMapping(path = "/batteries/{batteryId}", method = RequestMethod.GET)
    public Battery listPets(@PathVariable String batteryId) {
        Battery newBattery = new Battery();
        newBattery.setId(UUID.randomUUID().toString());
        newBattery.setName(BatteryData.getRandomName());
        newBattery.setPostcode(BatteryData.getRandomPostcode());
        newBattery.setCapacity(BatteryData.getRandomCapacity());
        return newBattery;
    }

}
