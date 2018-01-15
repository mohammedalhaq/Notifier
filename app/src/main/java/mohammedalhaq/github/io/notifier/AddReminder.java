package mohammedalhaq.github.io.notifier;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    TextView dateText, timeText;
    int currentYear,currentMonth,currentDay, currentHour, currentMinute;
    String time, date, description;
    CheckBox checkBox;
    Calendar toNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        dateText = findViewById(R.id.date);
        timeText = findViewById(R.id.time);
        EditText editText = (EditText) findViewById(R.id.setDescription);
        description = (String) editText.getText().toString();


        checkBox = (CheckBox) findViewById(R.id.checkPriority);

    }

    public void setReminder(View view){
        toNotify.set(currentYear,currentMonth,currentDay,currentHour,currentMinute);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userSetTime", time);
        intent.putExtra("userSetDate", date);
        intent.putExtra("userDescription", description);
        intent.putExtra("toNotify", toNotify);
        if (checkBox.isChecked()){
            intent.putExtra("isPriority", true);
        }
        startActivity(intent);
    }

    public void viewDate(View view){
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentYear= Calendar.getInstance().get(Calendar.YEAR);
        currentDay= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), AddReminder.this, currentYear, currentMonth, currentDay);

        datePickerDialog.show();
    }

    public void viewTime(View view){
        int currentHour = Calendar.getInstance().get(Calendar.HOUR);
        int currentMin = Calendar.getInstance().get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                time = i + ":" + i1;
                timeText.setText(time);
            }
        }, currentHour, currentMin, false);

        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        if ((year!=0) || (month!=0) || (day!=0)) {
            currentYear=year;
            currentMonth=month;
            currentDay=day;
            date = String.valueOf(day) + "/" + String.valueOf(month+1);
            if (!Integer.toString(currentYear).equals(String.valueOf(year))) {
                date += "/" + String.valueOf(year);
            }
            dateText.setTextColor(Color.BLACK);
            dateText.setText(date);
        } else {
            dateText.setTextColor(Color.RED);
            dateText.setText("Invalid date");
        }
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        currentHour=hour;
        currentMinute=minute;

    }
}
