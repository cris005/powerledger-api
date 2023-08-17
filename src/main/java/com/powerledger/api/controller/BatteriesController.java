package com.powerledger.api.controller;


import com.powerledger.api.dto.BatteryDto;
import com.powerledger.api.service.BatteryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@EnableWebMvc
@Validated
public class BatteriesController {
    private final BatteryService batteryService;
    private static final Logger log = LogManager.getLogger();

    public BatteriesController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping("/batteries")
    ResponseEntity<List<UUID>> createBatteries(
            @RequestBody @NotEmpty(message = "List cannot be empty") List<@Valid BatteryDto> batteries) {
        List<UUID> batteryIds = new ArrayList<UUID>();

        for (BatteryDto battery : batteries) {
            var createdBattery = batteryService.CreateBattery(battery);
            batteryIds.add(createdBattery.getId());
        }

        log.info("Successfully created Battery records");
        return ResponseEntity.ok(batteryIds);
    }

    @GetMapping("/batteries")
    ResponseEntity<Iterable<BatteryDto>> listBatteries(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        var batteries = batteryService.ListBatteries();

        log.info("Successfully fetched a Batteries list");
        return ResponseEntity.ok(batteries);
    }

    @GetMapping("/batteries/{batteryId}")
    ResponseEntity<BatteryDto> getBattery(@PathVariable UUID batteryId) {
        var battery = batteryService.GetBattery(batteryId);

        log.info("Successfully fetched a Battery");
        return ResponseEntity.ok(battery);
    }
}
