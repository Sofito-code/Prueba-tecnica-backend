package co.edu.udea.lis.pruebat.web.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationData {

    public static boolean isNotValidDateTime(String dateTimeStr) {
        try {
            OffsetDateTime.parse(dateTimeStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isBigDecimal(String decimalStr) {
        try{
            new BigDecimal(decimalStr);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


    public static boolean isNotPositiveBigDecimal(BigDecimal value) {
        return !(value.compareTo(BigDecimal.ZERO) > 0);
    }

    public static boolean isNull(BigDecimal value){
        return value == null;
    }

    public static boolean isEmptyString(String value){
        return value == null || value.isEmpty();
    }

    public static boolean isValidDate(String date) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}