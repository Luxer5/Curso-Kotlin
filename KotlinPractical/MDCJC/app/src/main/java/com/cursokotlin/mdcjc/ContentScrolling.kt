package com.cursokotlin.mdcjc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cursokotlin.mdcjc.ui.theme.MDCJCTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentPreview(){
    MDCJCTheme {
        Content()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Content(modifier: Modifier = Modifier){
    Column(modifier = modifier
         .verticalScroll(rememberScrollState())) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column {
                /*Image(painter = painterResource(id = R.drawable.ic_shop),
                    contentDescription = null,
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)))*/
                var urlValue by remember { mutableStateOf("") }
                GlideImage(model = urlValue,
                    contentDescription = null,
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.card_img_cover_height))
                        .background(colorResource(id = R.color.teal_200)),
                    contentScale = ContentScale.Crop)

                Text(text = stringResource(id = R.string.card_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_padding_default)),
                style = MaterialTheme.typography.h5)



                OutlinedTextField(value = urlValue,
                    onValueChange = { urlValue = it },
                    label = { Text(text = stringResource(id = R.string.card_input_url))},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.common_padding_default),
                            start = dimensionResource(id = R.dimen.common_padding_default),
                            end = dimensionResource(id = R.dimen.common_padding_default)
                        ))
                Text(text = stringResource(id = R.string.card_required),
                    style =  MaterialTheme.typography.caption, color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.common_padding_default), top = dimensionResource(id = R.dimen.common_padding_micro)))
            }
        }
    }
}