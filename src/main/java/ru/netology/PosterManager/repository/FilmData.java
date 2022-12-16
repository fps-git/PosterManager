package ru.netology.PosterManager.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmData {
    int id;
    String name;
    String genre;
    String imageLink;
    boolean premiereTomorrow;
}
