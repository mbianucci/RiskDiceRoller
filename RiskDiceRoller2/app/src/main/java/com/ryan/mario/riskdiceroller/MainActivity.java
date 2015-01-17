// MainActivity.java
// "I basically copied all of this code"
// "Thanks Google."

package com.ryan.mario.riskdiceroller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    // No idea if these are actually necessary or not, but they aren't hurting anything.
    public final static String EXTRA_MESSAGE1 = "com.ryan.mario.riskdiceroller.attacking";
    public final static String EXTRA_MESSAGE2 = "com.ryan.mario.riskdiceroller.defending";
    public final static String EXTRA_MESSAGE3 = "com.ryan.mario.riskdiceroller.limit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /** Called when the user clicks the Battle button */
    public void Battle(View view) {
        // Creating the intent, i.e. the stuff to send to the next activity
        Intent intent = new Intent(this, BattleActivity.class);

        // Gets the text inputted on this activity
        EditText attacker_armies = (EditText) findViewById(R.id.attacker_armies);
        EditText defender_armies = (EditText) findViewById(R.id.defender_armies);
        EditText attacker_ll = (EditText) findViewById(R.id.attacker_ll);

        // Stops if user doesn't input anything for attacker armies
        try {
            if (attacker_armies.length() == 0) {
                Context context = getApplicationContext();
                CharSequence text = "Please enter a number of attacker armies";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                throw new RuntimeException();
            }
        }
        catch(RuntimeException e) {
            return;
        }

        // Stops if user doesn't input anything for defender armies
        try {
            if (defender_armies.length() == 0) {
                Context context = getApplicationContext();
                CharSequence text = "Please enter a number of defender armies";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                throw new RuntimeException();
            }
        }
        catch(RuntimeException e) {
            return;
        }

        int a_ll;

        // Changes that text into a string, then into integers. Probably could be done in a more straightforward way
        if (attacker_ll.length() == 0) {
            a_ll = 1;
        } else {
            a_ll = Integer.parseInt(attacker_ll.getText().toString());
        }

        int aa = Integer.parseInt(attacker_armies.getText().toString());
        int da = Integer.parseInt(defender_armies.getText().toString());


        // Don't think we need to actually put anything extra, but maybe. It isn't hurting anything.
        intent.putExtra(EXTRA_MESSAGE1, aa);
        intent.putExtra(EXTRA_MESSAGE2, da);
        intent.putExtra(EXTRA_MESSAGE3, a_ll);

        // Starting the new activity and sending the intents
        startActivity(intent);
    }
}
