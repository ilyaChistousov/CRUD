package controller;

import model.Writer;
import repository.Impl.WriterRepoImpl;
import service.Impl.WriterServiceImpl;

import java.util.List;

public class WriterController {

    private final WriterServiceImpl writerService = new WriterServiceImpl(new WriterRepoImpl());

    public void addNewWriter(String firstName, String lastName) {
        Writer newWriter = new Writer();
        newWriter.setFirstName(firstName);
        newWriter.setLastName(lastName);
        writerService.save(newWriter);
    }

    public Writer getWriter(Long id) {
        return writerService.getById(id);
    }

    public List<Writer> getAllWriters() {
        return writerService.getAll();
    }

    public void updateWriter(String newFirstName, String newLastName, Long id) {
        Writer updatedWriter = new Writer();
        updatedWriter.setId(id);
        updatedWriter.setFirstName(newFirstName);
        updatedWriter.setLastName(newLastName);
        writerService.update(updatedWriter);
    }

    public void deleteWriter(Long id) {
        writerService.delete(id);
    }
}
