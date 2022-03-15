package service;

import model.Label;

public interface LabelService extends GeneralService <Label, Long> {

    int save(Label label);
}
