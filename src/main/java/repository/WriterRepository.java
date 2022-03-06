package repository;

import model.Writer;


public interface WriterRepository extends GeneralRepository<Writer, Long> {

    int save(Writer writer);
}
