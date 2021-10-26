package com.gmail.gurik;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// ------SINGLETON------------
public class Logger {

    private static Logger logger;
    private static String logfile = "log.txt";
    private static FileWriter writer;
    private static Calendar calendar;

    public static synchronized Logger getLogger() {

        if (logger == null)
            logger = new Logger();

        if (writer == null) {
            try {
                writer = new FileWriter(logfile, true);
            } catch (IOException e) {
                System.out.println("Log error.");
            }
        }

        if (calendar == null)
            calendar = new GregorianCalendar();

        return logger;
    }

    // Контрокутор за замовчуванням
    private Logger() {}

    //Додання інформації в лог-файл
    public void add(String loginfo) {
        try {
            FileWriter writer = new FileWriter(logfile, true);

          //  updateDate();
            Calendar now = new GregorianCalendar();
            writer.write(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND));
            writer.write("\t- " + loginfo);

            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
        }
    }

    //Виведення лог-файлу
    public void show() {
        System.out.println(logfile);
    }
/*
    private boolean updateDate() {
        Calendar now = new GregorianCalendar();
        if (calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH) &&
            calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH) &&
            calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {

        }
    }*/
}
