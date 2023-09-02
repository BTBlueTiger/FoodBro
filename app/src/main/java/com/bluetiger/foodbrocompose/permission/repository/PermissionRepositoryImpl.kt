package com.bluetiger.foodbrocompose.permission.repository

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bluetiger.foodbrocompose.permission.PermissionState
import com.bluetiger.foodbrocompose.permission.data.repository.PermissionRepository
import com.bluetiger.foodbrocompose.permission.domain.model.Permission

class PermissionRepositoryImpl : PermissionRepository {

    /**
     * @return
     *  There are four situations.
     * 1. A Permission Request was never made -> PermissionState. NEVER_REQUIRED
     * 2. It has already been accepted ->
     * PermissionState. GRANTED
     * 3. It has already been picked up once ->
     * PermissionState. RATIONAL
     * 4. It has been checked out and should not be requested again -> PermissionState. DENIED
     *
     */
    override fun checkSelf(context: Context, permission: Permission): Permission {
        ContextCompat.checkSelfPermission(context, permission.permission).also {
            return if (it == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context.getActivity() as Activity,
                        permission.permission
                    )
                ) {
                    permission.copy(
                        permissionState = PermissionState.RATIONAL
                    )
                } else {
                    permission.copy(
                        permissionState = PermissionState.DENIED
                    )
                }
            } else if (it == PackageManager.PERMISSION_GRANTED) {
                permission.copy(
                    permissionState = PermissionState.GRANTED
                )
            } else {
                permission.copy(
                    permissionState = PermissionState.NEVER_REQUIRED
                )
            }
        }
    }

    override fun requestPermission(permission: Permission, context: Context) {
        when (permission.permissionState) {
            PermissionState.NEVER_REQUIRED -> firstRequest(permission, context)
            PermissionState.RATIONAL -> rationalRequest(permission, context)
            PermissionState.DENIED -> showPermissionDeniedDialog(permission, context)

            PermissionState.GRANTED -> {
                throw InvalidPermissionRequest(
                    "If the PermissionState is already Granted it should not requested again"
                )
            }
            // Should never happen !
            // UNKNOWN should only be returned from @checkself to indicate that the permission state
            // is not already given
            PermissionState.UNKNOWN ->
                throw InvalidPermissionRequest(
                    "PermissionState.UNKNOWN should never be insert into the database! Clean it!"
                )
        }
    }


    private fun firstRequest(permission: Permission, context: Context) {
        context.getActivity().also { activityCompat ->
            ActivityCompat.requestPermissions(
                activityCompat as Activity,
                arrayOf(permission.permission),
                permission.requestCode
            )
        }
    }
    private fun rationalRequest(permission: Permission, context: Context) {

        context.getActivity().also { activityCompat ->
            ActivityCompat.requestPermissions(
                activityCompat as Activity,
                arrayOf(permission.permission),
                permission.requestCode
            )
        }
    }
    private fun showPermissionDeniedDialog(permission: Permission, context: Context) {
    }

    class InvalidPermissionRequest(message: String) : Exception(message)

    private fun Context.getActivity(): AppCompatActivity = when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> throw InvalidPermissionRequest("Something went wrong to get the Activity for Permission Issues")
    }
}



