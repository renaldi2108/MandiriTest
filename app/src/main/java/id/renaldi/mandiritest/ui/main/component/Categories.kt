package id.renaldi.mandiritest.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.renaldi.mandiritest.core.presentation.theme.MainWhiteColor
import id.renaldi.mandiritest.core.presentation.theme.Pink40
import id.renaldi.mandiritest.ui.main.HomeViewModel

@Composable
fun Categories(categories: List<String>, viewModel: HomeViewModel) {
    LazyRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(categories) { category ->
            Text(
                text = category,
                style = typography.body1.merge(),
                color = if (category == viewModel.selectedCategory.value) {
                    Color.White
                } else {
                    Color.Black
                },
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 8.dp,
                        ),
                    )
                    .clickable {
                        viewModel.setCategory(category)
                        viewModel.getProducts(viewModel.selectedCategory.value)
                    }
                    .background(
                        if (category == viewModel.selectedCategory.value) {
                            Pink40
                        } else {
                            MainWhiteColor
                        }
                    )
                    .padding(
                        10.dp
                    )
            )
        }
    }
}