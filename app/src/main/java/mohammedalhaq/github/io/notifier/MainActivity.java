package mohammedalhaq.github.io.notifier;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> reminderTime = new ArrayList<String>();
    static ArrayList<String> reminder = new ArrayList<String>();
    static int notifId = 1;
    String finalTime = "null";
    String description = "null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null) {
            Calendar calendar = (Calendar) bundle.get("toNotify");
            String userSetTime =(String) bundle.get("userSetTime");
            String userSetDate = (String) bundle.get("userSetDate");
            description = (String) bundle.get("description");
            finalTime = userSetDate + ", " + userSetTime;


            Intent result = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId, result, 0);

            NotificationCompat.Builder n = new NotificationCompat.Builder(this);
            n.setSmallIcon(R.mipmap.ic_launcher_round);
            n.setContentTitle("Notifier");
            n.setContentText(description);
            n.setContentIntent(pendingIntent);
            n.setDefaults(Notification.DEFAULT_SOUND);

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notifId, n.build());
            notifId++;

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        reminderTime.add(finalTime);
        reminder.add(description);

        ArrayAdapter<String> item1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,reminderTime);
        listView.setAdapter(item1);

        //ArrayAdapter<String> item2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,reminder);
        //listView.setAdapter(item2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent edit = new Intent(MainActivity.this, AddReminder.class);
                edit.putExtra("pastDescription", description);

                startActivity(edit);
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddReminder.class);
                startActivity(intent);


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
