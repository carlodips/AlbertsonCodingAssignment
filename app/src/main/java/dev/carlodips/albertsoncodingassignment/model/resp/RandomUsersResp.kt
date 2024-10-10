package dev.carlodips.albertsoncodingassignment.model.resp

import dev.carlodips.albertsoncodingassignment.model.data.Info
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser

data class RandomUsersResp(
    val info: Info?,
    val results: List<RandomUser>?
)