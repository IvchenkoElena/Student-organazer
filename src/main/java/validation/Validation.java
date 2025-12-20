package validation;

public class Validation {
    private static final int MIN_GROUP_NUMBER = 100;
    private static final int MAX_GROUP_NUMBER = 999;
    private static final double MIN_AVERAGE_GRADE = 2.0;
    private static final double MAX_AVERAGE_GRADE = 5.0;
    private static final int FIRST_SYMBOL = 0;
    private static final int SECOND_SYMBOL = 1;
    private static final int LENGTH_RECORD_BOOK_NUMBER = 8;
    private static final int FIRST_UPPER_SYMBOL_UNICODE = 65;
    private static final int LAST_UPPER_SYMBOL_UNICODE = 90;

    public static boolean isValidInputData(int groupNumber, double averageGrade, String recordBookNumber){
        return  isValidGroupNumber(groupNumber) && isValidAverageGrade(averageGrade) && isValidRecordBookNumber(recordBookNumber);
    }
    private static boolean isValidGroupNumber(int groupNumber){
        return groupNumber >= MIN_GROUP_NUMBER && groupNumber <= MAX_GROUP_NUMBER;
    }
    private static boolean isValidAverageGrade(double averageGrade){
        return averageGrade >= MIN_AVERAGE_GRADE && averageGrade <= MAX_AVERAGE_GRADE;
    }
    private static boolean isValidRecordBookNumber(String recordBookNumber){
        if(recordBookNumber == null || recordBookNumber.isEmpty()){
            return false;
        }
        //можно этой регуляркой все заменить она проверяет первые две большие буквы и последующие 6 цифры
        boolean isDigitsFromTwoIndex = recordBookNumber.matches("[A-Z]{0,2}\\d{2,6}");
        boolean isUpperFirstSymBol = recordBookNumber.codePointAt(FIRST_SYMBOL) >= FIRST_UPPER_SYMBOL_UNICODE && recordBookNumber.codePointAt(FIRST_SYMBOL) <= LAST_UPPER_SYMBOL_UNICODE;
        boolean isUpperSecondSymBol = recordBookNumber.codePointAt(SECOND_SYMBOL) >= FIRST_UPPER_SYMBOL_UNICODE && recordBookNumber.codePointAt(SECOND_SYMBOL) <= LAST_UPPER_SYMBOL_UNICODE;
        return  isUpperFirstSymBol && isUpperSecondSymBol && isDigitsFromTwoIndex && recordBookNumber.length() == LENGTH_RECORD_BOOK_NUMBER;
    }
}
