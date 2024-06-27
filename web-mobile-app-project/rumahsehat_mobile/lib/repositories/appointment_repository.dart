import 'package:rumahsehat_mobile/api/appointment_api.dart';
import 'package:rumahsehat_mobile/models/appointment_model.dart';

class AppointmentRepository {
  final AppointmentApi _appointmentApi;

  AppointmentRepository(this._appointmentApi);

  Future<String> createAppointment(Map<String, dynamic> data) async {
    return await _appointmentApi.createAppointment(data);
  }

  Future<List<AppointmentModel>> getAppointmentByPatient() async {
    final listAppointments = await _appointmentApi.getAppointmentByPatient();
    List<AppointmentModel> listAppointmentsConverted = [];
    for (var i = 0; i < listAppointments.length; i++) {
      listAppointmentsConverted
          .add(AppointmentModel.fromJson(listAppointments[i]));
    }
    return listAppointmentsConverted;
  }
}
