package com.powerledger.api.service;

import com.powerledger.api.dto.BatteryDto;
import com.powerledger.api.model.Battery;
import com.powerledger.api.repository.BatteryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BatteryService {
    private final BatteryRepository batteryRepository;

    public BatteryService(BatteryRepository batteryRepository){
        this.batteryRepository = batteryRepository;
    }

    public BatteryDto CreateBattery(BatteryDto batteryDto) {
        var battery = new Battery();
        battery.setId(UUID.randomUUID());
        battery.setName(batteryDto.getName());
        battery.setCapacity(batteryDto.getCapacity());
        battery.setPostcode(batteryDto.getPostcode());

        var savedBattery = this.batteryRepository.save(battery);

        return savedBattery.asDto();
    }

    public BatteryDto GetBattery(UUID batteryId) {
        var retrievedBattery = this.batteryRepository.findById(batteryId);

        return retrievedBattery.map(Battery::asDto).orElse(null);

    }

    public List<BatteryDto> ListBatteries(String min, String max) {
        List<Battery> batteries = this.batteryRepository.findAllByPostcodeBetweenOrderByNameAsc(min, max);

        return batteries.stream()
                .map(Battery::asDto)
                .toList();
    }
}
