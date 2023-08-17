package com.powerledger.api.repository;

import com.powerledger.api.model.Battery;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BatteryRepository extends CrudRepository<Battery, UUID> {
}
