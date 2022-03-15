package controller;

import lombok.SneakyThrows;
import model.Label;
import repository.Impl.LabelRepoImpl;
import service.Impl.LabelServiceImpl;

import java.util.List;

public class LabelController {

    private final LabelServiceImpl service = new LabelServiceImpl(new LabelRepoImpl());

    public void addNewLabel(String name) {
        Label label = new Label();
        label.setName(name);
        service.save(label);
    }

    public Label getLabel(Long id) {
        return service.getById(id);
    }

    public List<Label> getAllLabels() {
        return service.getAll();
    }

    @SneakyThrows
    public void update(String newName, Long labelId) {
        Label newLabel = new Label(labelId, newName);
        service.update(newLabel);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
