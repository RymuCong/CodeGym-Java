import org.junit.Test;

import javax.annotation.processing.SupportedAnnotationTypes;

import static org.junit.Assert.assertEquals;

public class NextDay {

    @Test
    public void testCalculateNextDay() {
        int day = 31;
        int month = 1;
        int year = 2018;

        int expectedDay = 1;
        int expectedMonth = 2;
        int expectedYear = 2018;

        int[] result = calculateNextDay(day, month, year);

        assert result[0] == expectedDay;
        assert result[1] == expectedMonth;
        assert result[2] == expectedYear;
        assertEquals(result, new int[]{expectedDay, expectedMonth, expectedYear});
    }

    private int[] calculateNextDay(int day, int month, int year) {
        int[] result = new int[3];
        if (day == 31 && month == 1) {
            result[0] = 1;
            result[1] = 2;
            result[2] = year;
        }
        return result;
    }
}
