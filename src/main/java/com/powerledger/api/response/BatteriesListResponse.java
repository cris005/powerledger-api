package com.powerledger.api.response;

import java.util.List;

public class BatteriesListResponse {
    private Integer count;
    private Integer totalCapacity;
    private Integer averageCapacity;
    private List<String> batteries;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getAverageCapacity() {
        return averageCapacity;
    }

    public void setAverageCapacity(Integer averageCapacity) {
        this.averageCapacity = averageCapacity;
    }

    public List<String> getBatteries() {
        return batteries;
    }

    public void setBatteries(List<String> batteries) {
        this.batteries = batteries;
    }
}
