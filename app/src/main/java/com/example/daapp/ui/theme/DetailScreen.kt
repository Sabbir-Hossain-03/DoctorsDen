package com.example.daapp.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.daapp.R
import com.example.daapp.model.DoctorModel


@Composable
fun DetailScreen(item: DoctorModel, onBackClick: () -> Unit, onMakeAppointmentClick: () -> Unit){
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(
                    color = colorResource(id = R.color.green),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))

        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(160.dp))

                    Text(
                        text = item.Name,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.Special,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                AsyncImage(
                    model = item.Picture,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(top=80.dp),
                    contentScale = ContentScale.Crop
                    )


                }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
            ){
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onBackClick() }
                        .padding(start = 12.dp)

                )
                Text(
                    text = "Appointment",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
                    .background(Color(0x40000000),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                .padding(all=16.dp)
            ){
                Row{
                    Image(
                        painter = painterResource(R.drawable.calendar),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(modifier = Modifier.padding(start=8.dp),
                        text = item.Date,
                        color = Color.White,
                        )

                }

                Spacer(modifier = Modifier.height(16.dp))
                Row{
                    Image(
                        painter = painterResource(R.drawable.clock),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(modifier = Modifier.padding(start=8.dp),
                        text = item.Time,
                        color = Color.White
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(all = 16.dp)){
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(R.drawable.location),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.Address,
                    color = colorResource(id = R.color.green),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                Column(modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Patiens")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.Patiens,
                        color = colorResource(id = R.color.green),
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(Color.LightGray)
                )
                Column(modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Expriense")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${item.Expriense} Years",
                        color = colorResource(id = R.color.green),
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(modifier = Modifier
                    .width(1.dp)
                    .height(50.dp)
                    .background(Color.LightGray)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Rating")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = item.Rating.toString(),
                            color = colorResource(id = R.color.green),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Biography",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.Biography,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionItem(
                    iconId = R.drawable.website,
                    title = "Website",
                    onClick = {
                        val i = Intent(Intent.ACTION_VIEW)
                        i.setData(Uri.parse(item.Site))
                        context.startActivity(i)


                    })
                ActionItem(
                    iconId = R.drawable.message,
                    title = "Message",
                    onClick = {
                        val uri=Uri.parse("smsto:${item.Mobile}")
                        val intent=Intent(Intent.ACTION_SENDTO,uri)
                        intent.putExtra("sms_body","the sms text")
                        context.startActivity(intent)


                    })
                ActionItem(
                    iconId = R.drawable.call,
                    title = "Call",
                    onClick = {
                        val uri="tel:"+item.Mobile.trim()
                        val intent=Intent(Intent.ACTION_DIAL,Uri.parse(uri))
                        context.startActivity(intent)


                    })
                ActionItem(
                    iconId = R.drawable.direction,
                    title = "Direction",
                    onClick = {
                        val intent=Intent(Intent.ACTION_VIEW, Uri.parse(item.Location))
                        context.startActivity(intent)


                    })
                ActionItem(
                    iconId = R.drawable.share,
                    title = "Share",
                    onClick = {
                        val intent=Intent(Intent.ACTION_SEND)
                        intent.setType("text/plain")
                        intent.putExtra(Intent.EXTRA_SUBJECT,item.Name)
                        intent.putExtra(Intent.EXTRA_TEXT,
                            item.Name + " " + item.Address + " " + item.Mobile)
                        context.startActivity(Intent.createChooser(intent,"Share Via"))
                    })

            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onMakeAppointmentClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                shape = RoundedCornerShape(size = 12.dp)
            ) {
                Text(
                    text = "Make Appointment",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ActionItem(iconId: Int, title: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(Color(0xffe8f5e9), shape = RoundedCornerShape(size = 50.dp))
                .padding(all = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DAappTheme {
        DetailScreen(
            item = DoctorModel(
                Name = "Dr. Sabbir Hossain",
                Special = "Cardiologist",
                Rating = 4.8,
                Expriense = 10,
                Cost = "$100",
                Patiens = "100",
                Date = "18/05/2026",
                Time = "10:00 AM",
                Address = "123 Hospital",
                Biography = "Dr. Sabbir Hossain is a cardiologist with 10 years of experience.",
            ),
            onBackClick = {},
            onMakeAppointmentClick = {}
        )
    }
}