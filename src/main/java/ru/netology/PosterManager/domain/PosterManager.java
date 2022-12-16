package ru.netology.PosterManager.domain;

import ru.netology.PosterManager.repository.FilmData;
import ru.netology.PosterManager.repository.FilmsRepository;

public class PosterManager {
    private FilmsRepository repo;

    public PosterManager(FilmsRepository repo) {
        this.repo = repo;
    }

    public FilmData[] findAll() {
        return repo.findAll();
    }

    public void addNewFilm(FilmData film) {
        repo.save(film);
    }

    public FilmData findById(int id) {
        return repo.findById(id);
    }

    /*
    В задании указано "если объекта нет, то пусть будет исключение, как на лекции", а в лекции такого случая
    не рассмотрено. Поэтому в методе removeById в случае если id не найдено, я выявлю это
    до вызова метода удаления и не буду ничего делать с массивом, соответственно оставлю его такой же длины,
    как он был в начале, чтобы не было exception'а
     */
    public void removeById(int id) {
        boolean find = false;
        for (FilmData film : findAll()) {
            if (film.getId() == id) {
                find = true;
                break;
            }
        }
        if (find) {
            repo.removeById(id);
        }
    }

    /*
    Не совсем понял, что имелось ввиду под "пустым массивом" в задании в фразе "для удаления всех элементов
    достаточно в поле items положить пустой массив". Либо это массив нулевой длины, либо этот массив той же длины, что
    и было до удаления всех данных, но с пустыми ячейчками. Поэтому я сделал два варианта метода removeAll,
    первый removeAll1 присваивает массив нулевой длины, а второй removeAll2 присвает всем ячейкам массива значение null
     */
    public void removeAll1() {
        repo.removeAll1();
    }

    public void removeAll2() {
        repo.removeAll2();
    }

    /*
    В задании сказано, что новых функций по сравнению с предыдущим заданием добавлять не нужно, про удалени функций
    тоже ничего не сказано, поэтому и старую функцию findLast я оставил, адаптировав под работу с репозиторием.
     */
    public FilmData[] findLast(int lastFilmsLimit) {
        int resultLength;
        if (lastFilmsLimit < 1) {
            lastFilmsLimit = 10;
        }
        if (lastFilmsLimit > findAll().length) {
            resultLength = findAll().length;
        } else {
            resultLength = lastFilmsLimit;
        }

        FilmData[] last = new FilmData[resultLength];
        for (int i = 0; i < resultLength; i++) {
            last[i] = findAll()[findAll().length - 1 - i];
        }
        return last;
    }
}
