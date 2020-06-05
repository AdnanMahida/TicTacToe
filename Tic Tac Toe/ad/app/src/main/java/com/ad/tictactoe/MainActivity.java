package com.ad.tictactoe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    int activeplayer = 0;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    ImageButton btnreset;
    TextView status;
    int[] gameStateArray = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos2DArray = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    // 0 =x
    // 1 =o
    //  2 = not active
    public void reSetBtn(View v) {
        reSetGame();
    }

    @SuppressLint("SetTextI18n")
    public void tapClicked(View v) {
        ImageView images = (ImageView) v;
        int tapped = Integer.parseInt(images.getTag().toString());
        if (!gameActive) {
            reSetGame();
        }
        if (gameStateArray[tapped] == 2) {
            gameStateArray[tapped] = activeplayer;
            if (activeplayer == 0) {
                images.setImageResource(R.drawable.ex);
                activeplayer = 1;
                status.setText("0 's Turn");
            } else {
                images.setImageResource(R.drawable.zero);
                activeplayer = 0;
                status.setText("X 's Turn");
            }
        }
        for (int[] winPosition : winPos2DArray) {
            if (gameStateArray[winPosition[0]] == gameStateArray[winPosition[1]] && gameStateArray[winPosition[1]] == gameStateArray[winPosition[2]] &&
                    gameStateArray[winPosition[0]] != 2) {
                gameActive = false;
                if (gameStateArray[winPosition[0]] == 0) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.MyDialog);
                    builder.setMessage("X is Won").setIcon(R.drawable.ex).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reSetGame();
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    status.setText("");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.MyDialog);
                    builder.setMessage("0 is Won").setIcon(R.drawable.zero).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reSetGame();
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    status.setText("");
                }
            }
        }

    }

    private void init() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        status = findViewById(R.id.m_txt_status);
        btnreset = findViewById(R.id.btn_reset);
    }

    private void reSetGame() {
        gameActive = true;
        activeplayer = 0;
        try {
            for (int i = 0; i <= gameStateArray.length; i++) {
                gameStateArray[i] = 2;
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        img1.setImageResource(0);
        img2.setImageResource(0);
        img3.setImageResource(0);
        img4.setImageResource(0);
        img5.setImageResource(0);
        img6.setImageResource(0);
        img7.setImageResource(0);
        img8.setImageResource(0);
        img9.setImageResource(0);
        status.setText("X 's Turn ,Tap for To play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //tapClicked(getCurrentFocus());
    }

}
