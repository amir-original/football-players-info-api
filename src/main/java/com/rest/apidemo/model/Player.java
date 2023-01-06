package com.rest.apidemo.model;

import java.util.Objects;

public class Player {
    public String name;
    public Integer age;
    public String nationality;
    public String club;
    public String position;

    public Player() {
    }

    public Player(String name, Integer age, String nationality, String club, String position) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.club = club;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public String getClub() {
        return club;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(age, player.age) && Objects.equals(nationality, player.nationality) && Objects.equals(club, player.club) && Objects.equals(position, player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, nationality, club, position);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", club='" + club + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
