import 'package:flutter/material.dart';

class AppointmentDetailScreen extends StatelessWidget {
  static const routeName = '/detail-appointment';
  const AppointmentDetailScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 0,
        title: const Text(
          "Detail Appointment by Ajay",
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
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(8.0),
                color: Colors.blue[50],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
