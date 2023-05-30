package com.example.multiuserrealtimefragment;

public class ModelMaintenance {

    String dataSizeMaintenance, dataAddressMaintenance, dataCityMaintenance;

    public ModelMaintenance(){


    }

    public String getDataSizeMaintenance() {
        return dataSizeMaintenance;
    }

    public void setDataSizeMaintenance(String dataSizeMaintenance) {
        this.dataSizeMaintenance = dataSizeMaintenance;
    }

    public String getDataAddressMaintenance() {
        return dataAddressMaintenance;
    }

    public void setDataAddressMaintenance(String dataAddressMaintenance) {
        this.dataAddressMaintenance = dataAddressMaintenance;
    }

    public String getDataCityMaintenance() {
        return dataCityMaintenance;
    }

    public void setDataCityMaintenance(String dataCityMaintenance) {
        this.dataCityMaintenance = dataCityMaintenance;
    }

    public ModelMaintenance(String dataSizeMaintenance, String dataAddressMaintenance, String dataCityMaintenance) {
        this.dataSizeMaintenance = dataSizeMaintenance;
        this.dataAddressMaintenance = dataAddressMaintenance;
        this.dataCityMaintenance = dataCityMaintenance;
    }
}
