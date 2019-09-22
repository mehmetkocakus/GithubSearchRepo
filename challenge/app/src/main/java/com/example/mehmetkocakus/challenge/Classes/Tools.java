package com.example.mehmetkocakus.challenge.Classes;

import android.view.View;

import com.example.mehmetkocakus.challenge.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Bu class proje içinde lazım olacak metotlar için kullanılmıştır.
 */
public class Tools {

    /**
     * Bu metot proje içindeki null kontroller için kullanılmıştır.
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }

    /**
     * Ögelere çift tıklama olayını kontrol eder
     *
     * @param view
     */
    public static void preventTwoClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            public void run() {
                view.setEnabled(true);
            }
        }, 500);
    }

    /**
     * Bu metot snackbar hata mesajı içindir
     * @param view
     * @param message
     */
    public static void snackbarErrorMessage(View view,int message)
    {
        Snackbar snackbar = Snackbar.make(view, view.getContext().getResources().getString(message), Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(view.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
