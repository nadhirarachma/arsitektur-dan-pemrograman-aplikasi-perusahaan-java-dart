import 'package:rumahsehat_mobile/api/request_helper.dart';

class AppointmentApi {
  Future<String> createAppointment(Map<String,dynamic> data) async {
    final response = await RequestHelper.postWithToken("/post-appointment", data);
    return response;
  }

  Future<List<dynamic>> getAppointmentByPatient() async {
    final response = await RequestHelper.getWithToken("/get-appointment-pasien");
    return response;
  }
}