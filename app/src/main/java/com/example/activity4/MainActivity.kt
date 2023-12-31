package com.example.activity4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.activity4.Data.DataFrom
import com.example.activity4.Data.DataSource.hubungan
import com.example.activity4.Data.DataSource.jenis
import com.example.activity4.ui.theme.Activity4Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Activity4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Tampillayout()
                }
            }
        }
    }
}
@Composable
fun Tampillayout(
    modifier: Modifier = Modifier
){
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)

        ) {
            TampilForm()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaviewmodel: cobaviewmodel = viewModel()){
    var textname by remember { mutableStateOf("") }
    var texttlp by remember { mutableStateOf("") }
    var textemail by remember { mutableStateOf("") }
    var textalamat by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dataForm: DataFrom
    val uiState by cobaviewmodel.uiState.collectAsState()
    dataForm = uiState

   OutlinedTextField(
       value = textname ,
       singleLine = true,
       shape =  MaterialTheme.shapes.large,
       modifier = Modifier.fillMaxWidth(),
       label = {Text(text = "nama lengkap")},
       onValueChange = {
           textname = it
       } )

    OutlinedTextField(
        value = texttlp ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "telepon")},
        onValueChange = {
            texttlp = it
        })

    OutlinedTextField(
        value = textemail ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "email")},
        onValueChange = {
            textemail = it
        })

    Selectjk(
        options = jenis.map {id -> context.resources.getString(id)},
        onSelectionChange = {cobaviewmodel.setjenisk(it)}

    )

    Selecthubungan(
        options = hubungan.map { id -> context.resources.getString(id) },
        onSelectionChange = {cobaviewmodel.setstatus(it)})

    OutlinedTextField(
        value = textalamat ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "alamat")},
        onValueChange = {
            textalamat = it
        })

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            cobaviewmodel.insertData(textname, texttlp, textemail ,dataForm.status, dataForm.sex,textalamat)
        }
    ){
        Text(
            text = stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    TextHasil(namanya = cobaviewmodel.namausr, telponya = cobaviewmodel.noTLP,  jenisnya = cobaviewmodel.jeniskl,emailnya = cobaviewmodel.Email, statusnya = cobaviewmodel.status, alamatnya = cobaviewmodel.alamat )
}

@Composable
fun TextHasil(namanya:String, telponya: String, jenisnya:String,emailnya: String,statusnya:String,alamatnya:String)
{
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 150.dp
        ),
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "telepon :" + telponya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
         text = "jenis kelamin :" + jenisnya,
                 modifier = Modifier
                 .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "email :" + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "status :" + statusnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "alamat :" + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}
@Composable
fun Selectjk(
    options: List<String>,
    onSelectionChange: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(10.dp)) {
        options.forEach{ item ->
           Row (
               modifier = Modifier.selectable(
                   selected = selectedValue == item,
                   onClick = {
                       selectedValue = item
                       onSelectionChange(item)
                   }
               ),
               verticalAlignment = Alignment.CenterVertically
           ){
               RadioButton(selected = selectedValue == item,
                   onClick = {
                       selectedValue = item
                       onSelectionChange(item)
                   }
               )
               Text(text = item)
           }
        }
    }

}
@Composable
fun Selecthubungan(
    options: List<String>,
    onSelectionChange: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(10.dp)) {
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChange(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChange(item)
                    }
                )
                Text(text = item)
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Activity4Theme {
            Tampillayout()
    }
}