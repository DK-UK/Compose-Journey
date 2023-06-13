package com.example.myapplication.registrationDemo

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun registrationDemo() {
    var name by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }


    var hobby : HashSet<String> = HashSet()

    var hobbies = mutableMapOf<String, Boolean>(
        "coding" to true,
        "gaming" to false,
        "exploring" to false,
        "observing" to false
    )


    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 10.dp)
            .verticalScroll(state = ScrollState(0)),
    ) {
        Text(text = "Registration", style = MaterialTheme.typography.headlineMedium
            .copy(textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth())

        createTextField(label = "Name", onValueChanged = {
                                                         name = it
        },
            modifier = Modifier.fillMaxWidth())

        createTextField(label = "Number", onValueChanged = {
            number = it
        }, inputType = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())

        createTextField(label = "Email", onValueChanged = {
            email = it
        },
            modifier = Modifier.fillMaxWidth())

        createGenderField{
            gender = it
        }

        createHobbyField (hobbyList = hobbies)

        var registrationDetails by remember {
            mutableStateOf(false)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    registrationDetails = !registrationDetails
                }
            ) {
                Text(text = "Submit")
            }
        }

        if (registrationDetails) {
            Text(
                text = "Name : $name \n" +
                        "Number : $number \n" +
                        "Email : $email \n" +
                        "Gender : $gender \n" +
                        "Hobby : ${hobby}"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createTextField(
    modifier: Modifier = Modifier,
    value1: String = "",
    label: String,
    inputType: KeyboardOptions = KeyboardOptions.Default,
    onValueChanged: (String) -> Unit
) {
    var value by remember {
        mutableStateOf(value1)
    }

    OutlinedTextField(
        value = value, onValueChange = {
            value = it
            onValueChanged(it)
        },
        label = {
            Text(text = label)
        },
        keyboardOptions = inputType,
        modifier = modifier
    )
}

@Composable
fun createGenderField(
    modifier: Modifier = Modifier,
    onGenderSelected : (String) -> Unit
) {
    val genderList = mutableListOf("Male", "Female", "Other")
    var selectionOption by remember {
        mutableStateOf(genderList[0])
    }
    Column() {

        Text(text = "Gender", style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
            modifier = Modifier.padding(10.dp)

        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 8.dp)
        ) {
            genderList.forEach {
                RadioButton(selected = (it == selectionOption),
                    onClick = {
                        ->
                        selectionOption = it
                        onGenderSelected(it)
                    }
                )
                Text(text = it)
            }
        }
    }
}

@Composable
fun createHobbyField(
    hobbyList : MutableMap<String, Boolean>
) {

    var  (optionSelection, onHobbySelected) = remember {
        mutableStateOf("")
    }

    Column {

        Text(text = "Hobby", style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
            modifier = Modifier.padding(10.dp)

        )
        hobbyList.forEach {hobby->
            var checked by remember {
                mutableStateOf(false)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = {
                    checked = it
                })
                Text(text = hobby.key, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}