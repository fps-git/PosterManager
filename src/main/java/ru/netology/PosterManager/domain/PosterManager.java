package ru.netology.PosterManager.domain;

public class PosterManager {

    FilmData[] filmsList = new FilmData[0];
    int lastFilmsLimit = 10;

    public PosterManager () {

    }

    public PosterManager (int lastFilmsLimit) {
    this.lastFilmsLimit = lastFilmsLimit;
    }

    public void addNewFilm (FilmData newFilm) {
        FilmData[] tmp = new FilmData[filmsList.length + 1];
        for (int i = 0; i < filmsList.length; i++) {
            tmp[i] = filmsList[i];
        }
        tmp[tmp.length - 1] = newFilm;
        filmsList = tmp;
    }

    public FilmData[] findAll () {
    return filmsList;
    }

    public FilmData[] findLast () {
        int resultLength;
        if (lastFilmsLimit > filmsList.length) {
            resultLength = filmsList.length;
        } else {
            resultLength = lastFilmsLimit;
        }
        if (resultLength < 1) {
            return null;
        }
        FilmData[] last = new FilmData[resultLength];
        for (int i = 0; i < resultLength; i++) {
            last[i] = filmsList[filmsList.length - 1 - i];
        }
        return last;
    }
}
