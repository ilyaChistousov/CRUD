package service.Impl;

import model.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Impl.WriterRepoImpl;
import service.WriterService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WriterServiceImplTest {

    private WriterRepoImpl writerRepository;
    private WriterService writerService;

    @BeforeEach
    void setService() {
        writerRepository = mock(WriterRepoImpl.class);
        writerService = new WriterServiceImpl(writerRepository);
    }

    @Test
    void getByIdWithCorrectDataTest() {
        Writer writer = new Writer();
        writer.setId(1L);
        writer.setFirstName("first name");
        writer.setLastName("last name");

        when(writerRepository.getById(eq(1L))).thenReturn(writer);

        assertEquals(writer, writerService.getById(1L));
    }

    @Test
    void getByIdThrowNPEIfWriterNotExistTest() {
        Writer writer = new Writer();
        writer.setId(1L);
        writer.setFirstName("first name");
        writer.setLastName("last name");

        when(writerRepository.getById(eq(1L))).thenReturn(writer);

        assertThrows(NullPointerException.class, () -> writerService.getById(2L));
    }

    @Test
    void getAllTest() {
        List<Writer> writers = new ArrayList<>(List.of(new Writer(), new Writer()));

        when(writerRepository.getAll()).thenReturn(writers);

        assertEquals(2, writerService.getAll().size());
    }

    @Test
    void saveWithCorrectDataReturn1Test() {
        Writer writer = new Writer();
        writer.setId(1L);
        writer.setFirstName("first");
        writer.setLastName("last");

        when(writerRepository.save(eq(writer))).thenReturn(1);

        assertEquals(1, writerService.save(writer));
    }

    @Test
    void saveThrowNPEWhenFirstNameOrLastNameIsNullTest() {
        Writer writer = new Writer();
        writer.setFirstName("first");
        when(writerRepository.save(eq(writer))).thenReturn(1);

        assertThrows(NullPointerException.class, () -> writerService.save(writer));
    }

    @Test
    void updateWithCorrectDataReturn1Test() {
        Writer newWriter = new Writer();
        newWriter.setFirstName("new First name");
        newWriter.setLastName("new Last name");

        when(writerRepository.update(eq(newWriter))).thenReturn(1);

        assertEquals(1, writerService.update(newWriter));
    }

    @Test
    void updateThrowNPEIfNewContentIsNullTest() {
        Writer writer = new Writer();

        when(writerRepository.update(eq(writer))).thenReturn(1);

        assertThrows(NullPointerException.class, () -> writerService.update(writer));
    }

    @Test
    void deleteSuccessIfPostExistTest() {
        Writer deletedWriter = new Writer();
        deletedWriter.setId(1L);

        when(writerRepository.delete(1L)).thenReturn(1);

        assertEquals(1, writerService.delete(1L));
    }

    @Test
    void deleteThrowNPEIfPostNotExist() {
        Writer deletedWriter = new Writer();
        deletedWriter.setId(1L);

        when(writerRepository.delete(1L)).thenReturn(1);

        assertThrows(NullPointerException.class, () -> writerService.delete(2L));
    }
}