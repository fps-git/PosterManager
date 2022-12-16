package ru.netology.PosterManager.repository;


public class FilmsRepository {

    FilmData[] filmsList = new FilmData[0];

    public FilmData[] findAll() {
        return filmsList;
    }

    public void save(FilmData newFilm) {
        FilmData[] tmp = new FilmData[filmsList.length + 1];
        for (int i = 0; i < filmsList.length; i++) {
            tmp[i] = filmsList[i];
        }
        tmp[tmp.length - 1] = newFilm;
        filmsList = tmp;
    }

    public FilmData findById(int id) {
        for (FilmData film : findAll()) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

    public void removeById(int id) {

        FilmData[] tmp = new FilmData[filmsList.length - 1];
        int copyToIndex = 0;
        for (FilmData film : findAll()) {
            if (film.getId() != id) {
                tmp[copyToIndex] = film;
                copyToIndex++;
            }
        }
        filmsList = tmp;
    }

    /*
    Не совсем понял, что имелось ввиду под "пустым массивом" в задании в фразе "для удаления всех элементов
    достаточно в поле items положить пустой массив". Либо это массив нулевой длины, либо этот массив той же длины, что
    и было до удаления всех данных, но с пустыми ячейчками. Поэтому я сделал два варианта метода removeAll,
    первый removeAll1 присваивает массив нулевой длины, а второй removeAll2 присвает всем ячейкам массива значение null
     */
    public void removeAll1() {
        filmsList = new FilmData[0];
    }

    public void removeAll2() {
        for (int i = 0; i < filmsList.length; i++) {
            filmsList[i] = null;
        }
    }
}
