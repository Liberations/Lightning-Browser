package acr.browser.lightning

import acr.browser.lightning.utils.Utils
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.NetworkUtils
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

class MySplashActivity:AppCompatActivity() {
    private  val TAG = "testHome"
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
                        android.app.AlertDialog.Builder(this@MySplashActivity).setTitle("提示")
                            .setMessage("请先连接名称为" + Utils.WIFI_SSID + "的WIFI")
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


                    } else {
                        startActivity(Intent(this@MySplashActivity,DefaultBrowserActivity::class.java))
                        finish()
                    }
                }

                override fun onDenied(permissions: MutableList<String>, never: Boolean) {
                    if (never) {
                        toast("Permission Deny")
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(
                            this@MySplashActivity,
                            permissions
                        )
                    } else {
                        toast("Permission Deny")
                    }
                }
            })
    }


    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}