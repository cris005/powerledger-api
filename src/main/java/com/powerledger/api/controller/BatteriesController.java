package com.powerledger.api.controller;


import com.powerledger.api.dto.BatteryDto;
import com.powerledger.api.service.BatteryService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


@RestController
@EnableWebMvc
public class BatteriesController {
    private final BatteryService batteryService;
    private static final Logger log = LogManager.getLogger();

    public BatteriesController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping("/batteries")
    ResponseEntity<BatteryDto> createBattery(@Valid @RequestBody BatteryDto battery) {
        var createdBattery = batteryService.CreateBattery(battery);

        log.info("Successfully created a Battery record");
        return ResponseEntity.ok(createdBattery);
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
