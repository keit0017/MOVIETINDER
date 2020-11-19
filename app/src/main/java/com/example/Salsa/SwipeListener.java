package com.example.Salsa;

import android.util.Log;

import com.wenchao.cardstack.CardStack;

public class SwipeListener implements CardStack.CardEventListener {


    @Override
    public boolean swipeEnd(int i, float v) {
        Log.v("SWIPED;", String.valueOf((i == 1 || i == 3)? true : false));

        return (i == 1 || i== 3)? true : false;

    }

    @Override
    public boolean swipeStart(int i, float v) {
        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public void discarded(int i, int i1) {

    }

    @Override
    public void topCardTapped() {

    }
}
