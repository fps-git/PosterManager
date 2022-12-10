package ru.netology.PosterManager.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.PosterManager.repository.FilmData;
import ru.netology.PosterManager.repository.FilmsRepository;

class PosterManagerTest {
    FilmsRepository repo = new FilmsRepository();
    PosterManager manager = new PosterManager(repo);
    FilmData film1 = new FilmData(1, "Бладшот", "боевик", "https://upload.wikimedia.org/wikipedia/ru/9/9d/Bloodshot_poster.jpg", false);
    FilmData film2 = new FilmData(2, "Вперёд", "мультфильм", "https://upload.wikimedia.org/wikipedia/ru/1/10/Постер_мультфильма_«Вперёд».jpg", false);
    FilmData film3 = new FilmData(3, "Отель «Белград»", "комедия", "https://upload.wikimedia.org/wikipedia/ru/thumb/3/32/Отель_«Белград».jpg/1200px-Отель_«Белград».jpg", false);
    FilmData film4 = new FilmData(4, "Джентельмены", "боевик", "https://upload.wikimedia.org/wikipedia/ru/c/c1/Джентльмены.jpg", false);
    FilmData film5 = new FilmData(5, "Человек-невидимка", "ужасы", "https://s2.afisha.ru/mediastorage/c2/97/0e6842322934440cbfa8927297c2.jpg", false);
    FilmData film6 = new FilmData(6, "Тролли. Мировой тур", "мультфильм", "https://static.kinoafisha.info/k/movie_posters/canvas/800x1200/upload/movie_posters/0/3/9/8329930/3d4b4d2f021f984bcd02f0435172965b.jpeg", true);
    FilmData film7 = new FilmData(7, "Номер один", "комедия", "https://s.1rnd.ru/section/afisha_event/upload/pers/116/img/afisha/000/000/037/200215003034678_5f204ccc1601e.jpg", true);

    @BeforeEach
    public void setup() {
        manager.addNewFilm(film1);
        manager.addNewFilm(film2);
        manager.addNewFilm(film3);
        manager.addNewFilm(film4);
        manager.addNewFilm(film5);
        manager.addNewFilm(film6);
        manager.addNewFilm(film7);
    }

    @Test
    public void shouldShowAll() {

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNewFilm() {
        FilmData film8 = new FilmData(8, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);
        manager.addNewFilm(film8);

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7, film8};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {

        FilmData expected = film7;
        FilmData actual = manager.findById(7);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullIfBadId() {

        FilmData expected = null;
        FilmData actual = manager.findById(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByValidId() {
        manager.removeById(3);

        FilmData[] expected = {film1, film2, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByInvalidId() {
        manager.removeById(56);

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllFirstOption() {
        manager.removeAll1();

        FilmData[] expected = new FilmData[0];
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllSecondOption() {
        manager.removeAll2();
        FilmData[] emptyArray = new FilmData[repo.findAll().length];

        FilmData[] expected = emptyArray;
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSetDefault10IfBadLastFilmsLimit() {
        FilmData film8 = new FilmData(8, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);
        FilmData film9 = new FilmData(9, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);
        FilmData film10 = new FilmData(10, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);
        FilmData film11 = new FilmData(11, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);
        FilmData film12 = new FilmData(12, "Елки-9", "комедия", "https://img04.rl0.ru/afisha/e920x1380q65i/s5.afisha.ru/mediastorage/c5/1e/907d96ee4dfc45ed814e3f661ec5.jpeg",true);

        manager.addNewFilm(film8);
        manager.addNewFilm(film9);
        manager.addNewFilm(film10);
        manager.addNewFilm(film11);
        manager.addNewFilm(film12);

        FilmData[] expected = {film12, film11, film10, film9, film8, film7, film6, film5, film4, film3};
        FilmData[] actual = manager.findLast(0);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowOnlyOneLast() {

        FilmData[] expected = {film7};
        FilmData[] actual = manager.findLast(1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfFilmsQuantityBelowLimit() {

        FilmData[] expected = {film7, film6, film5, film4, film3, film2, film1};
        FilmData[] actual = manager.findLast(8);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfFilmsQuantityEqualsLimit() {

        FilmData[] expected = {film7, film6, film5, film4, film3, film2, film1};
        FilmData[] actual = manager.findLast(7);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfFilmsQuantityAboveLimit() {

        FilmData[] expected = {film7, film6, film5, film4};
        FilmData[] actual = manager.findLast(4);

        Assertions.assertArrayEquals(expected, actual);
    }

}
