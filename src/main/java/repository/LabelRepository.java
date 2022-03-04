package repository;

import model.Label;

import java.util.List;

public interface LabelRepository extends GeneralRepository<Label, Long> {

    int save(Label label, Long postId);
}
