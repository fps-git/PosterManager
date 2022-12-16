package ru.netology.PosterManager.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PosterManagerTest {

    FilmData film1 = new FilmData(1, "Бладшот", "боевик", "https://upload.wikimedia.org/wikipedia/ru/9/9d/Bloodshot_poster.jpg", false);
    FilmData film2 = new FilmData(2, "Вперёд", "мультфильм", "https://upload.wikimedia.org/wikipedia/ru/1/10/Постер_мультфильма_«Вперёд».jpg", false);
    FilmData film3 = new FilmData(3, "Отель «Белград»", "комедия", "https://upload.wikimedia.org/wikipedia/ru/thumb/3/32/Отель_«Белград».jpg/1200px-Отель_«Белград».jpg", false);
    FilmData film4 = new FilmData(4, "Джентельмены", "боевик", "https://upload.wikimedia.org/wikipedia/ru/c/c1/Джентльмены.jpg", false);
    FilmData film5 = new FilmData(5, "Человек-невидимка", "ужасы", "https://s2.afisha.ru/mediastorage/c2/97/0e6842322934440cbfa8927297c2.jpg", false);
    FilmData film6 = new FilmData(6, "Тролли. Мировой тур", "мультфильм", "https://static.kinoafisha.info/k/movie_posters/canvas/800x1200/upload/movie_posters/0/3/9/8329930/3d4b4d2f021f984bcd02f0435172965b.jpeg", true);
    FilmData film7 = new FilmData(7, "Номер один", "комедия", "https://s.1rnd.ru/section/afisha_event/upload/pers/116/img/afisha/000/000/037/200215003034678_5f204ccc1601e.jpg", true);

    @Test
    public void shouldSaveAndShowAll() {
        PosterManager manager = new PosterManager();
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullIfZero() {
        PosterManager manager = new PosterManager(0);
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = null;
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowOnlyOneLast() {
        PosterManager manager = new PosterManager(1);
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = {film7};
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfUnderLimit() {
        PosterManager manager = new PosterManager(8);
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = {film7, film6, film5, film4, film3, film2, film1};
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfEqualsLimit() {
        PosterManager manager = new PosterManager(7);
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = {film7, film6, film5, film4, film3, film2, film1};
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfOverLimit() {
        PosterManager manager = new PosterManager(4);
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);

        FilmData[] expected = {film7, film6, film5, film4};
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastOverDefaultLimit10() {
        PosterManager manager = new PosterManager();
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);
        manager.addNewFilm(film7);
        manager.addNewFilm(film7);
        manager.addNewFilm(film7);
        manager.addNewFilm(film7);

        FilmData[] expected = {film7, film7, film7, film7, film7, film6, film5, film4, film3, film2};
        FilmData[] actual = manager.findLast();

        Assertions.assertArrayEquals(expected, actual);
    }

}