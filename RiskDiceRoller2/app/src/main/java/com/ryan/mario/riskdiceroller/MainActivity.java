package com.ryan.mario.riskdiceroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
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
        Intent intent = new Intent(this, BattleActivity.class);
        EditText attacker_armies = (EditText) findViewById(R.id.attacker_armies);
        EditText defender_armies = (EditText) findViewById(R.id.defender_armies);
        EditText attacker_ll = (EditText) findViewById(R.id.attacker_ll);
        int aa = Integer.parseInt(attacker_armies.getText().toString());
        int da = Integer.parseInt(defender_armies.getText().toString());
        int a_ll = Integer.parseInt(attacker_ll.getText().toString());
        intent.putExtra(EXTRA_MESSAGE1, aa);
        intent.putExtra(EXTRA_MESSAGE2, da);
        intent.putExtra(EXTRA_MESSAGE3, a_ll);
        startActivity(intent);
    }
}
