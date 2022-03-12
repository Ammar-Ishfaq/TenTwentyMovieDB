package com.muhammad_ammar.tentwenty.view.fragments.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.muhammad_ammar.tentwenty.util.AlertMessageDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment : Fragment() {


    private var mPreviousView: View? = null
    private var mModule: Module? = null


    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun inOnCreateView(
        mRootView: ViewGroup, savedInstanceState: Bundle?
    )

    open fun themeInflater(baseInflater: LayoutInflater): LayoutInflater? = null

    open fun initializeView(view: View) {}

    open fun registerModule(): Module? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mPreviousView?.let { return it }
        val newInflater = themeInflater(inflater) ?: inflater
        val mView = newInflater.inflate(getLayoutResId(), container, false)
        initializeView(mView)
        val module = registerModule()
        if (module != null)
            loadKoinModules(module).also { mModule = module }
        inOnCreateView(mView as ViewGroup, savedInstanceState)
        return mView.also {
            mPreviousView = it
        }
    }

    protected fun <T, LD : LiveData<T>> observe(liveData: LD, onChanged: (T) -> Unit) {
        liveData.observe(this, Observer {
            it?.let(onChanged)
        })
    }

    suspend fun customDelay(delayValue: Long) = withContext(Dispatchers.IO) {
        delay(delayValue)
    }

    override fun onDestroy() {
        super.onDestroy()
        mModule?.let { unloadKoinModules(it) }
    }

    public fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

//    fun isValidPassword(password: String?): Boolean {
//        password?.let {
//            val passwordPattern =
//                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
//            val passwordMatcher = Regex(passwordPattern)
//
//            return passwordMatcher.find(password) != null
//        } ?: return false
//    }

    fun showAlertDialog(
        msg: String,
        positiveButtonCallback: AlertMessageDialog.PositiveButtonCallback? = null
    ) {
        AlertMessageDialog.newInstance(msg, positiveButtonCallback)
            .show(requireActivity().supportFragmentManager, AlertMessageDialog.TAG)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

}