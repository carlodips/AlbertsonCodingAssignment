package dev.carlodips.albertsoncodingassignment.utils

import dev.carlodips.albertsoncodingassignment.model.data.Coordinates
import dev.carlodips.albertsoncodingassignment.model.data.Dob
import dev.carlodips.albertsoncodingassignment.model.data.Id
import dev.carlodips.albertsoncodingassignment.model.data.Location
import dev.carlodips.albertsoncodingassignment.model.data.Login
import dev.carlodips.albertsoncodingassignment.model.data.Name
import dev.carlodips.albertsoncodingassignment.model.data.Picture
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.model.data.Registered
import dev.carlodips.albertsoncodingassignment.model.data.Street
import dev.carlodips.albertsoncodingassignment.model.data.Timezone

class MockData {
    companion object {
        val location = Location(
            street = Street(
                number = 8929,
                name = "Valwood Pkwy"
            ),
            city = "Billings",
            state = "Michigan",
            country = "United States",
            postcode = "63104",
            coordinates = Coordinates(
                latitude = "-69.8246",
                longitude = "134.8719"
            ),
            timezone = Timezone(
                offset = "+9:30",
                description = "Adelaide, Darwin"
            )
        )

        val randomUser = RandomUser(
            gender = "female",
            name = Name(
                title = "Miss",
                first = "Jennie",
                last = "Nichols"
            ),
            location = location,
            email = "jennie.nichols@example.com",
            login = Login(
                uuid = "7a0eed16-9430-4d68-901f-c0d4c1c3bf00",
                username = "yellowpeacock117",
                password = "addison",
                salt = "sld1yGtd",
                md5 = "ab54ac4c0be9480ae8fa5e9e2a5196a3",
                sha1 = "edcf2ce613cbdea349133c52dc2f3b83168dc51b",
                sha256 = "48df5229235ada28389b91e60a935e4f9b73eb4bdb855ef9258a1751f10bdc5d"
            ),
            dob = Dob(
                date = "1992-03-08T15:13:16.688Z",
                age = 30
            ),
            registered = Registered(
                date = "2007-07-09T05:51:59.390Z",
                age = 14
            ),
            phone = "(272) 790-0888",
            cell = "(489) 330-2385",
            id = Id(
                name = "SSN",
                value = "405-88-3636"
            ),
            picture = Picture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            nat = "US"


        )
    }
}