import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/blocs/appointment_bloc/appointment_bloc.dart';
import 'package:rumahsehat_mobile/blocs/doctor_bloc/doctor_bloc.dart';
import 'package:rumahsehat_mobile/commons/extensions/date_time_extension.dart';
import 'package:rumahsehat_mobile/models/doctor_model.dart';
import 'package:rumahsehat_mobile/repositories/appointment_repository.dart';
import 'package:rumahsehat_mobile/repositories/doctor_repository.dart';
import 'package:rumahsehat_mobile/widget/appointment/dropdown.dart';
import 'package:rumahsehat_mobile/widget/blue_button.dart';

class CreateAppointmentScreen extends StatefulWidget {
  static const routeName = '/create-appointment';
  const CreateAppointmentScreen({Key? key}) : super(key: key);

  @override
  State<CreateAppointmentScreen> createState() =>
      _CreateAppointmentScreenState();
}

class _CreateAppointmentScreenState extends State<CreateAppointmentScreen> {
  List<DoctorModel> dropdownList = [];
  DoctorModel? selectedValue;
  DateTime? selectedDate;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 0,
        title: const Text(
          "Create Appointment",
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: MultiBlocProvider(
        providers: [
          BlocProvider<DoctorBloc>(
            create: (_) {
              final repository =
                  RepositoryProvider.of<DoctorRepository>(context);
              return DoctorBloc(repository)..add(FetchAllDoctors());
            },
          ),
          BlocProvider<AppointmentBloc>(
            create: (_) {
              final repository =
                  RepositoryProvider.of<AppointmentRepository>(context);
              return AppointmentBloc(repository);
            },
          ),
        ],
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: BlocConsumer<AppointmentBloc, AppointmentState>(
              listener: (context, state) {
                if (state is SuccessCreateAppointment) {
                  showDialog(
                    context: context,
                    builder: (context) => const AlertDialog(
                      title: Text("Sukses membuat appointment."),
                    ),
                  );
                } else if (state is FailCreateAppointment) {
                  showDialog(
                    context: context,
                    builder: (context) => const AlertDialog(
                      title: Text("Gagal membuat appointment."),
                    ),
                  );
                }
              },
              builder: (context, state) {
                if (state is AppointmentLoading) {
                  return const Center(child: CircularProgressIndicator());
                } else {
                  return BlocBuilder<DoctorBloc, DoctorState>(
                    builder: (context, state) {
                      if (state is SuccessFetchAllDoctors) {
                        dropdownList = state.listDoctors;
                        return Column(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            const Spacer(flex: 1),
                            CreateAppoinmentDropdown(
                              dropdownItems: dropdownList,
                              onChanged: (value) {
                                setState(() {
                                  selectedValue = value as DoctorModel;
                                });
                              },
                              selectedValue: selectedValue,
                            ),
                            const SizedBox(height: 16.0),
                            BlueButton(
                              onPressed: () {
                                DatePicker.showDateTimePicker(
                                  context,
                                  locale: LocaleType.id,
                                  minTime: DateTime.now(),
                                  onConfirm: (time) {
                                    setState(() {
                                      selectedDate = time;
                                    });
                                  },
                                );
                              },
                              text: selectedDate != null
                                  ? selectedDate!.displayDate
                                  : "Pilih tanggal",
                            ),
                            const Spacer(flex: 1),
                            BlueButton(
                              onPressed: () {
                                _createAppointment(context);
                              },
                              text: "Buat appointment",
                              backgroundColor: Colors.blue,
                              minWidth: 100,
                            ),
                          ],
                        );
                      } else {
                        return const Center(child: CircularProgressIndicator());
                      }
                    },
                  );
                }
              },
            ),
          ),
        ),
      ),
    );
  }

  void _createAppointment(BuildContext context) {
    Map<String, dynamic> data = {
      "tanggal": selectedDate!.toIso8601String(),
      "username": selectedValue!.username,
    };
    context.read<AppointmentBloc>().add(CreateAppointment(data));
  }
}
