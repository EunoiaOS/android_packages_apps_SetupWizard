/*
 * SPDX-FileCopyrightText: 2016 The CyanogenMod Project
 * SPDX-FileCopyrightText: 2017-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.eunoiaos.setupwizard;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import static com.eunoiaos.setupwizard.SetupWizardApp.ACTION_LOAD;
import static com.eunoiaos.setupwizard.SetupWizardApp.EXTRA_SCRIPT_URI;
import static com.eunoiaos.setupwizard.SetupWizardApp.LOGV;

import android.annotation.Nullable;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.eunoiaos.setupwizard.util.SetupWizardUtils;
import com.eunoiaos.setupwizard.wizardmanager.WizardManager;

public class SetupWizardActivity extends AppCompatActivity {
    private static final String TAG = SetupWizardActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (LOGV) {
            Log.v(TAG, "onCreate savedInstanceState=" + savedInstanceState);
        }
        if (SetupWizardUtils.hasLeanback(this) && SetupWizardUtils.hasGMS(this)) {
            finish();
            return;
        }
        SetupWizardUtils.enableComponent(this, WizardManager.class);
        Intent intent = new Intent(ACTION_LOAD);
        if (SetupWizardUtils.isOwner()) {
            intent.putExtra(EXTRA_SCRIPT_URI, getString(R.string.eunoia_wizard_script_uri));
        } else if (SetupWizardUtils.isManagedProfile(this)) {
            intent.putExtra(EXTRA_SCRIPT_URI, getString(
                    R.string.eunoia_wizard_script_managed_profile_uri));
        } else {
            intent.putExtra(EXTRA_SCRIPT_URI,
                    getString(R.string.eunoia_wizard_script_user_uri));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_GRANT_READ_URI_PERMISSION);
        intent.setPackage(getPackageName());
        startActivity(intent);
        finish();
    }
}
