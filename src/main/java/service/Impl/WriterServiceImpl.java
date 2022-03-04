package service.Impl;

import lombok.SneakyThrows;
import model.Writer;
import repository.WriterRepository;
import service.WriterService;
import java.util.List;

public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepo;

    public WriterServiceImpl(WriterRepository writerRepo) {
        this.writerRepo = writerRepo;
    }

    @Override
    public Writer getById(Long id) {
        Writer writer = writerRepo.getById(id);
        if(writer == null) {
            throw new NullPointerException("Writer not exits");
        }
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        return writerRepo.getAll();
    }

    @Override
    public int save(Writer writer) {
        if(writer.getFirstName().isEmpty() | writer.getLastName().isEmpty()) {
            throw new NullPointerException("First name and Last name shouldn`t be empty");
        }
        return writerRepo.save(writer);
    }

    @SneakyThrows
    @Override
    public int update(Writer writer, Long id) {
        if(writer.getFirstName().isEmpty() | writer.getLastName().isEmpty()) {
            throw new NullPointerException("New First name and new Last name shouldn`t be empty");
        }
        return writerRepo.update(writer, id);
    }

    @Override
    public int delete(Long id) {
        int result = writerRepo.delete(id);
        if(result == 0) {
            throw new NullPointerException("Writer not exist");
        }
        return result;
    }
}
