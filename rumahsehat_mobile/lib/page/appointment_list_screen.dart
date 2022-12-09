import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/appointment_detail_screen.dart';

class AppointmentListScreen extends StatelessWidget {
  static const routeName = '/view-appointment';
  const AppointmentListScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Dummy data.
    List<Map<String, String>> _dummyData = [
      {
        "nama": "Ajay",
        "waktu_awal": "18 November 2022 12:00",
        "status": "Selesai",
      },
      {
        "nama": "Ajay",
        "waktu_awal": "18 November 2022 12:00",
        "status": "Selesai",
      },
      {
        "nama": "Ajay",
        "waktu_awal": "18 November 2022 12:00",
        "status": "Selesai",
      },
    ];

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 0,
        title: const Text(
          "Lihat Appointment by Ajay",
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: ListView.builder(
            itemCount: _dummyData.length,
            itemBuilder: ((context, index) {
              return Padding(
                padding: const EdgeInsets.symmetric(vertical: 8.0),
                child: Material(
                  borderRadius: BorderRadius.circular(8.0),
                  color: Colors.blue[100],
                  child: InkWell(
                    onTap: () {
                      Navigator.push(context,
                          MaterialPageRoute(builder: (context) {
                        return const AppointmentDetailScreen();
                      }));
                    },
                    child: _buildCard(_dummyData[index], index + 1),
                  ),
                ),
              );
            }),
          ),
        ),
      ),
    );
  }

  Widget _buildCard(Map<String, String> data, int no) {
    return Container(
      padding: const EdgeInsets.all(10),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8.0),
      ),
      child: Row(
        children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              Text("No"),
              SizedBox(height: 4),
              Text("Nama Dokter"),
              SizedBox(height: 4),
              Text("Waktu Awal"),
              SizedBox(height: 4),
              Text("Status"),
            ],
          ),
          const SizedBox(width: 4),
          Column(
            children: const [
              Text(":"),
              SizedBox(height: 4),
              Text(":"),
              SizedBox(height: 4),
              Text(":"),
              SizedBox(height: 4),
              Text(":"),
            ],
          ),
          const SizedBox(width: 4),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(no.toString()),
              const SizedBox(height: 4),
              Text(data["nama"]!),
              const SizedBox(height: 4),
              Text(data["waktu_awal"]!),
              const SizedBox(height: 4),
              Text(data["status"]!),
            ],
          ),
        ],
      ),
    );
  }
}
