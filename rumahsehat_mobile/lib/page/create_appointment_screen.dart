import 'dart:developer';

import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/blocs/doctor_bloc/doctor_bloc.dart';
import 'package:rumahsehat_mobile/models/doctor_model.dart';
import 'package:rumahsehat_mobile/repositories/doctor_repository.dart';

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
          "Buat Appointment by Ajay",
          style: TextStyle(
            color: Colors.black,
          ),
        ),
      ),
      body: BlocProvider<DoctorBloc>(
        create: (_) {
          final repository = RepositoryProvider.of<DoctorRepository>(context);
          return DoctorBloc(repository)..add(FetchAllDoctors());
        },
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: BlocBuilder<DoctorBloc, DoctorState>(
              builder: (context, state) {
                if (state is SuccessFetchAllDoctors) {
                  dropdownList = state.listDoctors;
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      const Spacer(flex: 1),
                      DropdownButtonHideUnderline(
                        child: DropdownButton2(
                          isExpanded: true,
                          hint: Row(
                            children: const [
                              Spacer(flex: 1),
                              Text(
                                'Pilih dokter',
                                style: TextStyle(
                                  fontSize: 14,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.white,
                                ),
                                overflow: TextOverflow.ellipsis,
                              ),
                              SizedBox(
                                width: 8,
                              ),
                              Icon(
                                Icons.arrow_forward_ios_outlined,
                                color: Colors.white,
                                size: 14,
                              ),
                              Spacer(flex: 1),
                            ],
                          ),
                          items: dropdownList
                              .map((item) => DropdownMenuItem<DoctorModel>(
                                    value: item,
                                    child: Text(
                                      '${item.username} - ${item.tarif}',
                                      style: const TextStyle(
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold,
                                        color: Colors.white,
                                      ),
                                      overflow: TextOverflow.ellipsis,
                                    ),
                                  ))
                              .toList(),
                          value: selectedValue,
                          onChanged: (value) {
                            setState(() {
                              selectedValue = value as DoctorModel;
                            });
                          },
                          icon: const SizedBox(),
                          buttonHeight: 50,
                          buttonWidth: double.infinity,
                          buttonPadding:
                              const EdgeInsets.only(left: 14, right: 14),
                          buttonDecoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(8),
                            color: Colors.blue[200],
                          ),
                          buttonElevation: 0,
                          itemHeight: 40,
                          itemPadding:
                              const EdgeInsets.only(left: 14, right: 14),
                          dropdownMaxHeight: 200,
                          dropdownWidth: 200,
                          dropdownPadding: null,
                          dropdownDecoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(14),
                            color: Colors.blue[200],
                          ),
                          dropdownElevation: 8,
                          scrollbarRadius: const Radius.circular(40),
                          scrollbarThickness: 6,
                          scrollbarAlwaysShow: true,
                          offset: const Offset(0, 0),
                        ),
                      ),
                      const SizedBox(height: 16.0),
                      TextButton(
                        onPressed: () {
                          DatePicker.showDateTimePicker(
                            context,
                            locale: LocaleType.id,
                            onConfirm: (time) {
                              selectedDate = time;
                            },
                          );
                        },
                        style: ButtonStyle(
                          minimumSize: MaterialStateProperty.all(
                            const Size(double.infinity, 44.0),
                          ),
                          backgroundColor:
                              MaterialStateProperty.resolveWith<Color?>(
                            (states) {
                              return Colors.blue[200];
                            },
                          ),
                          shape: MaterialStateProperty.all<OutlinedBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                          ),
                        ),
                        child: const Text(
                          "Pilih tanggal",
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      ),
                      const Spacer(flex: 1),
                      TextButton(
                        onPressed: () {
                          _createAppointment();
                        },
                        style: ButtonStyle(
                          backgroundColor:
                              MaterialStateProperty.resolveWith<Color>(
                            (states) {
                              return Colors.blue;
                            },
                          ),
                          shape: MaterialStateProperty.all<OutlinedBorder>(
                            RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(8.0),
                            ),
                          ),
                        ),
                        child: const Text(
                          "Buat appointment",
                          style: TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      )
                    ],
                  );
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

  void _createAppointment() {
    log('$selectedValue');
    log('$selectedDate');
  }
}
