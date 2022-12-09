import 'dart:convert' as convert;
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/topup.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';

Future<DetailResep> fetchResep(int id) async {
  // String url = 'http://apap-087.cs.ui.ac.id/api/v1/pasien/profile/';
  // String url = 'http://localhost:10087/api/v1/pasien/profile/';
  String url = 'http://localhost:8080/api/v1/resep/view/';
  // String url2 = 'http://localhost:8080/api/v1/resep/jumlah/';
  final response = await http.get(Uri.parse(url + id.toString()));
  print(response.statusCode);
  print(response.body);
  if (response.statusCode == 200) {
    return DetailResep.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Gagal melihat daftar resep');
  }
}

// Future<List<DaftarObat>> fetchObat(int id) async {
//   String url2 = 'http://localhost:8080/api/v1/resep/jumlah/';
//   final response = await http.get(Uri.parse(url2 + id.toString()));
//   print(response.statusCode);
//   print(response.body);
//   if (response.statusCode == 200) {
//     // return List<DaftarObat>.fromJson(jsonDecode(response.body));
//     Map<String, dynamic> jsonList = convert.jsonDecode(response.body);
//   } else {
//     throw Exception('Gagal melihat daftar resep');
//   }
// }

class DaftarObat {
  final String namaObat;
  final String jumlah;

  const DaftarObat(
      {required this.namaObat,
      required this.jumlah});

  factory DaftarObat.fromJson(Map<String, dynamic> json) {
    return DaftarObat(
        namaObat: json['obat'],
        jumlah: json['kuantitas']);
  }
}

class DetailResep {
  final int id;
  final String pasien;
  final String apoteker;
  final String dokter;
  final int totalObat;
  final String status;
  final List<DaftarObat> listObat;

  const DetailResep(
      {required this.id,
      required this.pasien,
      required this.apoteker,
      required this.dokter,
      required this.totalObat,
      required this.status,
      required this.listObat});

  factory DetailResep.fromJson(Map<String, dynamic> json) {
    String apoteker;
    if (json['apoteker'] != null) {
      apoteker = json['apoteker'];
    }
    else {
      apoteker = "";
    }
    // List<String> listObat = json['jumlah'];
    // // for (int = 0; i < json['ju) {

    // // }
    // List<DaftarObat> temp;

    // listObat.forEach((var i) {
    //   DaftarObat daftarObat = new DaftarObat(namaObat: i, jumlah: jumlah);
    // });

    return DetailResep(
        id: json['id'],
        pasien: json['appointment']['pasien']['username'],
        apoteker: apoteker,
        dokter: json['appointment']['dokter']['username'],
        totalObat: jsonDecode(json['jumlah']).length,
        status: json['isDone'] ? "Sudah Dikonfirmasi" : "Belum Dikonfirmasi",
        listObat: json['jumlah']);
  }
}

class Resep extends StatefulWidget {
  const Resep({Key? key, required this.username, required this.id}) : super(key: key);

  final String username;
  final int id;
  State<Resep> createState() => _Resep();
}

class _Resep extends State<Resep> {
  late Future<DetailResep> futureResep;
  late Widget card;

  _getDetailResep() async {
    futureResep = fetchResep(widget.id);
  }

  @override
  void initState() {
    super.initState();
    _getDetailResep();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(title: const Text('Detail Resep')),
        drawer: DrawerPage(username: widget.username),
        body: SingleChildScrollView(
            child: FutureBuilder<DetailResep>(
          future: futureResep,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              card = resepCard(context, snapshot.data!);
              return Column(
                children: [
                  card,
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      TextButton(
                          onPressed: (() {
                            Navigator.pop(context);
                          }),
                          child: const Text('Kembali')),
                    ],
                  ),
                ],
              );
              ;
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }
            return const CircularProgressIndicator();
          },
        )));
  }
}

