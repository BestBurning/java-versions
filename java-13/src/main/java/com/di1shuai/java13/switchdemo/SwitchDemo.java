package com.di1shuai.java13.switchdemo;

/**
 * @author Shea
 * @date 2021-01-22
 * @description Switch 增强 (预览)
 * case 多个 -> xxx;
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
        TODAY toDay = whatIsTodayBefore13(day);
        System.out.println(toDay);

        toDay = whatIsToday13(day);
        System.out.println(toDay);


    }

    private static TODAY whatIsTodayBefore13(DAY day) {
        // Before 13
        TODAY today;
        switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> today = TODAY.WORKING_DAY;
            case SATURDAY, SUNDAY -> today = TODAY.WEEKEND_DAY;
            default -> throw new IllegalArgumentException("Invalid day");
        }
        return today;
    }


    private static TODAY whatIsToday13(DAY day) {
        // 13
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                yield TODAY.WORKING_DAY;
            }
            case SATURDAY, SUNDAY -> {
                yield TODAY.WEEKEND_DAY;
            }
            default -> throw new IllegalArgumentException("Invalid day");
        };
    }

}
