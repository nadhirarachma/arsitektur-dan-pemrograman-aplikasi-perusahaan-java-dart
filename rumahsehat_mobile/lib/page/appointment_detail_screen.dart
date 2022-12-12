import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/commons/extensions/date_time_extension.dart';
import 'package:rumahsehat_mobile/models/appointment_model.dart';
import 'package:rumahsehat_mobile/widget/blue_button.dart';

class AppointmentDetailScreen extends StatelessWidget {
  static const routeName = '/detail-appointment';
  final AppointmentModel appointment;
  const AppointmentDetailScreen({
    Key? key,
    required this.appointment,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 0,
        title: const Text(
          "Detail Appointment",
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Material(
            borderRadius: BorderRadius.circular(8.0),
            elevation: 4,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              width: double.infinity,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(8.0),
                color: Colors.blue[50],
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 16),
                  const Text(
                    "Kode appointment",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    appointment.kode,
                  ),
                  const SizedBox(height: 16),
                  const Text(
                    "Waktu awal",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    appointment.waktuAwal.displayDate,
                  ),
                  const SizedBox(height: 16),
                  const Text(
                    "Status appointment",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    appointment.isDone ? "Selesai" : "Belum selesai",
                  ),
                  const SizedBox(height: 16),
                  const Text(
                    "Nama dokter",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    appointment.namaDokter,
                  ),
                  const SizedBox(height: 16),
                  const Text(
                    "Nama pasien",
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 8),
                  Text(
                    appointment.namaPasien,
                  ),
                  const SizedBox(height: 16),
                  BlueButton(
                    onPressed: appointment.idResep != null ? () {} : null,
                    minWidth: 100,
                    text: "Lihat resep",
                    backgroundColor:
                        appointment.isDone ? Colors.blue : Colors.grey[350],
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
