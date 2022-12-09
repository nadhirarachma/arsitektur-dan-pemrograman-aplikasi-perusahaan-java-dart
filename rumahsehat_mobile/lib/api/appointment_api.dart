import 'package:rumahsehat_mobile/api/request_helper.dart';

class AppointmentApi {
  Future<List<dynamic>> createAppointment() async {
    final response = await RequestHelper.post("/post-appointment", null);
    return response;
  }
}