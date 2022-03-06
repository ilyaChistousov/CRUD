package repository;

import model.Label;


public interface LabelRepository extends GeneralRepository<Label, Long> {

    int save(Label label, Long postId);
}
