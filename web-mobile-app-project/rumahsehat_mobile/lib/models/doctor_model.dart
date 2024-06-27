class DoctorModel {
  String id;
  String username;
  int tarif;

  DoctorModel({
    required this.id,
    required this.username,
    required this.tarif,
  });

  factory DoctorModel.fromJson(Map<String, dynamic> json) => DoctorModel(
        id: json["uuid"],
        username: json["username"],
        tarif: json["tarif"],
      );
}
