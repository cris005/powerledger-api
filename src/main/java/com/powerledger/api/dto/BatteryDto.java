package com.powerledger.api.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.util.UUID;

public class BatteryDto {
    private UUID id;

    @Pattern(
            regexp = "(0[289][0-9]{2})|([1-9][0-9]{3})",
            message = "Postcode must fall within the following ranges: 0200-0299, 0800-0999 and 1000-9999"
    )
    private String postcode;

    @NotBlank(message="Name cannot be blank")
    private String name;

    @Min(value = 0, message = "Capacity must be greater than 0")
    private Integer capacity;

    private Instant createdAt;
    private Instant updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** @return A 4-digit postcode */
    public String getPostcode() {
        return postcode;
    }

    /** @param postcode A 4-digit postcode */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /** @return Capacity in watts */
    public Integer getCapacity() {
        return capacity;
    }

    /** @param capacity Capacity in watts */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
