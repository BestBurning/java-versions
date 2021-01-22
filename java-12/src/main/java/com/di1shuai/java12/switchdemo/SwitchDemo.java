package com.di1shuai.java12.switchdemo;

/**
 * @author Shea
 * @date 2021-01-22
 * @description Switch 增强 (预览)
 *  case 多个 -> xxx;
 *
 */
public class SwitchDemo {

    enum DAY {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    enum TODAY {
        WEEKEND_DAY,
        WORKING_DAY
    }

    public static void main(String[] args) {
        DAY day = DAY.MONDAY;
        TODAY toDay = whatIsTodayBefore12(day);
        System.out.println(toDay);

        toDay = whatIsToday12(day);
        System.out.println(toDay);


    }

    private static TODAY whatIsTodayBefore12(DAY day) {
        // Before 12
        TODAY today;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                today = TODAY.WORKING_DAY;
                break;
            case SATURDAY:
            case SUNDAY:
                today = TODAY.WEEKEND_DAY;
                break;
            default:
                throw new RuntimeException("N/A");
        }
        return today;
    }


    private static TODAY whatIsToday12(DAY day) {
        // 12
        TODAY today;
        switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> today = TODAY.WORKING_DAY;
            case SATURDAY, SUNDAY -> today = TODAY.WEEKEND_DAY;
            default -> throw new IllegalArgumentException("Invalid day");
        }
        return today;
    }

}