Widget resepCard(BuildContext context, DetailResep resep) {
  Widget card = Card(
    child: Column(
      children: [
        Text('id: ' + resep.id.toString()),
        Text('pasien : ' + resep.pasien),
        Text('apoteker : ' + resep.apoteker),
        Text('dokter : ' + resep.dokter),
        Text('total obat : ' + resep.totalObat.toString()),
        Text('status : ' + resep.status),
      ],
    ),
  );

  return card;
}

// import 'dart:async';
// import 'dart:convert';

// import 'package:flutter/material.dart';
// import 'package:http/http.dart' as http;


// class DaftarObat {
//   final String namaObat;
//   final String jumlah;

//   const DaftarObat(
//       {required this.namaObat,
//       required this.jumlah});

//   factory DaftarObat.fromJson(Map<String, dynamic> json) {
//     return DaftarObat(
//         namaObat: json['obat'],
//         jumlah: json['kuantitas']);
//   }
// }

// class Detail {
//   final int id;
//   final String pasien;
//   final String apoteker;
//   final String dokter;
//   final int totalObat;
//   final String status;
//   final List<DaftarObat> listObat;

//   const Detail(
//       {required this.id,
//       required this.pasien,
//       required this.apoteker,
//       required this.dokter,
//       required this.totalObat,
//       required this.status,
//       required this.listObat});

//   factory Detail.fromJson(Map<String, dynamic> json) {
//     return Detail(
//         id: json['id'],
//         pasien: json['appointment']['pasien']['username'],
//         apoteker: json['apoteker'],
//         dokter: json['appointment']['dokter']['username'],
//         totalObat: jsonDecode(json['jumlah']).length,
//         status: json['isDone'] ? "Sudah Dikonfirmasi" : "Belum Dikonfirmasi",
//         listObat: json['jumlah']);
//   }
// }

// Future<Detail> fetchDetailResep(int id) async {
//   final response = await http.get(
//       Uri.parse('http://localhost:8080/api/v1/resep/view/${id}'));

//   if (response.statusCode == 200) {
//     // If the server did return a 200 OK response,
//     // then parse the JSON.
//     Detail detailResep = Detail.fromJson(json.decode(response.body));
//     return detailResep;
//   } else {
//     // If the server did not return a 200 OK response,
//     // then throw an exception.
//     throw Exception('Failed to load appointment');
//   }
// }

// class DetailResep extends StatefulWidget {

//   int id;
//   DetailResep({super.key, required this.id});

//   @override
//   // ignore: library_private_types_in_public_api
//   _DetailResepState createState() => _DetailResepState();
// }

// class _DetailResepState extends State<DetailResep> {
//   late Future<Detail> resep;

//   @override
//   void initState() {
//     super.initState();
//     resep = fetchDetailResep(widget.id);
//   }

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: const Text("Detail Resep"),
//       ),
//       body: FutureBuilder(
//         future: resep,
//         builder: (context, snapshot) {
//           if (!snapshot.hasData) {
//             return const Center(child: CircularProgressIndicator());
//           }

//           if (snapshot.hasData) {
//             return GridView.count(
//               primary: false,
//               padding: const EdgeInsets.all(20),
//               crossAxisSpacing: 10,
//               mainAxisSpacing: 10,
//               crossAxisCount: 2,
//               children: <Widget>[
//                 Container(
//                     padding: const EdgeInsets.all(8),
//                     child: Text("ID: ${snapshot.data!.id}")),
//                 Container(
//                     padding: const EdgeInsets.all(8),
//                     child: Text("Waktu Awal: ${snapshot.data!.}")),
//                 Container(
//                     padding: const EdgeInsets.all(8),
//                     child: Text("Status: ${snapshot.data!.status}")),
//                 Container(
//                     padding: const EdgeInsets.all(8),
//                     child: Text("Nama Dokter: ${snapshot.data!.namaDokter}")),
//                 Container(
//                     padding: const EdgeInsets.all(8),
//                     child: Text("Nama Pasien: ${snapshot.data!.namaPasien}")),
//               ],
//             );
//           }

//           return const Center();
//         },
//       ),
//     );
//   }
// }
