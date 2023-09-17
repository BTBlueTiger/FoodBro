package com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_personal_informations

data class UserPersonalInformationUseCases(
    val addUserPersonalInformation: AddUserPersonalInformation,
    val deleteUserPersonalInformation: DeleteUserPersonalInformation,
    val getUserPersonalInformation: GetUserPersonalInformation,
    val getAllLocalUserPersonalInformation: GetAllLocalUserPersonalInformation,
    val getPendingNewUserPersonalInformation: GetPendingNewUserPersonalInformation,
    val setPendingNewUserPersonalInformation: SetPendingNewUserPersonalInformation,
    val initPendingNewUserPersonalInformation: InitPendingNewUserPersonalInformation
)
