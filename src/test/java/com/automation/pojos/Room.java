package com.automation.pojos;

public class Room {
    /*
     "id": 112,
        "name": "harvard",
        "description": "veritas",
        "capacity": 6,
        "withTV": true,
        "withWhiteBoard": false
     */

    private int id;
    private String name;
    private String description;
    private int capacity;
    private boolean withTV;
    private boolean withWhiteBoard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWithTV() {
        return withTV;
    }

    public void setWithTV(boolean withTV) {
        this.withTV = withTV;
    }

    public boolean isWithWhiteBoard() {
        return withWhiteBoard;
    }

    public void setWithWhiteBoard(boolean withWhiteBoard) {
        this.withWhiteBoard = withWhiteBoard;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", withTV=" + withTV +
                ", withWhiteBoard=" + withWhiteBoard +
                '}';
    }
}
