package censusanalyser;

import com.bl.csvbuilder.CSVException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INDIA_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusWrong.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCensusWrongHeader.csv";

    // UC2 files path
    private static final String INDIA_STATE_CODE_CSV_FILE = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_TYPE = "./src/test/resources/IndiaStateCode.txt";
    private static final String INDIA_STATE_CODE_WRONG_DELIMITER = "/src/test/resources/IndiaStateCodeWrongDelimiter.csv";
    private static final String INDIA_STATE_CODE_WRONG_HEADER = "/src/test/resources/IndiaStateCodeWrongHeader";

    // TC 1.1
    @Test
    public void givenIndianCensusCSVFile_returnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CSVException e) {
            e.printStackTrace();
        }
    }

    // TC 1.2
    @Test
    public void givenIndiaCensusData_withWrongFile_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    // TC 1.3
    @Test
    public void givenIndiaCensusData_withWrongFileType_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    // TC 1.4
    @Test
    public void givenIndiaCensusData_withWrongDelimiterFile_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_DELIMITER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.WRONG_CENSUS_CSV_FILE, e.type);
        }
    }

    // TC 1.5 (Wrong header)
    @Test
    public void givenIndiaCensusData_withWrongHeader_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_HEADER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.WRONG_CENSUS_CSV_FILE, e.type);
        }
    }

    // UC2 TC 1.1
    @Test
    public void givenIndianStateCodeCSVFile_shouldReturnCorrectRecordsCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE);
            Assert.assertEquals(37, numOfRecords);
        } catch (CSVException e) {
            e.printStackTrace();
        }
    }

    // UC2 TC 1.2
    @Test
    public void givenIndiaStateCodeData_withWrongFile_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaStateCode(WRONG_STATE_CODE_FILE_PATH);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    // UC2 TC 1.3
    @Test
    public void givenIndiaStateCodeData_withWrongFileType_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaStateCode(WRONG_STATE_CODE_FILE_TYPE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    // TC 1.4
    @Test
    public void givenIndiaStateCodeData_withWrongDelimiterFile_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_WRONG_DELIMITER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    // TC 1.5 (Wrong header)
    @Test
    public void givenIndiaStateCode_withWrongHeader_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_WRONG_HEADER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_usingCommonCSV_shouldReturnCorrectRecordsCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateCodeUsingCommon(INDIA_STATE_CODE_CSV_FILE);
            Assert.assertEquals(37, numOfRecords);
        } catch (CSVException e) {
            e.printStackTrace();
        }
    }

    //UC3 State Alphabetical order
    @Test
    public void givenIndianCensusData_whenSortedOnState_shouldReturnSortedResult() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
    }

    //UC4 State code sorting
    @Test
    public void givenIndianStateCodeData_whenSortedOnStateCode_shouldReturnSortedResult() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE);
        String sortedStateCodeData = censusAnalyser.getStateCodeWiseSortedStateCodeData();
        IndiaStateCodeCSV[] stateCodeCSV = new Gson().fromJson(sortedStateCodeData, IndiaStateCodeCSV[].class);
        Assert.assertEquals("AD", stateCodeCSV[0].stateCode);
    }

    //UC5 Sorting from most populous state to the least on
    // check for most populous state
    @Test
    public void givenIndianCensusData_whenSortedOnPopulation_shouldReturnSortedResult() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
    }

    // check for least populous state
    @Test
    public void givenIndianCensusData_whenSortedOnPopulation_shouldReturnSortedResult_checkLast() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Sikkim", censusCSV[28].state);
    }

    //UC6 Sorting from most population density state to the least one
    // check for most density population state
    @Test
    public void givenIndianCensusData_whenSortedOnPopulationDensity_shouldReturnSortedResult_checkFirst() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Bihar", censusCSV[0].state);
    }

    // check for least density population state
    @Test
    public void givenIndianCensusData_whenSortedOnPopulationDensity_shouldReturnSortedResult_checkLast() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Arunachal Pradesh", censusCSV[28].state);
    }

    //UC7 Sorting from Largest State by Area to the smallest state
    // check for Largest State by Area
    @Test
    public void givenIndianCensusData_whenSortedOnArea_shouldReturnSortedResult_checkFirst() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Rajasthan", censusCSV[0].state);
    }

    // check for Smallest State by Area
    @Test
    public void givenIndianCensusData_whenSortedOnArea_shouldReturnSortedResult_checkLast() throws CSVException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
        IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Goa", censusCSV[28].state);
    }
}
