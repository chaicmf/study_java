package com.cmf.design.constants;

public enum Day  implements ProductP{
    MONDAY,TUESDAY,WENDESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;

    @Override
    public void getO() {
        System.out.println("12211");
    }
    public    void getDay(Day day){
        switch (day){
            case MONDAY:
                System.out.println("MONDAY"); break;
            case TUESDAY:
                System.out.println("TUESDAY");  break;
            case WENDESDAY:
                System.out.println("WENDESDAY");  break;
            case THURSDAY:
                System.out.println("THURSDAY");  break;
            case FRIDAY:
                System.out.println("FRIDAY");  break;
            case SATURDAY:
                System.out.println("SATURDAY");  break;
            case SUNDAY:
                System.out.println("SUNDAY");  break;
        }

    }
}
