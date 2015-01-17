// BattleActivity.java
// Where the magic happens. (tm)
// AKA the file that has the code for doing all of the dice rolls and determining the winner

package com.ryan.mario.riskdiceroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Random;

public class BattleActivity extends Activity {
    public Die adie = new Die(0,0,0); //Attacker die
    public Die ddie = new Die(0,0,0); //Defender die

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();

        // attackers_armies, defenders_armies self-explanatory, attackers_ll is the lower limit for the attackers' army
        int attackers_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE1, 0);
        int defenders_armies = intent.getIntExtra(MainActivity.EXTRA_MESSAGE2, 0);
        int attackers_ll = intent.getIntExtra(MainActivity.EXTRA_MESSAGE3, 0);

        // From here on should maybe be its own function?******************************************************

        // RNG
        Random rn = new Random();

        // Make sure the attackers armies doesn't go beyond the lower limit or 1, and defender doesn't go beyond 0
        while(attackers_armies > attackers_ll && defenders_armies > 0 && attackers_armies > 1){
            // Reset the die every time so that if one of the players is rolling less than 3 die, the extra(s) stays at 0.
            adie.resetDie();

            // Greater than or equal to 4, roll 3 die. 3 means roll 2 die, 2 rolls 1 die. 1 should never happen
            if(attackers_armies >= 4){
                adie.setDie1(rn.nextInt(6) + 1);
                adie.setDie2(rn.nextInt(6) + 1);
                adie.setDie3(rn.nextInt(6) + 1);
            } else if (attackers_armies == 3){
                adie.setDie1(rn.nextInt(6) + 1);
                adie.setDie2(rn.nextInt(6) + 1);
            } else {
                adie.setDie1(rn.nextInt(6) + 1);
            }

            // Defender rolls 2 for greater than or equal to 2 armies, 1 otherwise
            if(defenders_armies >= 2){
                ddie.setDie1(rn.nextInt(6) + 1);
                ddie.setDie2(rn.nextInt(6) + 1);
            } else {
                ddie.setDie1(rn.nextInt(6) + 1);
            }

            // Sort the 3 die for both from highest to lowest.
            SortHighestOf3(adie);
            SortHighestOf3(ddie);

            // Printing the die to the console to see if it is sorting right
            System.out.println("Attacker dice, H to L: " + adie.getDie1() + " " + adie.getDie2() + " " + adie.getDie3());
            System.out.println("Defender dice, H to L: " + ddie.getDie1() + " " + ddie.getDie2() + " " + ddie.getDie3());

            // Determine how many are lost on each side. This needs to be looked over**********************************************
            if(defenders_armies >= 2 && attackers_armies > 2) {
                if ((adie.getDie1() > ddie.getDie1()) && (adie.getDie2() > ddie.getDie2())) {
                    defenders_armies = defenders_armies - 2;
                } else if ((adie.getDie1() > ddie.getDie1()) && (adie.getDie2() <= ddie.getDie2())) {
                    attackers_armies--;
                    defenders_armies--;
                } else if ((adie.getDie1() <= ddie.getDie1()) && (adie.getDie2() > ddie.getDie2())) {
                    attackers_armies--;
                    defenders_armies--;
                } else if ((ddie.getDie1() > adie.getDie1()) && (ddie.getDie2() > adie.getDie2())) {
                    attackers_armies = attackers_armies - 2;
                }
            } else {
                if(adie.getDie1() > ddie.getDie1()){
                    defenders_armies--;
                } else if (ddie.getDie1() > adie.getDie1()){
                    attackers_armies--;
                }
            }
            // Prints the number of armies after each round in the console
            System.out.println("Attacker armies: " + attackers_armies);
            System.out.println("Defender armies: " + defenders_armies);
        }

        // Determines the winner, prints that out to app and console.
        // There is probably a better way of printing these things to the app, i.e. through/with the xml file
        // Not sure how to do it, but something to look into.
        if(attackers_armies == attackers_ll || attackers_armies == 1){
            TextView textView = new TextView(this);
            textView.setText("Defender wins. Defender armies: " + defenders_armies + "\n" + "Attacking armies: " + attackers_armies);
            setContentView(textView);
            System.out.println("Defender wins. Defender armies: " + defenders_armies);
            System.out.println("Attacking armies:" + attackers_armies);
        } else if (defenders_armies == 0){
            TextView textView = new TextView(this);
            textView.setText("Attacker wins. Attacking armies: " + attackers_armies + "\n" + "Defender armies: " + defenders_armies);
            setContentView(textView);
            System.out.println("Attacker wins. Attacking armies: " + attackers_armies);
            System.out.println("Defender armies:" + defenders_armies);
        } else {
            System.out.println("borked... no winners or losers");
        }
    }

    // This came free with the file... Not touching it.
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

    // Function that takes a Die object and sorts the 3 ints in order, highest to lowest.
    public void SortHighestOf3(Die dice){
        // Makes three temp variables to begin to make swaps simple
        int temp1 = dice.getDie1();
        int temp2 = dice.getDie2();
        int temp3 = dice.getDie3();


        if(temp1 > temp2){
            if(temp1 > temp3){
                // If temp1 is greater than temp2 and temp3, it must be the highest, then determine the order of the other 2
                dice.setDie1(temp1);
                if(temp2 > temp3){
                    dice.setDie2(temp2);
                    dice.setDie3(temp3);
                } else {
                    // If temp2 isn't higher than temp3, it must be that temp3 is either higher or equal, Die2 can safely be temp3 in
                    // either case.
                    dice.setDie2(temp3);
                    dice.setDie3(temp2);
                }
            } else{
                // If temp1 is greater than temp2, but temp3 is greater than or equal to temp1, then temp3 can safely be Die1 and always be
                // correct, then Die1 must be temp1 and Die3 must be temp2
                dice.setDie1(temp3);
                dice.setDie2(temp1);
                dice.setDie3(temp2);
            }
        } else if (temp2 > temp1){
            // Same basic logic as for the first 'if', just changing the variables around.
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
            // Same logic as first 'if'
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
            // Same
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
            // You get the idea...
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
            // This was a lot of work.
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
                // If temp1 is greater than temp3 and temp1 is equal to temp2, then temp2 must be greater than temp3, so temp3 must be
                // Die 3, and the order of temp1 and temp2 doesn't matter
                dice.setDie1(temp1);
                dice.setDie2(temp2);
                dice.setDie3(temp3);
            } else {
                // If temp3 is greater than or equal to temp1, then temp3 can safely be Die1 and the order of the other temps does not matter
                dice.setDie1(temp3);
                dice.setDie2(temp1);
                dice.setDie3(temp2);
            }
        } else if(temp1 == temp3){
            // Same logic as above
            if(temp1 > temp2) {
                dice.setDie1(temp1);
                dice.setDie2(temp3);
                dice.setDie3(temp2);
            } else {
                dice.setDie1(temp2);
                dice.setDie2(temp1);
                dice.setDie3(temp3);
            }
        } else if(temp2 == temp3){
            // Same logic as above
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

// Die object
class Die {
    // Never have more than 3 die, but easy to change if needed
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

    // Reset Values to 0
    public void resetDie() {
        this.die1 = 0;
        this.die2 = 0;
        this.die3 = 0;
    }
}
