package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserPersonalInformationRepository

class GetPendingNewUserPersonalInformation(
    private val repository: UserPersonalInformationRepository
) {
    operator fun invoke(): SnapshotStateMap<UserPersonalInformation.ValueType, Any> {
        return repository.pendingNewUser
    }
    fun notNull(): Boolean {
        return !repository.pendingNewUser.isEmpty()
    }
}