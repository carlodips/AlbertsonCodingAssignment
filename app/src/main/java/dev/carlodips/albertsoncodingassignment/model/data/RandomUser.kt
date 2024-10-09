package dev.carlodips.albertsoncodingassignment.model.data

data class RandomUser(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
) {
    fun getDisplayName(): String {
        return "${name.first} ${name.last}"
    }

    fun getDisplayAddress(): String {
        return "${location.street.number} ${location.street.name} ${location.city}" +
                " ${location.state} ${location.postcode} ${location.country}"
    }
}
