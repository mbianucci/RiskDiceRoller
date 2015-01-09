package com.ryan.mario.riskdiceroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Random;


public class BattleActivity extends Activity {
    private int attacker_1;
    private int attacker_2;
    private int attacker_3;
    private int defender_1;
    private int defender_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        int attackers_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE1, 0);
        int defenders_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE2, 0);
        int attackers_ll = intent.getIntExtra(MainActivity.EXTRA_MESSAGE3, 0);

        // Determine who wins
        Random rn = new Random();

        int temp1;
        int temp2;
        int temp3;
        int temp4;
        int temp5;

        while(attackers_armies > attackers_ll && defenders_armies > 0){
            temp1 = rn.nextInt(7);
            temp2 = rn.nextInt(7);
            temp3 = rn.nextInt(7);
            temp4 = rn.nextInt(7);
            temp5 = rn.nextInt(7);


            if(attackers_armies > 4){
                SortHighestOf3(temp1, temp2, temp3);
            }
        }

        /** Create the text view
        TextView aa = new TextView(this);
        aa.setTextSize(40);
        aa.setText(attackers_armies);

        TextView da = new TextView(this);
        da.setTextSize(40);
        da.setText(defenders_armies);

        TextView aa_ll = new TextView(this);
        aa_ll.setTextSize(40);
        aa_ll.setText(attackers_ll);

        // Set the text view as the activity layout
        setContentView(aa);
        setContentView(da);
        setContentView(aa_ll);*/
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

    public void SortHighestOf3(int temp1, int temp2, int temp3){
        if(temp1 > temp2){
            if(temp1 > temp3){
                attacker_1 = temp1;
                if(temp2 > temp3){
                    attacker_2 = temp2;
                    attacker_3 = temp3;
                } else {
                    attacker_2 = temp3;
                    attacker_3 = temp2;
                }
            } else{
                attacker_1 = temp3;
                attacker_2 = temp1;
                attacker_3 = temp2;
            }
        } else if (temp2 > temp1){
            if(temp2 > temp3){
                attacker_1 = temp2;
                if(temp1 > temp3){
                    attacker_2 = temp1;
                    attacker_3 = temp3;
                } else {
                    attacker_2 = temp3;
                    attacker_3 = temp1;
                }
            } else {
                attacker_1 = temp3;
                attacker_2 = temp2;
                attacker_3 = temp1;
            }
        } else if(temp1 > temp3){
            if(temp1 > temp2){
                attacker_1 = temp1;
                if(temp3 > temp2){
                    attacker_2 = temp3;
                    attacker_3 = temp2;
                } else {
                    attacker_2 = temp2;
                    attacker_3 = temp3;
                }
            } else{
                attacker_1 = temp2;
                attacker_2 = temp1;
                attacker_3 = temp3;
            }
        } else if(temp2 > temp3){
            if(temp2 > temp1){
                attacker_1 = temp2;
                if(temp3 > temp1){
                    attacker_2 = temp3;
                    attacker_3 = temp1;
                } else {
                    attacker_2 = temp1;
                    attacker_3 = temp3;
                }
            } else{
                attacker_1 = temp1;
                attacker_2 = temp2;
                attacker_3 = temp3;
            }
        } else if (temp3 > temp1){
            if(temp3 > temp2){
                attacker_1 = temp3;
                if(temp1 > temp2){
                    attacker_2 = temp1;
                    attacker_3 = temp2;
                } else {
                    attacker_2 = temp2;
                    attacker_3 = temp1;
                }
            } else {
                attacker_1 = temp2;
                attacker_2 = temp3;
                attacker_3 = temp1;
            }
        } else if(temp3 > temp2){
            if(temp3 > temp1){
                attacker_1 = temp3;
                if(temp2 > temp1){
                    attacker_2 = temp2;
                    attacker_3 = temp1;
                } else {
                    attacker_2 = temp1;
                    attacker_3 = temp2;
                }
            } else{
                attacker_1 = temp1;
                attacker_2 = temp3;
                attacker_3 = temp2;
            }
        } else if(temp1 == temp2){
            if(temp1 > temp3){
                attacker_1 = temp1;
                attacker_2 = temp2;
                attacker_3 = temp3;
            } else {
                attacker_1 = temp3;
                attacker_2 = temp1;
                attacker_3 = temp2;
            }
        } else if(temp1 == temp3){
            if(temp1 > temp2){
                attacker_1 = temp1;
                attacker_2 = temp3;
                attacker_3 = temp2;
            } else {
                attacker_1 = temp2;
                attacker_2 = temp1;
                attacker_3 = temp3;
            }
        } else if(temp2 == temp3){
            if(temp2 > temp1){
                attacker_1 = temp2;
                attacker_2 = temp3;
                attacker_3 = temp1;
            } else {
                attacker_1 = temp1;
                attacker_2 = temp2;
                attacker_3 = temp3;
            }
        }
    }

    public void SortHighestOf2(int temp1, int temp2){
        if(temp1 > temp2)
    }
}
