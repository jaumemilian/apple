package com.asg.services.apple.pojos;

import java.util.Objects;

public class AppleCreateRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppleCreateRequest that = (AppleCreateRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AppleCreateRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
