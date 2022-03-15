package service.Impl;


import model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Impl.LabelRepoImpl;
import service.LabelService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LabelServiceImplTest {

    private LabelRepoImpl labelRepository;

    private LabelService labelService;

    @BeforeEach
    void setLabelService() {
        labelRepository = mock(LabelRepoImpl.class);
        labelService = new LabelServiceImpl(labelRepository);
    }

    @Test
    void getByIdWithCorrectDataTest() {
        Label label = new Label(1L, "name");

        when(labelRepository.getById(any(Long.class))).thenReturn(label);

        assertEquals(label, labelService.getById(1L));
    }

    @Test
    void getByIdThrowNPEIfLabelNotExistTest() {
        Label label = new Label(1L, "name");

        when(labelRepository.getById(1L)).thenReturn(label);

        assertThrows(NullPointerException.class, () -> labelService.getById(2L));
    }

    @Test
    void getAllLabelsTest() {
        List<Label> labels = new ArrayList<>(List.of(
                new Label(1L, "name1"),
                new Label(2L, "name2")));

        when(labelRepository.getAll()).thenReturn(labels);
        List<Label> labelServiceAll = labelService.getAll();

        assertEquals(2, labelServiceAll.size());
        verify(labelRepository, times(1)).getAll();
    }

    @Test
    void saveWithCorrectDataReturn1Test() {
        when(labelRepository.save(any(Label.class))).thenReturn(1);

        assertEquals(1, labelService.save(new Label( "name")));
    }

    @Test
    void saveThrowNPEIfIdIsNullTest() {
        Label label = new Label();

        assertThrows(NullPointerException.class, () -> labelService.save(label));
    }

    @Test
    void updateWithCorrectDateReturn1Test() {
        Label newLabel = new Label("new Name");

        when(labelRepository.update(eq(newLabel))).thenReturn(1);

        assertEquals(1, labelService.update(newLabel));
    }

    @Test
    void updateThrowNPEIfNewNameIsNull() {
        Label newLabel = new Label();
        when(labelRepository.update(eq(newLabel))).thenReturn(1);

        assertThrows(NullPointerException.class, () -> labelService.update(newLabel));
    }

    @Test
    void deleteSuccessIfLabelExist() {
        Label label = new Label(1L, "name");

        when(labelRepository.delete(label.getId())).thenReturn(1);

        assertEquals(1, labelService.delete(1L));
    }

    @Test
    void deleteThrowNPEIfLabelNotExitsTest() {
        Label label = new Label(1L, "name");

        when(labelRepository.delete(label.getId())).thenReturn(1);

        assertThrows(NullPointerException.class, () ->labelService.delete(2L));
    }
}