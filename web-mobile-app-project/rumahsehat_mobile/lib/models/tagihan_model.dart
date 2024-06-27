class Tagihan {
  final String kode;
  final bool isPaid;
  final String tanggalTerbuat;
  final String tanggalBayar;
  final int jumlahTagihan;

  const Tagihan(
      {required this.kode,
      required this.isPaid,
      required this.tanggalTerbuat,
      required this.tanggalBayar,
      required this.jumlahTagihan});

  factory Tagihan.fromJson(Map<String, dynamic> json) {
    String tanggalBayar;
    if (json['tanggalBayar'] != null) {
      tanggalBayar = json['tanggalBayar'];
    } else {
      tanggalBayar = '-';
    }
    return Tagihan(
        kode: json['kode'],
        isPaid: json['isPaid'],
        tanggalTerbuat: json['tanggalTerbuat'],
        tanggalBayar: tanggalBayar,
        jumlahTagihan: json['jumlahTagihan']);
  }
}
