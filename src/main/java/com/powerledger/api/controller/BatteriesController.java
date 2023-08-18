package com.powerledger.api.controller;

import com.powerledger.api.annotation.Postcode;
import com.powerledger.api.dto.BatteryDto;
import com.powerledger.api.exception.ResourceNotFoundException;
import com.powerledger.api.response.BatteriesListResponse;
import com.powerledger.api.service.BatteryService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.*;

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

        batteries.forEach(batteryDto -> {
            BatteryDto createdBattery = batteryService.CreateBattery(batteryDto);
            batteryIds.add(createdBattery.getId());
        });

        log.info("Successfully created Battery records");
        return ResponseEntity.ok(batteryIds);
    }

    @GetMapping("/batteries")
    ResponseEntity<BatteriesListResponse> listBatteries(
            @RequestParam("minPostcode") Optional<@Postcode(optional = true) String> minPostcode,
            @RequestParam("maxPostcode") Optional<@Postcode(optional = true) String> maxPostcode) {

        if (!isPostcodeRangeValid(minPostcode, maxPostcode)) {
            throw new ConstraintViolationException("Min. postcode must be lesser than max postcode", null);
        }

        List<BatteryDto> batteries = batteryService.ListBatteries(
            minPostcode.orElse("0"),
            maxPostcode.orElse("9999")
        );

        BatteriesListResponse response = makeBatteriesListResponse(batteries);
        log.info("Successfully fetched a Batteries list");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/batteries/{batteryId}")
    ResponseEntity<BatteryDto> getBattery(@PathVariable UUID batteryId) throws ResourceNotFoundException {
        var battery = batteryService.GetBattery(batteryId);

        if (battery == null) {
            log.info("Battery not found");
            throw new ResourceNotFoundException("The ID provided does not belong to an existing Battery");
        }

        log.info("Successfully fetched a Battery");
        return ResponseEntity.ok(battery);
    }

    private boolean isPostcodeRangeValid(Optional<String> min, Optional<String> max) {
        boolean bothExist = min.isPresent() && max.isPresent();

        // Only 1 end of the range provided, so no conflicts
        if (!bothExist) {
            return true;
        }

        // If min is lesser than max, then no issues (range is valid)
        return Integer.parseInt(min.get()) < Integer.parseInt(max.get());
    }

    private BatteriesListResponse makeBatteriesListResponse(List<BatteryDto> batteries) {
        int batteriesCount = batteries.size();
        int totalCapacity = batteries.stream().mapToInt(BatteryDto::getCapacity).sum();
        List<String> batteryNames = batteries.stream().map(BatteryDto::getName).toList();

        BatteriesListResponse response = new BatteriesListResponse();
        response.setCount(batteriesCount);
        response.setTotalCapacity(totalCapacity);
        // Must default to 0, else this will try divide by 0 and throw errors
        response.setAverageCapacity(batteriesCount > 0 ? totalCapacity / batteriesCount : 0);
        response.setBatteries(batteryNames);

        return response;
    }
}
