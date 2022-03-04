package service;

import model.Writer;

public interface WriterService extends GeneralService<Writer, Long> {

    int save(Writer writer);
}
