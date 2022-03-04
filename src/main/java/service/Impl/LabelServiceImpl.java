package service.Impl;

import model.Label;
import repository.LabelRepository;
import service.LabelService;
import java.util.List;

public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepo;

    public LabelServiceImpl(LabelRepository labelRepo) {
        this.labelRepo = labelRepo;
    }

    @Override
    public Label getById(Long id) {
        Label label = labelRepo.getById(id);
        if(label == null) {
            throw new NullPointerException("Calling Label not exist");
        }
        return label;
    }

    @Override
    public List<Label> getAll() {
        return labelRepo.getAll();
    }

    @Override
    public int save(Label label, Long postId) {
        if(label.getName().isEmpty()) {
            throw new NullPointerException("Name should`t be empty");
        }
        return labelRepo.save(label, postId);
    }

    @Override
    public int update(Label newLabel, Long id){
        if(newLabel.getName().isEmpty()) {
            throw new NullPointerException("New name should`n be empty");
        }
        return labelRepo.update(newLabel, id);
    }

    @Override
    public int delete(Long id) {
        int result = labelRepo.delete(id);
        if(result == 0) {
            throw new NullPointerException("Label not exist");
        }
        return result;
    }
}
