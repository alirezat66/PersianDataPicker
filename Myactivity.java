package com.example.persiandatepicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MyActivity extends Activity {
    public String strWeekDay = "";
    public String strMonth = "";


   Button btnyearupper;
    Button btnyearlower;
    Button btnmonthupper;
    Button btnmonthlower;
    Button btndayupper;
    Button btndaylower;
    EditText edtyear;
    EditText edtmonth;
    EditText edtday;
    int date;
    int month;
    int year;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         btnyearupper=(Button)findViewById(R.id.yearupper);
         btnyearlower=(Button)findViewById(R.id.yearlower);
        btnmonthupper=(Button)findViewById(R.id.mountupper);
        btnmonthlower=(Button)findViewById(R.id.mountlower);
        btndayupper=(Button)findViewById(R.id.dayupper);
        btndaylower=(Button)findViewById(R.id.daylower);
        edtyear=(EditText)findViewById(R.id.edtyear);
        edtmonth=(EditText)findViewById(R.id.edtmonth);
        edtday=(EditText)findViewById(R.id.edtday);
        Date d = new Date();
        d.getTime();
        calcSolarCalendar(d);
        edtyear.setText(year+"");
        edtmonth.setText(month+"");
        edtday.setText(date+"");
        btnyearupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = Integer.parseInt(edtyear.getText().toString());
                year++;
                if (year >1399) {
                    year = 1380;
                }

                edtyear.setText(year + "");
            }
        });
        btnyearlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 year=Integer.parseInt(edtyear.getText().toString());
                year--;
                if(year<1380)
                {
                    year=1399;
                }

                edtyear.setText(year+"");
            }
        });
        btnmonthupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 month=Integer.parseInt(edtmonth.getText().toString());
                month++;
                if(month>6 && date==31)
                {
                    date=30;
                    edtday.setText("30");
                }
                else  if(month==12 &&(date>29))
                {
                    date=29;
                    edtday.setText("29");
                }
                if(month>12)
                {
                    month=1;
                }
                edtmonth.setText(month+"");
            }
        });
        btnmonthlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 month=Integer.parseInt(edtmonth.getText().toString());
                month--;

                if(month<1)
                {
                    month=12;
                }
                edtmonth.setText(month+"");
                if(month>6 && month<13 && date==31)
                {
                    date=30;
                    edtday.setText("30");
                }
                if(month==12 && date>29)
                {
                    date=29;
                    edtday.setText("29");
                }
            }
        });
        btndayupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 date=Integer.parseInt(edtday.getText().toString());
                date++;
                if(date>31 |(month>6 &&date>30)|(month==12 && date>29))
                {
                 date=1;
                }
                edtday.setText(date+"");
            }
        });
        btndaylower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 date=Integer.parseInt(edtday.getText().toString());
                date--;
                  if(date<1 && month==12)
                {
                    date=29;
                }
               else if(date<1 && month>6)
                {
                    date=30;
                }
               else if(date<1)
                {
                    date=31;
                }
                edtday.setText(date+"");
            }
        });

    }

    private void calcSolarCalendar(Date MiladiDate) {

        int ld;

        int miladiYear = MiladiDate.getYear() + 1900;
        int miladiMonth = MiladiDate.getMonth() + 1;
        int miladiDate = MiladiDate.getDate();
        int WeekDay = MiladiDate.getDay();

        int[] buf1 = new int[12];
        int[] buf2 = new int[12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((miladiYear % 4) != 0) {
            date = buf1[miladiMonth - 1] + miladiDate;

            if (date > 79) {
                date = date - 79;
                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = date / 31;
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            } else {
                if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                date = date + ld;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }
        } else {
            date = buf2[miladiMonth - 1] + miladiDate;

            if (miladiYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (date > ld) {
                date = date - ld;

                if (date <= 186) {
                    switch (date % 31) {
                        case 0:
                            month = (date / 31);
                            date = 31;
                            break;
                        default:
                            month = (date / 31) + 1;
                            date = (date % 31);
                            break;
                    }
                    year = miladiYear - 621;
                } else {
                    date = date - 186;

                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 6;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 7;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 621;
                }
            }

            else {
                date = date + 10;

                switch (date % 30) {
                    case 0:
                        month = (date / 30) + 9;
                        date = 30;
                        break;
                    default:
                        month = (date / 30) + 10;
                        date = (date % 30);
                        break;
                }
                year = miladiYear - 622;
            }

        }

        switch (month) {
            case 1:
                strMonth = "فروردين";
                break;
            case 2:
                strMonth = "ارديبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تير";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهريور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دي";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
        }

        switch (WeekDay) {

            case 0:
                strWeekDay = "يکشنبه";
                break;
            case 1:
                strWeekDay = "دوشنبه";
                break;
            case 2:
                strWeekDay = "سه شنبه";
                break;
            case 3:
                strWeekDay = "چهارشنبه";
                break;
            case 4:
                strWeekDay = "پنج شنبه";
                break;
            case 5:
                strWeekDay = "جمعه";
                break;
            case 6:
                strWeekDay = "شنبه";
                break;
        }

    }

}
