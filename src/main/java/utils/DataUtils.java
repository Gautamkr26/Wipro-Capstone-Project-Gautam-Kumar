package utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class DataUtils {
    public static Object[][] getExcelData(String path, String sheetName) throws Exception {
        try (FileInputStream fis = new FileInputStream(path); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = wb.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows(), cols = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rows - 1][cols];
            DataFormatter fmt = new DataFormatter();
            for (int i = 1; i < rows; i++) for (int j = 0; j < cols; j++) data[i - 1][j] = fmt.formatCellValue(sheet.getRow(i).getCell(j));
            return data;
        }
    }
    public static JsonNode readJson(String path) throws Exception { return new ObjectMapper().readTree(new File(path)); }
}