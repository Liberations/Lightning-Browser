package acr.browser.lightning

import acr.browser.lightning.browser.BrowserActivity
import acr.browser.lightning.utils.Utils
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.blankj.utilcode.util.NetworkUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions


/**
 * The default browsing experience.
 */
class DefaultBrowserActivity : BrowserActivity() {
    private val TAG = "testHome"
    override fun isIncognito(): Boolean = false

    override fun menu(): Int = R.menu.main

    override fun homeIcon(): Int = R.drawable.ic_action_home
    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XXPermissions.with(this)
            .permission(Permission.ACCESS_COARSE_LOCATION)
            .permission(Permission.ACCESS_FINE_LOCATION)
            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            .request(object : OnPermissionCallback {

                override fun onGranted(permissions: MutableList<String>, all: Boolean) {
                    if (!all) {
                        toast("Permission Deny")
                        finish()
                        return
                    }
                    Log.d(TAG, "onCreate: " + NetworkUtils.getSSID())
                    if (!TextUtils.equals(NetworkUtils.getSSID(), Utils.WIFI_SSID)) {
                        AlertDialog.Builder(this@DefaultBrowserActivity).setTitle("提示")
                            .setMessage("当前WIFI不正确"+NetworkUtils.getSSID()+ "\n" +
                                    "请先连接WiFi " + Utils.WIFI_SSID)
                            .setOnDismissListener {
                                finish()
                            }
                            .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    val intent = Intent(Settings.ACTION_SETTINGS)
                                    startActivity(intent)
                                    finish()
                                }

                            })
                            .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    finish()
                                }

                            }).show()


                    }
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    if (never) {
                        toast("Permission Deny")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@DefaultBrowserActivity,
                            permissions
                        )
                    } else {
                        toast("Permission Deny")
                    }
                }
            })


    }

}
