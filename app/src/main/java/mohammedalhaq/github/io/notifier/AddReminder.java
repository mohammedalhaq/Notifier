package mohammedalhaq.github.io.notifier;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView dateText;
    TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        dateText = findViewById(R.id.date);
        timeText = findViewById(R.id.time);

    }

    public void setReminder(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewDate(View view){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), AddReminder.this, year, month, day);

        datePickerDialog.show();
    }

    public void viewTime(View view){


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        dateText.setText(calendar.toString());
    }
}
