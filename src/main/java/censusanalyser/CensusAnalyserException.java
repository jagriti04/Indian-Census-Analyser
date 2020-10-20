package censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, WRONG_CENSUS_CSV_FILE,
        STATE_CODE_FILE_PROBLEM, WRONG_STATE_CODE_FILE,
        UNABLE_TO_PARSE;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
