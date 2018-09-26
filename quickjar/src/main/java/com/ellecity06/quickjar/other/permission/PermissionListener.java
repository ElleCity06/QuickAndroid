package com.ellecity06.quickjar.other.permission;

/**
 * 权限请求的结果回调
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/26 11:40
 */
public interface PermissionListener {
    void onGranted(String permissionName);//成功授权

    void onDenied(String permissionName);//被拒绝

    void onDeniedWithNeverAsk(String permissionName);//被拒绝，且勾选了“不再提示”
}
