package com.plcoding.bluetoothchat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.bluetoothchat.domain.chat.BluetoothDevice
import com.plcoding.bluetoothchat.presentation.BluetoothUiState

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
    onStartServer: () -> Unit,
    onDeviceClick: (BluetoothDevice) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BluetoothDeviceList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = onDeviceClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = onStartScan,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Text(text = "Scanner",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Button(
                onClick = onStopScan,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Text(text = "Arreter Scan",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Button(
                onClick = onStartServer,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Text(
                    text = "Servir D'hote",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }
}

@Composable
fun BluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color(0xFFADD8E6)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Paired Devices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

        }
        items(pairedDevices) { device ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = device.name ?: "Unidentified Device",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClick(device) },
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "-------->"+device.address ?: "-------",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color(0xFFADD8E6)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Scanned Devices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        items(scannedDevices) { device ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = device.name ?: "Unidentified Device",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClick(device) },
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "-------->"+device.address ?: "-------",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}