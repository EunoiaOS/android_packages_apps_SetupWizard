/*
 * SPDX-FileCopyrightText: 2021-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.eunoiaos.setupwizard;

import android.content.ComponentName;
import android.content.Intent;

import com.eunoiaos.setupwizard.util.SetupWizardUtils;

public class DeviceSpecificActivity extends SubBaseActivity {

    private static final String ACTION_SETUP_DEVICE = "com.eunoiaos.settings.device.SUW_SETTINGS";

    protected void onStartSubactivity() {
        Intent intent = new Intent(ACTION_SETUP_DEVICE);
        ComponentName name = intent.resolveActivity(getPackageManager());
        if (name != null) {
            startSubactivity(intent);
        } else {
            SetupWizardUtils.disableComponent(this, DeviceSpecificActivity.class);
            finishAction(RESULT_OK);
        }
    }
}
