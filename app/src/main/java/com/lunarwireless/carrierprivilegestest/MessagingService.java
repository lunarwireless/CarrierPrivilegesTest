package com.lunarwireless.carrierprivilegestest;

import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.service.carrier.CarrierMessagingService;
import android.service.carrier.MessagePdu;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

public class MessagingService extends CarrierMessagingService {
    private static final String LOG_TAG = "MessagingService";
    @Override
    public void onFilterSms(@NonNull MessagePdu pdu, @NonNull String format, int destPort, int subId, @NonNull ResultCallback<Boolean> callback) {
        super.onFilterSms(pdu, format, destPort, subId, callback);
        Log.i(LOG_TAG,
                String.format("onFilterSms - \n\tBytes: %s", bytePartsToString(pdu.getPdus()))
        );
    }

    @Override
    public void onReceiveTextSms(@NonNull MessagePdu pdu, @NonNull String format, int destPort, int subId, @NonNull ResultCallback<Integer> callback) {
        super.onReceiveTextSms(pdu, format, destPort, subId, callback);
        Log.i(LOG_TAG,
                String.format("onReceiveTextSms - \n\tBytes: %s", bytePartsToString(pdu.getPdus()))
        );
    }

    @Override
    public void onSendTextSms(@NonNull String text, int subId, @NonNull String destAddress, @NonNull ResultCallback<SendSmsResult> callback) {
        super.onSendTextSms(text, subId, destAddress, callback);
        Log.i(LOG_TAG,
                String.format("onSendTextSms - \n\tText: %s\n\tTo: %s", text, destAddress)
        );
    }

    @Override
    public void onSendTextSms(@NonNull String text, int subId, @NonNull String destAddress, int sendSmsFlag, @NonNull ResultCallback<SendSmsResult> callback) {
        super.onSendTextSms(text, subId, destAddress, sendSmsFlag, callback);
        Log.i(LOG_TAG,
                String.format("onSendTextSms - \n\tText: %s\n\tTo: %s", text, destAddress)
        );
    }

    @Override
    public void onSendDataSms(@NonNull byte[] data, int subId, @NonNull String destAddress, int destPort, @NonNull ResultCallback<SendSmsResult> callback) {
        super.onSendDataSms(data, subId, destAddress, destPort, callback);
        Log.i(LOG_TAG,
                String.format("onSendDataSms - \n\tText: %s\n\tTo: %s", byteArrayToHex(data), destAddress)
        );
    }

    @Override
    public void onSendDataSms(@NonNull byte[] data, int subId, @NonNull String destAddress, int destPort, int sendSmsFlag, @NonNull ResultCallback<SendSmsResult> callback) {
        super.onSendDataSms(data, subId, destAddress, destPort, sendSmsFlag, callback);
        Log.i(LOG_TAG,
                String.format("onSendDataSms - \n\tText: %s\n\tTo: %s", byteArrayToHex(data), destAddress)
        );
    }

    @Override
    public void onSendMultipartTextSms(@NonNull List<String> parts, int subId, @NonNull String destAddress, @NonNull ResultCallback<SendMultipartSmsResult> callback) {
        super.onSendMultipartTextSms(parts, subId, destAddress, callback);
        Log.i(LOG_TAG,
                String.format("onSendMultipartTextSms - \n\tParts: %s\n\tTo: %s", partsToString(parts), destAddress)
        );
    }

    @Override
    public void onSendMultipartTextSms(@NonNull List<String> parts, int subId, @NonNull String destAddress, int sendSmsFlag, @NonNull ResultCallback<SendMultipartSmsResult> callback) {
        super.onSendMultipartTextSms(parts, subId, destAddress, sendSmsFlag, callback);
        Log.i(LOG_TAG,
                String.format("onSendMultipartTextSms - \n\tParts: %s\n\tTo: %s", partsToString(parts), destAddress)
        );
    }

    @Override
    public void onSendMms(@NonNull Uri pduUri, int subId, @Nullable Uri location, @NonNull ResultCallback<SendMmsResult> callback) {
        super.onSendMms(pduUri, subId, location, callback);
        Log.i(LOG_TAG,
                String.format("onSendMms - \n\tPDU Uri: %s\n\tLocation: %s", pduUri, location)
        );
    }

    @Override
    public void onDownloadMms(@NonNull Uri contentUri, int subId, @NonNull Uri location, @NonNull ResultCallback<Integer> callback) {
        super.onDownloadMms(contentUri, subId, location, callback);
        Log.i(LOG_TAG,
                String.format("onDownloadMms - \n\tContent Uri: %s\n\tLocation: %s", contentUri, location)
        );
    }

    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        IBinder returnVal = super.onBind(intent);
        Log.i(LOG_TAG,"onBind");
        return returnVal;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private static String bytePartsToString(List<byte[]> parts) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < parts.size(); ++i) {
            sb
                    .append('\n')
                    .append('\t')
                    .append(i)
                    .append(" --> ")
                    .append(byteArrayToHex(parts.get(i)));
        }
        return sb.toString();
    }

    private static String partsToString(List<String> parts) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < parts.size(); ++i) {
            sb
                    .append('\n')
                    .append('\t')
                    .append(i)
                    .append(" --> ")
                    .append(parts.get(i));
        }
        return sb.toString();
    }
}
