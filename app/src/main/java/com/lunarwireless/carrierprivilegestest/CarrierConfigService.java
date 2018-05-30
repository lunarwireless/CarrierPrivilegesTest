package com.lunarwireless.carrierprivilegestest;

import android.os.PersistableBundle;
import android.service.carrier.CarrierIdentifier;
import android.service.carrier.CarrierService;
import android.util.Log;

public class CarrierConfigService extends CarrierService {
    private static final String LOG_TAG = "CarrierConfig";
    private static final String CARRIER_NAME = "TEST_NAME";

    @Override
    public PersistableBundle onLoadConfig(CarrierIdentifier carrierIdentifier) {
        Log.d(LOG_TAG, "Config being fetched");

        PersistableBundle pb = new PersistableBundle();

        pb.putBoolean("carrier_name_override_bool", true);
        pb.putString("carrier_name_string", CARRIER_NAME);

        return pb;
    }
}
