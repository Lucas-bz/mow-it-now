package fr.bouzidi.mowitnow.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileStorageService.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FileStorageServiceTest {

    @Autowired
    FileStorageService fileStorageService;

    private String fileContent = "5 5\n" +
            "1 2 N\n" +
            "GAGAGAGAA";


    @Test
    @SneakyThrows
    void testFileContent() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", "file.txt",
                "text/plain", fileContent.getBytes());
        String[] lines = fileStorageService.getFileContent(mockMultipartFile);

        assertEquals(3, lines.length);
        assertTrue(new File("./upload/file.txt").exists());

    }
}