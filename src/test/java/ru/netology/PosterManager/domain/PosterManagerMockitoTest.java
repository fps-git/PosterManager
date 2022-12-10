package ru.netology.PosterManager.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.PosterManager.repository.FilmData;
import ru.netology.PosterManager.repository.FilmsRepository;

import static org.mockito.Mockito.*;

class PosterManagerMockitoTest {
    FilmsRepository repo = Mockito.mock(FilmsRepository.class);
    PosterManager manager = new PosterManager(repo);
    FilmData film1 = new FilmData(1, "Бладшот", "боевик", "https://upload.wikimedia.org/wikipedia/ru/9/9d/Bloodshot_poster.jpg", false);
    FilmData film2 = new FilmData(2, "Вперёд", "мультфильм", "https://upload.wikimedia.org/wikipedia/ru/1/10/Постер_мультфильма_«Вперёд».jpg", false);
    FilmData film3 = new FilmData(3, "Отель «Белград»", "комедия", "https://upload.wikimedia.org/wikipedia/ru/thumb/3/32/Отель_«Белград».jpg/1200px-Отель_«Белград».jpg", false);
    FilmData film4 = new FilmData(4, "Джентельмены", "боевик", "https://upload.wikimedia.org/wikipedia/ru/c/c1/Джентльмены.jpg", false);
    FilmData film5 = new FilmData(5, "Человек-невидимка", "ужасы", "https://s2.afisha.ru/mediastorage/c2/97/0e6842322934440cbfa8927297c2.jpg", false);
    FilmData film6 = new FilmData(6, "Тролли. Мировой тур", "мультфильм", "https://static.kinoafisha.info/k/movie_posters/canvas/800x1200/upload/movie_posters/0/3/9/8329930/3d4b4d2f021f984bcd02f0435172965b.jpeg", true);
    FilmData film7 = new FilmData(7, "Номер один", "комедия", "https://s.1rnd.ru/section/afisha_event/upload/pers/116/img/afisha/000/000/037/200215003034678_5f204ccc1601e.jpg", true);

    @Test
    public void shouldShowAll() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByValidId() {
        FilmData[] filmsList = {film1, film2, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        manager.removeById(3);

        FilmData[] expected = {film1, film2, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByInvalidId() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        manager.removeById(0);

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7};
        FilmData[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowOnlyOneLast() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        FilmData[] expected = {film7};
        FilmData[] actual = manager.findLast(1);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfFilmsQuantityBelowLimit() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        FilmData[] expected = {film7, film6, film5, film4, film3, film2, film1};
        FilmData[] actual = manager.findLast(8);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowLastIfFilmsQuantityAboveLimit() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7};
        doReturn(filmsList).when(repo).findAll();

        FilmData[] expected = {film7, film6, film5, film4};
        FilmData[] actual = manager.findLast(4);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSetDefault10WhenInvalidLimit() {
        FilmData[] filmsList = {film1, film2, film3, film4, film5, film6, film7, film6, film5, film4, film3, film2, film1};
        doReturn(filmsList).when(repo).findAll();

        FilmData[] expected = {film1, film2, film3, film4, film5, film6, film7, film6, film5, film4};
        FilmData[] actual = manager.findLast(0);

        Assertions.assertArrayEquals(expected, actual);
    }

}
