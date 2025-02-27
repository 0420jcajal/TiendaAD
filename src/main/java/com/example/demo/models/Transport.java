package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "transports")
public class Transport {
    
    @Id
    private String id;
    private String category;
    private String license;
    private double capacity;
    public Transport() {
    }
    public Transport(String id, String category, String license, double capacity) {
        this.id = id;
        this.category = category;
        this.license = license;
        this.capacity = capacity;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((license == null) ? 0 : license.hashCode());
        long temp;
        temp = Double.doubleToLongBits(capacity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transport other = (Transport) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (license == null) {
            if (other.license != null)
                return false;
        } else if (!license.equals(other.license))
            return false;
        if (Double.doubleToLongBits(capacity) != Double.doubleToLongBits(other.capacity))
            return false;
        return true;
    }
    
}
