package com.escaneoRedes.Escaneo.Redes.dto;

public class ConfiguracionRefreshDTO {

    private Boolean autoRefresh;
    private Integer refreshInterval;

    public ConfiguracionRefreshDTO() {
    }

    public ConfiguracionRefreshDTO(Boolean autoRefresh, Integer refreshInterval) {
        this.autoRefresh = autoRefresh;
        this.refreshInterval = refreshInterval;
    }

    public Boolean getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public Integer getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(Integer refreshInterval) {
        this.refreshInterval = refreshInterval;
    }
}
