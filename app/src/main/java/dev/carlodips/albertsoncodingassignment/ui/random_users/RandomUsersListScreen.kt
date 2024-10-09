package dev.carlodips.albertsoncodingassignment.ui.random_users

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.carlodips.albertsoncodingassignment.R
import dev.carlodips.albertsoncodingassignment.model.data.RandomUser
import dev.carlodips.albertsoncodingassignment.ui.theme.AlbertsonCodingAssignmentTheme
import dev.carlodips.albertsoncodingassignment.utils.MockData
import kotlinx.coroutines.flow.StateFlow

sealed class RandomUsersListUIState {
    data object Loading : RandomUsersListUIState()
    data object Error : RandomUsersListUIState()
    class Success(
        val listUsers: SnapshotStateList<RandomUser>,
        val navigateToDetails: (result: RandomUser) -> Unit
    ) : RandomUsersListUIState()
}

@Composable
fun RandomUsersListScreen(
    modifier: Modifier = Modifier,
    uiState: StateFlow<RandomUsersListUIState>
) {
    val stateUiState = uiState.collectAsStateWithLifecycle(RandomUsersListUIState.Loading)

    Surface(modifier = modifier.fillMaxSize()) {
        Column() {
            Spacer(modifier = Modifier.height(24.dp))

            when (val currentUiState = stateUiState.value) {
                is RandomUsersListUIState.Success -> {
                    RandomUsersList(uiState = currentUiState)
                }

                RandomUsersListUIState.Error -> TODO()
                RandomUsersListUIState.Loading -> {
                    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier.width(32.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RandomUsersList(
    modifier: Modifier = Modifier,
    uiState: RandomUsersListUIState.Success
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(uiState.listUsers) { index, item ->
            RandomUserItem(user = item) {
                uiState.navigateToDetails.invoke(item)
            }
        }
    }
}

@Composable
fun RandomUserItem(
    modifier: Modifier = Modifier,
    user: RandomUser,
    onItemClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                onClick = onItemClick
            ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            AsyncImage(
                modifier = modifier
                    .size(64.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                model = user.picture.medium,
                placeholder = painterResource(id = R.drawable.ic_person),
                contentDescription = null
            )

            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = user.getDisplayName()
                )
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = user.getDisplayAddress()
                )
            }

            Column(
                modifier = modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = ""
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewResultItem() {
    AlbertsonCodingAssignmentTheme {
        val user = MockData.randomUser

        RandomUserItem(user = user, onItemClick = {})
    }
}