package com.avoid.ihaveatheory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.avoid.ihaveatheory.R;
import com.avoid.ihaveatheory.global.Session;
import com.avoid.ihaveatheory.model.PlayableActivity;
import com.avoid.ihaveatheory.util.Controller;

public class ShelterActivity extends AppCompatActivity implements PlayableActivity {
    Button restButton;
    Button sleepButton;
    Button campButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        restButton = (Button) findViewById(R.id.shelter_rest_button);
        sleepButton = (Button) findViewById(R.id.shelter_sleep_button);
        campButton = (Button) findViewById(R.id.shelter_camp_button);

        setBackground();
        setIcons();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Session.saveFileHandler.saveGame();
    }

    @Override
    public void setBackground() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_shelter);
        layout.setBackgroundResource(Session.currentSaveFile.getScenario().getDayBackground());
    }

    @Override
    public void setIcons() {

    }

    public void onClickBackButton(View view) {
        finish();
    }

    public void onClickShelterRestButton(View view) {
        MainMenuActivity.playClickSound();
        Session.currentSaveFile.setStamina(Session.currentSaveFile.getStamina() + 50);
        Session.currentSaveFile.setHunger(Session.currentSaveFile.getHunger() - 10);
        Session.currentSaveFile.setThirst(Session.currentSaveFile.getThirst() - 20);

        Controller.INSTANCE.showProgress(ShelterActivity.this, "Resting....", "1 hour");
    }

    public void onClickShelterSleepButton(View view) {
        MainMenuActivity.playClickSound();
        Session.currentSaveFile.setStamina(Session.currentSaveFile.getStamina() + 100);
        Session.currentSaveFile.setHunger(Session.currentSaveFile.getHunger() - 20);
        Session.currentSaveFile.setThirst(Session.currentSaveFile.getThirst() - 35);

        Controller.INSTANCE.showProgress(ShelterActivity.this, "Sleeping....", "4 hour");
    }

    public void onClickShelterCampButton(View view) {
        MainMenuActivity.playClickSound();
        Session.currentSaveFile.setStamina(Session.currentSaveFile.getStamina() - 100);
        Session.currentSaveFile.setHunger(Session.currentSaveFile.getHunger() - 50);
        Session.currentSaveFile.setThirst(Session.currentSaveFile.getThirst() - 100);

        Controller.INSTANCE.showProgress(ShelterActivity.this, "Making a camp....", "4 hours");
    }
}
