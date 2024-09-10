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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}
