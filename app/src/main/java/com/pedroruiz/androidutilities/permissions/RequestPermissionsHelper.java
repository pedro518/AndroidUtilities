package com.pedroruiz.androidutilities.permissions;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class RequestPermissionsHelper {

    //Request Codes
    public static final int REQUEST_APP_PERMISSIONS = 100;
    public static final int REQUEST_PHONE_CALL_PERMISSIONS = 101;
    public static final int REQUEST_LOCATION_PERMISSIONS = 102;
    public static final int REQUEST_BLUETOOTH_PERMISSIONS = 103;
    public static final int REQUEST_INTERNET_PERMISSIONS = 104;
    public static final int REQUEST_RECEIVE_BOOT_COMPLETED_PERMISSIONS = 105;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSIONS = 106;

    //Permissions
    public static final String[] appPermissions = new String[]{
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.BLUETOOTH, android.Manifest.permission.BLUETOOTH_ADMIN, android.Manifest.permission.BLUETOOTH_PRIVILEGED,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.GET_ACCOUNTS,
            android.Manifest.permission.WAKE_LOCK,
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.GET_ACCOUNTS,
            android.Manifest.permission.WAKE_LOCK,
            android.Manifest.permission.GET_TASKS,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.SYSTEM_ALERT_WINDOW,
    };

    public static final String[] PHONE_CALL_PERMISSIONS = new String[]{android.Manifest.permission.CALL_PHONE};
    public static final String[] LOCATION_PERMISSIONS = new String[]{
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final String[] BLUETOOTH_PERMISSIONS = new String[]{
            android.Manifest.permission.BLUETOOTH,
            android.Manifest.permission.BLUETOOTH_ADMIN};
    public static final String[] INTERNET_PERMISSIONS = new String[]{android.Manifest.permission.INTERNET};
    public static final String[] RECEIVE_BOOT_COMPLETED_PERMISSIONS = new String[]{android.Manifest.permission.RECEIVE_BOOT_COMPLETED};
    public static final String[] WRITE_EXTERNAL_STORAGE_PERMISSIONS = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void requestAPPermissions(final AppCompatActivity activity){
        ActivityCompat.requestPermissions(activity,
                appPermissions,
                REQUEST_APP_PERMISSIONS);
    }

    /**
     * Solicita permiso para poder realizar llamadas telefónicas
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestPhoneCallPermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkPhoneCallPermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    PHONE_CALL_PERMISSIONS[0])) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, PHONE_CALL_PERMISSIONS, REQUEST_PHONE_CALL_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        PHONE_CALL_PERMISSIONS,
                        RequestPermissionsHelper.REQUEST_PHONE_CALL_PERMISSIONS);

            }
        }
    }

    /**
     * Comprueba si la app tiene permiso para realizar llamadas telefónicas
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkPhoneCallPermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Solicita permiso para poder obtener la localización
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestLocationPermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkLocationPermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    LOCATION_PERMISSIONS[0]) || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    LOCATION_PERMISSIONS[1]))) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, LOCATION_PERMISSIONS,  REQUEST_LOCATION_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        LOCATION_PERMISSIONS,
                        RequestPermissionsHelper.REQUEST_LOCATION_PERMISSIONS);
            }

        }
    }

    /**
     * Comprueba si la app tiene permiso para obtener la localización
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkLocationPermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(activity, LOCATION_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Solicita permiso para poder utilizar el bluetooth
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestBluetoothPermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkBluetoothPermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    BLUETOOTH_PERMISSIONS[0]) || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    BLUETOOTH_PERMISSIONS[1]))) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, BLUETOOTH_PERMISSIONS,  REQUEST_BLUETOOTH_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        BLUETOOTH_PERMISSIONS,
                        RequestPermissionsHelper.REQUEST_BLUETOOTH_PERMISSIONS);
            }

        }
    }

    /**
     * Comprueba si la app tiene permiso para utilizar el bluetooth
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkBluetoothPermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, BLUETOOTH_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(activity, BLUETOOTH_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Solicita permiso para poder utilizar el bluetooth
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestInternetPermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkInternetPermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    INTERNET_PERMISSIONS[0]))) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, INTERNET_PERMISSIONS,  REQUEST_INTERNET_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        INTERNET_PERMISSIONS,  REQUEST_INTERNET_PERMISSIONS);
            }

        }
    }

    /**
     * Comprueba si la app tiene permiso para utilizar el bluetooth
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkInternetPermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, INTERNET_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Solicita permiso para poder utilizar el bluetooth
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestReceiveBootCompletedPermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkReceiveBootCompletedPermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    RECEIVE_BOOT_COMPLETED_PERMISSIONS[0]))) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, RECEIVE_BOOT_COMPLETED_PERMISSIONS,  REQUEST_RECEIVE_BOOT_COMPLETED_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        RECEIVE_BOOT_COMPLETED_PERMISSIONS,  REQUEST_RECEIVE_BOOT_COMPLETED_PERMISSIONS);
            }

        }
    }

    /**
     * Comprueba si la app tiene permiso para utilizar el bluetooth
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkReceiveBootCompletedPermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, RECEIVE_BOOT_COMPLETED_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED;
    }

    //////////////////////////////
    /**
     * Solicita permiso para poder utilizar el bluetooth
     * @param activity activity desde la que se solicita el permiso
     * @param explanation mensaje que se desea mostrar como explicación
     */
    public static void requestWriteExternalStoragePermission(final AppCompatActivity activity, @Nullable String explanation){
        if (!checkWriteExternalStoragePermission(activity)) {
            // Should we show an explanation?
            if (explanation != null && !explanation.isEmpty() && (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    WRITE_EXTERNAL_STORAGE_PERMISSIONS[0]))) {

                //Explicar al usuario por que se necesita el permiso
                Bundle extras = new Bundle();
                extras.putString(ExplanationPermissonDialog.EXTRA_MESSAGE, explanation);
                DialogInterface.OnClickListener listener = new ShowExplanationListener(activity, WRITE_EXTERNAL_STORAGE_PERMISSIONS,  REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSIONS);
                ExplanationPermissonDialog d = ExplanationPermissonDialog.newInstance(extras);
                d.setPositiveButtonListener(listener);
                d.show(activity.getSupportFragmentManager(), "");

            } else {

                // No se necesita explicación
                ActivityCompat.requestPermissions(activity,
                        WRITE_EXTERNAL_STORAGE_PERMISSIONS,  REQUEST_RECEIVE_BOOT_COMPLETED_PERMISSIONS);
            }

        }
    }

    /**
     * Comprueba si la app tiene permiso para utilizar el bluetooth
     * @param activity activity desde donde se hace la comprobación
     * @return true si se tienen permisos, false si no se tienen
     */
    public static boolean checkWriteExternalStoragePermission(AppCompatActivity activity){
        return ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Listener que se puede utilizar para establecerlo como positiveButtonlistener del dialog de explicación
     */
    private static class ShowExplanationListener implements DialogInterface.OnClickListener{

        private AppCompatActivity activity;
        private int permissionRequestCode;
        private String[] permissions;

        ShowExplanationListener(AppCompatActivity activity, String[] permissions, int permissionRequestCode){
            this.activity = activity;
            this.permissionRequestCode = permissionRequestCode;
            this.permissions = permissions;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            //Piso de nuevo el permiso por si, depués de la expxlicación, el usuario cambia de opinión
            ActivityCompat.requestPermissions(activity,
                    permissions,
                    permissionRequestCode);
        }
    }

}
