import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.FileExtractor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Парсинг файлов")
public class FileParsingTest extends TestData {
    FileExtractor fileExtractor = new FileExtractor();
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Распаковка архива и парсинг csv файла")
    void parseCsvFileTest() throws Exception {

        byte[] fileContent = fileExtractor.extractZipFile(archiveName, csvFile);
        CSVReader csvReader = new CSVReader(new InputStreamReader(
                new ByteArrayInputStream(fileContent)));

        assertThat(csvReader.readAll().get(0)).isEqualTo(
                new String[]{csvFirstRow});
    }

    @Test
    @DisplayName("Распаковка архива и парсинг pdf файла")
    void parsePdfFileTest() throws Exception {

        byte[] fileContent = fileExtractor.extractZipFile(archiveName, pdfFile);
        PDF pdf = new PDF(fileContent);

        assertThat(pdf.creator).isEqualTo(creator);
        assertThat(pdf.numberOfPages).isEqualTo(pageCount);
    }

    @Test
    @DisplayName("Распаковка архива и парсинг xls файла")
    void parseXlsFileTest() throws Exception {

        byte[] fileContent = fileExtractor.extractZipFile(archiveName, xlsFile);
        XLS xls = new XLS(fileContent);

        String firstName = xls.excel.getSheetAt(0).getRow(2)
                .getCell(1).getStringCellValue();
        String lastName = xls.excel.getSheetAt(0).getRow(2)
                .getCell(2).getStringCellValue();
        String date = String.valueOf(xls.excel.getSheetAt(0).getRow(2)
                .getCell(6).getLocalDateTimeCellValue());

        assertThat(firstName).isEqualTo(userName);
        assertThat(lastName).isEqualTo(userSecondName);
        assertThat(date).isEqualTo(dateRegistration);
    }

    @Test
    @DisplayName("Парсинг json файла")
    void parseJsonTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("user.json")) {
            User user = objectMapper.readValue(is, User.class);

            assertThat(user.getEmail()).isEqualTo("igafarov@dtln.ru");
            assertThat(user.getFullName()).isEqualTo("Гафаров Ильгиз");
            assertThat(user.getLogin()).isEqualTo("igafarov");
            assertThat(user.getSupAcl().getRoles().get(0)).isEqualTo("admin");
            assertThat(user.getSupAcl().getRoles().get(1)).isEqualTo("operator");
        }
    }
}



