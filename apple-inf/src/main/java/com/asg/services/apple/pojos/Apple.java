package com.asg.services.apple.pojos;

import java.util.Objects;

public class Apple {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return Objects.equals(name, apple.name) &&
                Objects.equals(id, apple.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
