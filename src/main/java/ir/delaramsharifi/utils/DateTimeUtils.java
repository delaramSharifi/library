package ir.delaramsharifi.utils;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DateTimeUtils {
    public static final ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");
    public static final ULocale PERSIAN_EN_LOCALE = new ULocale("en@calendar=persian");
    public static final ZoneId IRAN_ZONE_ID = ZoneId.of("Asia/Tehran");

    public static Calendar fromDateToPersianCalendar(Date date) {
        Calendar persianCalendar = Calendar.getInstance(PERSIAN_LOCALE);
        persianCalendar.clear();
        persianCalendar.setTime(date);
        return persianCalendar;
    }

    /**
     * @param date
     * @param field example: Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, etc
     */
    public static int fromDateToPersianCalendarField(Date date, int field) {
        return fromDateToPersianCalendar(date).get(field);
    }

    public static String fromDateToPersianString(Date date) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, PERSIAN_LOCALE);
        return df.format(date);
    }

    public static String fromDateToPersianString(Date date, String pattern) {
        return new SimpleDateFormat(pattern, PERSIAN_LOCALE).format(date);
    }

    public static String getToDayAsPersianString() {
        String persianCharDate = new SimpleDateFormat("YYYY/MM/DD", PERSIAN_LOCALE).format(new Date());
         return getDbNum(persianCharDate);
    }

    public static String getTenDayAfterTodayAsPersianString() {
        String persianCharDate = new SimpleDateFormat("YYYY/MM/DD", PERSIAN_LOCALE).format(LocalDate.now().plusDays(10));
        return getDbNum(persianCharDate);
    }


    public static String fromDateToPersianString(Date date, String pattern, ULocale locale) {
        return new SimpleDateFormat(pattern, locale).format(date);
    }

    /**
     * @param month is zero based. (e.g. Farvardin = 0, Ordibehesht = 1, etc.)
     */
    public static Date fromPersianDateToDate(int year, int month, int day, int hour, int minutes, int seconds) {
        return new Date(fromPersianDate(year, month, day, hour, minutes, seconds));
    }

    /**
     * @param month is zero based. (e.g. Farvardin = 0, Ordibehesht = 1, etc.)
     */
    public static String fromPersianDateToPersianString(int year, int month, int day, int hour, int minutes, int seconds) {
        return fromDateToPersianString(fromPersianDateToDate(year, month, day, hour, minutes, seconds));
    }

    /**
     * @param month is zero based. (e.g. Farvardin = 0, Ordibehesht = 1, etc.)
     */
    public static LocalDateTime fromPersianDateToLocalDateTime(int year, int month, int day, int hour, int minutes, int seconds) {
        return fromPersianDateToZonedDateTime(year, month, day, hour, minutes, seconds).toLocalDateTime();
    }

    /**
     * @param month is zero based. (e.g. Farvardin = 0, Ordibehesht = 1, etc.)
     */
    public static ZonedDateTime fromPersianDateToZonedDateTime(int year, int month, int day, int hour, int minutes, int seconds) {
        return toZonedDateTime(fromPersianDate(year, month, day, hour, minutes, seconds));
    }

    /**
     * @param month is zero based. (e.g. Farvardin = 0, Ordibehesht = 1, etc.)
     */
    public static long fromPersianDate(int year, int month, int day, int hour, int minutes, int seconds) {
        Calendar persianCalendar = Calendar.getInstance(PERSIAN_LOCALE);
        persianCalendar.clear();
        persianCalendar.set(year, month, day, hour, minutes, seconds);
        return persianCalendar.getTimeInMillis();
    }

    public static ZonedDateTime toZonedDateTime(Long epochMilli) {
        if (epochMilli == null) return null;
        return Instant.ofEpochMilli(epochMilli).atZone(IRAN_ZONE_ID);
    }

    public static int[] persianDateSplitter(String persianDate) {

        if(persianDate==null)
            return null;

        List<String> expiryDateSpliced = Arrays.stream(persianDate.split("/")).collect(Collectors.toList());
        int[] splitedDate = {Integer.parseInt(expiryDateSpliced.get(0)),
                Integer.parseInt(expiryDateSpliced.get(1)) - 1,
                Integer.parseInt(expiryDateSpliced.get(2))
        };
        return splitedDate;
    }

    /**
     * convert persian Date to Java Date
     * @param persianDate
     * @return java.util.Date
     */
    public static Date persianDateToJavaDate(String persianDate){
        return fromPersianDateToDate(persianDateSplitter(persianDate)[0],
                persianDateSplitter(persianDate)[1],
                persianDateSplitter(persianDate)[2],
                23, 59, 59);
    }

    /**
     * if today is after persian date return true
     * @param persianDate
     * @return boolean
     */
    public static boolean isTodayAfterPersianDate(String persianDate){
        return new Date().after(persianDateToJavaDate(persianDate));
    }

    private static String getDbNum(String inputString) {
        String dbNum = inputString
                .replaceAll("\u06F0", "0")         //0
                .replaceAll("\u06F1", "1")         //1
                .replaceAll("\u06F2", "2")         //2
                .replaceAll("\u06F3", "3")         //3
                .replaceAll("\u06F4", "4")         //4
                .replaceAll("\u06F5", "5")         //5
                .replaceAll("\u06F6", "6")         //6
                .replaceAll("\u06F7", "7")         //7
                .replaceAll("\u06F8", "8")         //8
                .replaceAll("\u06F9", "9");        //9
        return dbNum;
    }

}
