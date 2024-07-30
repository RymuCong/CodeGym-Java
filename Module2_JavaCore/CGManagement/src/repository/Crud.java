package repository;

public interface Crud {
    void findAll();

    void delete();

    void edit();

    void search();

    void importFromCsv();

    void exportToCsv();
}
