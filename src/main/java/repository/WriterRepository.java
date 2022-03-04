package repository;

import model.Writer;

import java.util.List;

public interface WriterRepository extends GeneralRepository<Writer, Long> {

    int save(Writer writer);
}
