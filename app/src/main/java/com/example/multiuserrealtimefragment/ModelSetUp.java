package com.example.multiuserrealtimefragment;

public class ModelSetUp {

String dataSize, dataAddress, dataCity;

    public ModelSetUp(){



    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public void setDataAddress(String dataAddress) {
        this.dataAddress = dataAddress;
    }

    public String getDataCity() {
        return dataCity;
    }

    public void setDataCity(String dataCity) {
        this.dataCity = dataCity;
    }

    public ModelSetUp(String dataSize, String dataAddress, String dataCity) {
        this.dataSize = dataSize;
        this.dataAddress = dataAddress;
        this.dataCity = dataCity;
    }



}
