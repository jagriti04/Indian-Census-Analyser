package censusanalyser;

import com.bl.csvbuilder.ICSVBuilder;
import com.bl.csvbuilder.CSVBuilderFactory;
import com.bl.csvbuilder.CSVException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCodeCSV> stateCodeCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CSVException(e.getMessage(), CSVException.ExceptionType.WRONG_CENSUS_CSV_FILE);
        }
    }

    // UC 2 - method for Indian state code file
    public int loadIndiaStateCode(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodeCSVList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
            return stateCodeCSVList.size();
        } catch (IOException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CSVException(e.getMessage(), CSVException.ExceptionType.WRONG_STATE_CODE_FILE);
        }
    }

    private <E> int getEntryCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int countEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return countEntries;
    }

    public int loadIndiaStateCodeUsingCommon(String csvFilePath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            return this.getEntryCount(stateCodeCSVIterator);
        } catch (IOException e) {
            throw new CSVException(e.getMessage(),
                    CSVException.ExceptionType.STATE_CODE_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CSVException(e.getMessage(), CSVException.ExceptionType.WRONG_STATE_CODE_FILE);
        }
    }


}
