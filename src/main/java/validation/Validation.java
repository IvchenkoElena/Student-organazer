package validation;

public final class Validation {
    private static final int MIN_GROUP_NUMBER = 100;
    private static final int MAX_GROUP_NUMBER = 999;
    private static final double MIN_AVERAGE_GRADE = 2.0;
    private static final double MAX_AVERAGE_GRADE = 5.0;
    private Validation(){

    }

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
        //проверяет первые две большие буквы и последующие 6 цифры
        return recordBookNumber.matches("[A-Z]{2}\\d{6}");
    }
}
