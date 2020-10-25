package censusanalyser;

import com.bl.csvbuilder.ICSVBuilder;
import com.bl.csvbuilder.CSVBuilderFactory;
import com.bl.csvbuilder.CSVException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
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

    // this function uses commons csv library
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

    public String getStateWiseSortedCensusData() throws CSVException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CSVException("No Census Data", CSVException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing((census -> census.state));
        this.sort(censusComparator, censusCSVList);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedStateCodeData() throws CSVException {
        if (stateCodeCSVList == null || stateCodeCSVList.size() == 0) {
            throw new CSVException("No state code Data", CSVException.ExceptionType.NO_STATE_CODE_DATA);
        }
        Comparator<IndiaStateCodeCSV> stateCodeComparator = Comparator.comparing((stateCode -> stateCode.stateCode));
        this.sort(stateCodeComparator, stateCodeCSVList);
        String sortedStateCodeJson = new Gson().toJson(stateCodeCSVList);
        return sortedStateCodeJson;
    }

    private <E> void sort(Comparator<E> comparator, List<E> list) {
        for (int i = 0; i<list.size() - 1; i++) {
            for (int j=0; j<list.size()-i-1; j++) {
                E census1 = list.get(j);
                E census2 = list.get(j+1);
                if (comparator.compare(census1, census2) >0) {
                    list.set(j, census2);
                    list.set(j+1, census1);
                }
            }
        }
    }


}
