package com.kuaikuaiyu.assistant.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/26
 * Desc:    Android 6.0运行时权限检查工具
 */
public class PermissionUtil {
    public static final int PERMISSIONS_CALENDAR = 101;
    public static final int PERMISSIONS_CAMERA = 102;
    public static final int PERMISSIONS_CONTACTS = 103;
    public static final int PERMISSIONS_LOCATION = 104;
    public static final int PERMISSIONS_MICROPHONE = 105;
    public static final int PERMISSIONS_PHONE = 106;
    public static final int PERMISSIONS_SENSORS = 107;
    public static final int PERMISSIONS_SMS = 108;
    public static final int PERMISSIONS_STORAGE = 109;

    /**
     * 检查当前应用是否拥有特定的一些权限，
     * 如果参数权限组的子权限中存在未获取的权限，则申请相应的权限然后返回 false，
     * 如果已经获取全部权限则直接返回 true
     * <p/>
     * 回调的 RequestCode 为 PermissionUtil.PERMISSIONS_REQUEST_CODE
     *
     * @param permissions 请求权限时的 requestCode默认为第一个参数值
     * @return true 全部子权限都有， false 子权限中有部分（或一个）没有
     */
    public static boolean checkAndRequestPermission(Activity activity, int... permissions) {
        List<String> permsNotAcquired = getPermissionsNotAcquired(activity,
                getPermissionList(permissions));
        if (permsNotAcquired.size() > 0) {
            requestPermissions(activity, permsNotAcquired, permissions[0]);
            return false;
        } else {
            return true;
        }
    }

    /**
     * for fragment
     *
     * @param fragment
     * @param permissions
     * @return
     */
    public static boolean checkAndRequestPermission(Fragment fragment, int... permissions) {
        List<String> permsNotAcquired = getPermissionsNotAcquired(fragment.getActivity(),
                getPermissionList(permissions));
        if (permsNotAcquired.size() > 0) {
            requestPermissions(fragment, permsNotAcquired, permissions[0]);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查当前应用是否拥有特定的一些权限
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean checkPermission(Activity activity, int... permissions) {
        List<String> permsNotAcquired = getPermissionsNotAcquired(activity,
                getPermissionList(permissions));
        if (permsNotAcquired.size() > 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 判断权限请求是否得到用户允许
     * 在 onRequestPermissionsResult 中调用
     *
     * @param activity
     * @param grantResults       请求权限然后返回的结果
     * @param rejectMsg          用户不授权时弹出对话框的提示
     * @param settingRequestCode 启动应用设置设置权限后返回时的activityResultCode
     *                           AppConfig.REQUEST_CODE_SETTING is suggested
     * @return
     */
    public static boolean checkPermissionRequestResult(Activity activity, int[] grantResults,
                                                       String rejectMsg, int settingRequestCode) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            showGoSettingDia(activity, rejectMsg, settingRequestCode);
            return false;
        }
    }

    /**
     * for fragment
     * * 判断权限请求是否得到用户允许
     * 在 onRequestPermissionsResult 中调用
     *
     * @param fragment
     * @param grantResults
     * @param rejectMsg
     * @param settingRequestCode
     * @return
     */
    public static boolean checkPermissionRequestResult(Fragment fragment, int[] grantResults,
                                                       String rejectMsg, int settingRequestCode) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            showGoSettingDia(fragment, rejectMsg, settingRequestCode);
            return false;
        }
    }

    /**
     * 跳转到应用权限设置页面
     */
    private static void showGoSettingDia(final Activity activity, String msg, final int requestCode) {
        new AlertDialog.Builder(activity).setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSetting(activity, requestCode);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * for fragment
     * 跳转到应用权限设置页面
     *
     * @param fragment
     * @param msg
     * @param requestCode
     */
    private static void showGoSettingDia(final Fragment fragment, String msg, final int requestCode) {
        new AlertDialog.Builder(fragment.getActivity()).setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSetting(fragment, requestCode);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 开启应用设置
     *
     * @param activity
     * @param requestCode
     */
    private static void startAppSetting(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 开启应用设置
     * for fragmente
     *
     * @param fragment
     * @param requestCode
     */
    private static void startAppSetting(Fragment fragment, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", fragment.getActivity().getPackageName(), null);
        intent.setData(uri);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 申请权限
     *
     * @param activity
     * @param perms
     */
    private static void requestPermissions(Activity activity, List<String> perms, int requestCode) {
        ActivityCompat.requestPermissions(activity, perms.toArray(new String[perms.size()]),
                requestCode);
    }

    /**
     * 申请权限 for fragment
     *
     * @param fragment
     * @param perms
     * @param requestCode
     */
    private static void requestPermissions(Fragment fragment, List<String> perms, int requestCode) {
        fragment.requestPermissions(perms.toArray(new String[perms.size()]),
                requestCode);
    }

    /**
     * 获取应用目前没有取得的权限
     *
     * @param activity
     * @param perms
     * @return
     */
    private static List<String> getPermissionsNotAcquired(Activity activity, List<String> perms) {
        List<String> permissionsNotAcquired = new ArrayList<>();
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
                permissionsNotAcquired.add(perm);
            }
        }
        return permissionsNotAcquired;
    }

    /**
     * 获取权限组对应的子权限列表
     *
     * @param perms
     * @return
     */
    private static List<String> getPermissionList(int[] perms) {
        List<String> permissions = new ArrayList<>();
        for (int perm : perms) {
            switch (perm) {
                case PERMISSIONS_CALENDAR:
                    permissions.add(Manifest.permission.WRITE_CALENDAR);
                    permissions.add(Manifest.permission.READ_CALENDAR);
                    break;

                case PERMISSIONS_CAMERA:
                    permissions.add(Manifest.permission.CAMERA);
                    break;

                case PERMISSIONS_CONTACTS:
                    permissions.add(Manifest.permission.READ_CONTACTS);
                    permissions.add(Manifest.permission.WRITE_CONTACTS);
                    permissions.add(Manifest.permission.GET_ACCOUNTS);
                    break;

                case PERMISSIONS_LOCATION:
                    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
                    permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                    break;

                case PERMISSIONS_MICROPHONE:
                    permissions.add(Manifest.permission.RECORD_AUDIO);
                    break;

                case PERMISSIONS_PHONE:
                    permissions.add(Manifest.permission.READ_PHONE_STATE);
                    permissions.add(Manifest.permission.CALL_PHONE);
                    permissions.add(Manifest.permission.READ_CALL_LOG);
                    permissions.add(Manifest.permission.WRITE_CALL_LOG);
                    permissions.add(Manifest.permission.ADD_VOICEMAIL);
                    permissions.add(Manifest.permission.USE_SIP);
                    permissions.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
                    break;

                case PERMISSIONS_SENSORS:
                    permissions.add(Manifest.permission.BODY_SENSORS);
                    break;

                case PERMISSIONS_SMS:
                    permissions.add(Manifest.permission.SEND_SMS);
                    permissions.add(Manifest.permission.RECEIVE_SMS);
                    permissions.add(Manifest.permission.READ_SMS);
                    permissions.add(Manifest.permission.RECEIVE_WAP_PUSH);
                    permissions.add(Manifest.permission.RECEIVE_MMS);
                    break;

                case PERMISSIONS_STORAGE:
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    break;

                default:
                    break;
            }
        }
        return permissions;
    }
}

