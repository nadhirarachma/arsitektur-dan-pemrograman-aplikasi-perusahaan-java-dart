import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumahsehat_mobile/blocs/appointment_bloc/appointment_bloc.dart';
import 'package:rumahsehat_mobile/commons/extensions/date_time_extension.dart';
import 'package:rumahsehat_mobile/models/appointment_model.dart';
import 'package:rumahsehat_mobile/page/appointment_detail_screen.dart';
import 'package:rumahsehat_mobile/repositories/appointment_repository.dart';

class AppointmentListScreen extends StatelessWidget {
  static const routeName = '/view-appointment';
  const AppointmentListScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 0,
        title: const Text(
          "List Appointment",
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: BlocProvider<AppointmentBloc>(
        create: (_) {
          final repository =
              RepositoryProvider.of<AppointmentRepository>(context);
          return AppointmentBloc(repository)..add(FetchPatientAppointments());
        },
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: BlocBuilder<AppointmentBloc, AppointmentState>(
              builder: (context, state) {
                if (state is SuccessFetchAppointmentsData) {
                  final listAppointment = state.listAppointments;
                  return ListView.builder(
                    itemCount: listAppointment.length,
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
                                return AppointmentDetailScreen(
                                  appointment: listAppointment[index],
                                );
                              }));
                            },
                            child:
                                _buildCard(listAppointment[index], index + 1),
                          ),
                        ),
                      );
                    }),
                  );
                } else if (state is FailFetchAppointmensData) {
                  return const Center(
                      child: Text("Tidak ada appointment untuk saat ini."));
                } else {
                  return const Center(child: CircularProgressIndicator());
                }
              },
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildCard(AppointmentModel data, int no) {
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
              Text(data.namaDokter),
              const SizedBox(height: 4),
              Text(data.waktuAwal.displayDate),
              const SizedBox(height: 4),
              Text(data.isDone ? "Selesai" : "Belum selesai"),
            ],
          ),
        ],
      ),
    );
  }
}
