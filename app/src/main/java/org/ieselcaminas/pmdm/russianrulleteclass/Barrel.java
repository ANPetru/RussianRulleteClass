package org.ieselcaminas.pmdm.russianrulleteclass;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Barrel extends LinearLayout {



    public interface FireListener {
        void fire(boolean bang);
    }

    public static final int NUM_BULLETS = 6;
    private Button[] buttons;
    private Button bulletButton;
    private boolean gameOver;
    private FireListener fireListener;

    public void setFireListener(FireListener fireListener) {
        this.fireListener = fireListener;
    }

    public Barrel(Context context) {
        super(context);
        createButtons();
        insertBulletIntoBarrel();
    }

    private void createButtons() {
        buttons = new Button[NUM_BULLETS];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(getContext(), null, android.R.attr.buttonStyleSmall);
            buttons[i].setText("" + (i + 1));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (gameOver) return;
                    if (fireListener == null) return;
                    v.setEnabled(false);
                    if (v == bulletButton) {
                        gameOver=true;
                        fireListener.fire(true);
                    } else {
                        fireListener.fire(false);
                    }

                }
            });
            addView(buttons[i]);
        }
    }

    public Barrel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createButtons();
        insertBulletIntoBarrel();
    }

    public Barrel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createButtons();
        insertBulletIntoBarrel();
    }

    private void insertBulletIntoBarrel() {
        gameOver = false;
        int bulletIndex = (int) (Math.random() * NUM_BULLETS);
        bulletButton = buttons[bulletIndex];
    }

    public void reset() {
        for(Button b : buttons){
            b.setEnabled(true);
        }
        insertBulletIntoBarrel();
    }

    public boolean getGameOver(){
        return gameOver;
    }
}
