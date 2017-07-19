package com.example.jenif.dameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.jenif.dameapp.Controller.GamePlay;


public class SpielActivity extends AppCompatActivity {


    public static ImageButton[][] buttons;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        buttons = new ImageButton[8][8];
        buttons[0][0] = (ImageButton)findViewById(R.id.feld00);
        buttons[0][1] = (ImageButton)findViewById(R.id.feld01);
        buttons[0][2] = (ImageButton)findViewById(R.id.feld02);
        buttons[0][3] = (ImageButton)findViewById(R.id.feld03);
        buttons[0][4] = (ImageButton)findViewById(R.id.feld04);
        buttons[0][5] = (ImageButton)findViewById(R.id.feld05);
        buttons[0][6] = (ImageButton)findViewById(R.id.feld06);
        buttons[0][7] = (ImageButton)findViewById(R.id.feld07);
        buttons[1][0] = (ImageButton)findViewById(R.id.feld10);
        buttons[1][1] = (ImageButton)findViewById(R.id.feld11);
        buttons[1][2] = (ImageButton)findViewById(R.id.feld12);
        buttons[1][3] = (ImageButton)findViewById(R.id.feld13);
        buttons[1][4] = (ImageButton)findViewById(R.id.feld14);
        buttons[1][5] = (ImageButton)findViewById(R.id.feld15);
        buttons[1][6] = (ImageButton)findViewById(R.id.feld16);
        buttons[1][7] = (ImageButton)findViewById(R.id.feld17);
        buttons[2][0] = (ImageButton)findViewById(R.id.feld20);
        buttons[2][1] = (ImageButton)findViewById(R.id.feld21);
        buttons[2][2] = (ImageButton)findViewById(R.id.feld22);
        buttons[2][3] = (ImageButton)findViewById(R.id.feld23);
        buttons[2][4] = (ImageButton)findViewById(R.id.feld24);
        buttons[2][5] = (ImageButton)findViewById(R.id.feld25);
        buttons[2][6] = (ImageButton)findViewById(R.id.feld26);
        buttons[2][7] = (ImageButton)findViewById(R.id.feld27);
        buttons[3][0] = (ImageButton)findViewById(R.id.feld30);
        buttons[3][1] = (ImageButton)findViewById(R.id.feld31);
        buttons[3][2] = (ImageButton)findViewById(R.id.feld32);
        buttons[3][3] = (ImageButton)findViewById(R.id.feld33);
        buttons[3][4] = (ImageButton)findViewById(R.id.feld34);
        buttons[3][5] = (ImageButton)findViewById(R.id.feld35);
        buttons[3][6] = (ImageButton)findViewById(R.id.feld36);
        buttons[3][7] = (ImageButton)findViewById(R.id.feld37);
        buttons[4][0] = (ImageButton)findViewById(R.id.feld40);
        buttons[4][1] = (ImageButton)findViewById(R.id.feld41);
        buttons[4][2] = (ImageButton)findViewById(R.id.feld42);
        buttons[4][3] = (ImageButton)findViewById(R.id.feld43);
        buttons[4][4] = (ImageButton)findViewById(R.id.feld44);
        buttons[4][5] = (ImageButton)findViewById(R.id.feld45);
        buttons[4][6] = (ImageButton)findViewById(R.id.feld46);
        buttons[4][7] = (ImageButton)findViewById(R.id.feld47);
        buttons[5][0] = (ImageButton)findViewById(R.id.feld50);
        buttons[5][1] = (ImageButton)findViewById(R.id.feld51);
        buttons[5][2] = (ImageButton)findViewById(R.id.feld52);
        buttons[5][3] = (ImageButton)findViewById(R.id.feld53);
        buttons[5][4] = (ImageButton)findViewById(R.id.feld54);
        buttons[5][5] = (ImageButton)findViewById(R.id.feld55);
        buttons[5][6] = (ImageButton)findViewById(R.id.feld56);
        buttons[5][7] = (ImageButton)findViewById(R.id.feld57);
        buttons[6][0] = (ImageButton)findViewById(R.id.feld60);
        buttons[6][1] = (ImageButton)findViewById(R.id.feld61);
        buttons[6][2] = (ImageButton)findViewById(R.id.feld62);
        buttons[6][3] = (ImageButton)findViewById(R.id.feld63);
        buttons[6][4] = (ImageButton)findViewById(R.id.feld64);
        buttons[6][5] = (ImageButton)findViewById(R.id.feld65);
        buttons[6][6] = (ImageButton)findViewById(R.id.feld66);
        buttons[6][7] = (ImageButton)findViewById(R.id.feld67);
        buttons[7][0] = (ImageButton)findViewById(R.id.feld70);
        buttons[7][1] = (ImageButton)findViewById(R.id.feld71);
        buttons[7][2] = (ImageButton)findViewById(R.id.feld72);
        buttons[7][3] = (ImageButton)findViewById(R.id.feld73);
        buttons[7][4] = (ImageButton)findViewById(R.id.feld74);
        buttons[7][5] = (ImageButton)findViewById(R.id.feld75);
        buttons[7][6] = (ImageButton)findViewById(R.id.feld76);
        buttons[7][7] = (ImageButton)findViewById(R.id.feld77);

        GamePlay gp = new GamePlay();

    }
}
