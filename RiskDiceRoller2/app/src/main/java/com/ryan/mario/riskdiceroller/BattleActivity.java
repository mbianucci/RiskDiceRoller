package com.ryan.mario.riskdiceroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.widget.TextView;

import java.util.Random;


public class BattleActivity extends Activity {
    public Die adie = new Die(0,0,0);
    public Die ddie = new Die(0,0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        int attackers_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE1, 0);
        int defenders_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE2, 0);
        int attackers_ll = intent.getIntExtra(MainActivity.EXTRA_MESSAGE3, 0);

        // RNG
        Random rn = new Random();

        while(attackers_armies > attackers_ll && defenders_armies > 0 && attackers_armies > 1){
            adie.resetDie();

            if(attackers_armies > 4){
                adie.setDie1(rn.nextInt(7));
                adie.setDie2(rn.nextInt(7));
                adie.setDie3(rn.nextInt(7));
            } else if (attackers_armies == 3){
                adie.setDie1(rn.nextInt(7));
                adie.setDie2(rn.nextInt(7));
            } else {// Check if something else happens if attacker only has one army**********************************
                adie.setDie1(rn.nextInt(7));
            }

            if(defenders_armies >= 2){
                ddie.setDie1(rn.nextInt(7));
                ddie.setDie2(rn.nextInt(7));
            } else {
                ddie.setDie1(rn.nextInt(7));
            }

            SortHighestOf3(adie);
            SortHighestOf3(ddie);

            if(defenders_armies >= 2){
                if((adie.getDie1() > ddie.getDie1()) && (adie.getDie2() > ddie.getDie2())){
                    defenders_armies = defenders_armies - 2;
                } else if ((adie.getDie1() > ddie.getDie1()) && (adie.getDie2() <= ddie.getDie2())){// Check these******************
                    attackers_armies--;
                    defenders_armies--;
                } else if ((adie.getDie1() <= ddie.getDie1()) && (adie.getDie2() > ddie.getDie2())){// And this*********************
                    attackers_armies--;
                    defenders_armies--;
                }
                if((ddie.getDie1() > adie.getDie1()) && (ddie.getDie2() > adie.getDie2())) {
                    attackers_armies = attackers_armies - 2;
                }
            } else {
                if(adie.getDie1() > ddie.getDie1()){
                    defenders_armies--;
                } else if (ddie.getDie1() > adie.getDie1()){
                    attackers_armies--;
                }
            }
            System.out.println("Attacker armies: " + attackers_armies);
            System.out.println("Defender armies: " + defenders_armies);
        }

        if(attackers_armies == attackers_ll || attackers_armies == 1){
            TextView textView = new TextView(this);
            TextView textView2 = new TextView(this);
            textView.setText("Defender wins. Defender armies: " + defenders_armies + "\n" + "Attacking armies: " + attackers_armies);
            setContentView(textView);
            System.out.println("Defender wins. Defender armies: " + defenders_armies);
            System.out.println("Attacking armies:" + attackers_armies);
        } else if (defenders_armies == 0){
            TextView textView = new TextView(this);
            TextView textView2 = new TextView(this);
            textView.setText("Attacker wins. Attacking armies: " + attackers_armies + "\n" + "Defender armies: " + defenders_armies);
            setContentView(textView);
            System.out.println("Attacker wins. Attacking armies: " + attackers_armies);
            System.out.println("Defender armies:" + defenders_armies);
        } else {
            System.out.println("borked... no winners or losers");
        }
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

    public void SortHighestOf3(Die dice){
        int temp1 = dice.getDie1();
        int temp2 = dice.getDie2();
        int temp3 = dice.getDie3();

        if(temp1 > temp2){
            if(temp1 > temp3){
                dice.setDie1(temp1);
                if(temp2 > temp3){
                    dice.setDie2(temp2);
                    dice.setDie3(temp3);
                } else {
                    dice.setDie2(temp3);
                    dice.setDie3(temp2);
                }
            } else{
                dice.setDie1(temp3);
                dice.setDie2(temp1);
                dice.setDie3(temp2);
            }
        } else if (temp2 > temp1){
            if(temp2 > temp3){
                dice.setDie1(temp2);
                if(temp1 > temp3){
                    dice.setDie2(temp1);
                    dice.setDie3(temp3);
                } else {
                    dice.setDie2(temp3);
                    dice.setDie3(temp1);
                }
            } else {
                dice.setDie1(temp3);
                dice.setDie2(temp2);
                dice.setDie3(temp1);
            }
        } else if(temp1 > temp3){
            if(temp1 > temp2){
                dice.setDie1(temp1);
                if(temp3 > temp2){
                    dice.setDie2(temp3);
                    dice.setDie3(temp2);
                } else {
                    dice.setDie2(temp2);
                    dice.setDie3(temp3);
                }
            } else{
                dice.setDie1(temp2);
                dice.setDie2(temp1);
                dice.setDie3(temp3);
            }
        } else if(temp2 > temp3){
            if(temp2 > temp1){
                dice.setDie1(temp2);
                if(temp3 > temp1){
                    dice.setDie2(temp3);
                    dice.setDie3(temp1);
                } else {
                    dice.setDie2(temp1);
                    dice.setDie3(temp3);
                }
            } else{
                dice.setDie1(temp1);
                dice.setDie2(temp2);
                dice.setDie3(temp3);
            }
        } else if (temp3 > temp1){
            if(temp3 > temp2){
                dice.setDie1(temp3);
                if(temp1 > temp2){
                    dice.setDie2(temp1);
                    dice.setDie3(temp2);
                } else {
                    dice.setDie2(temp2);
                    dice.setDie3(temp1);
                }
            } else {
                dice.setDie1(temp2);
                dice.setDie2(temp3);
                dice.setDie3(temp1);
            }
        } else if(temp3 > temp2){
            if(temp3 > temp1){
                dice.setDie1(temp3);
                if(temp2 > temp1){
                    dice.setDie2(temp2);
                    dice.setDie3(temp1);
                } else {
                    dice.setDie2(temp1);
                    dice.setDie3(temp2);
                }
            } else{
                dice.setDie1(temp1);
                dice.setDie2(temp3);
                dice.setDie3(temp2);
            }
        } else if(temp1 == temp2){
            if(temp1 > temp3){
                dice.setDie1(temp1);
                dice.setDie2(temp2);
                dice.setDie3(temp3);
            } else {
                dice.setDie1(temp3);
                dice.setDie2(temp1);
                dice.setDie3(temp2);
            }
        } else if(temp1 == temp3){
            if(temp1 > temp2){
                dice.setDie1(temp1);
                dice.setDie2(temp3);
                dice.setDie3(temp2);
            } else {
                dice.setDie1(temp2);
                dice.setDie2(temp1);
                dice.setDie3(temp3);
            }
        } else if(temp2 == temp3){
            if(temp2 > temp1){
                dice.setDie1(temp2);
                dice.setDie2(temp3);
                dice.setDie3(temp1);
            } else {
                dice.setDie1(temp1);
                dice.setDie2(temp2);
                dice.setDie3(temp3);
            }
        }
    }
}

class Die {
    private int die1;
    private int die2;
    private int die3;

    // Constructor
    public Die(int die1, int die2, int die3){
        this.die1 = die1;
        this.die2 = die2;
        this.die3 = die3;
    }

    // Setters
    public void setDie1(int die1) { this.die1 = die1;}
    public void setDie2(int die2) { this.die2 = die2;}
    public void setDie3(int die3) { this.die3 = die3;}

    // Getters
    public int getDie1() { return die1;}
    public int getDie2() { return die2;}
    public int getDie3() { return die3;}

    // Reset Values
    public void resetDie() {
        this.die1 = 0;
        this.die2 = 0;
        this.die3 = 0;
    }
}
