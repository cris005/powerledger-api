package com.powerledger.api.model;


import com.powerledger.api.dto.BatteryDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "batteries")
public class Battery {
    public Battery() {}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "postcode", nullable = false)
    private String postcode;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
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

    public BatteryDto asDto()
    {
        BatteryDto dto = new BatteryDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCapacity(this.capacity);
        dto.setPostcode(this.postcode);
        dto.setCreatedAt(this.createdAt);
        dto.setUpdatedAt(this.updatedAt);

        return dto;
    }
}
