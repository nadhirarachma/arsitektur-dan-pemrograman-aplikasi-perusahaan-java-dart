import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:login_logout_registration/components/button.dart';
import 'package:rumahsehat_mobile/models/pasien_model.dart';
import 'package:rumahsehat_mobile/page/profile.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';

import 'dart:async';
import 'dart:convert' as convert;
import 'package:http/http.dart' as http;

import '../models/tagihan_model.dart';

Future<Tagihan> fetchTagihan(String kode) async {
  String url = 'http://apap-087.cs.ui.ac.id/api/v1/tagihan/detail/';
  // String url = 'http://localhost:8080/api/v1/tagihan/detail/';
  final response = await http.get(Uri.parse(url + kode));
  if (response.statusCode == 200) {
    return Tagihan.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Gagal mendapatkan tagihan');
  }
}

class TagihanDetail extends StatefulWidget {
  const TagihanDetail({Key? key, required this.kode, required this.username})
      : super(key: key);

  final String kode;
  final String username;
  @override
  State<TagihanDetail> createState() => _TagihanDetail();
}

class _TagihanDetail extends State<TagihanDetail> {
  final String url = 'http://apap-087.cs.ui.ac.id/api/v1/tagihan/bayar';

  late Future<Tagihan> futureTagihan;
  late Future<Pasien> futurePasien;
  late Widget detail;

  _getTagihan() async {
    futureTagihan = fetchTagihan(widget.kode);
  }

  _getPasienProfile() async {
    futurePasien = fetchPasien(widget.username);
  }

  @override
  void initState() {
    super.initState();
    _getTagihan();
    _getPasienProfile();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(title: const Text('Detail Tagihan')),
        drawer: DrawerPage(username: widget.kode),
        body: SingleChildScrollView(
            child: FutureBuilder<Tagihan>(
          future: futureTagihan,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              Tagihan tagihan = snapshot.data!;
              return Column(
                children: [
                  TextButton(
                      onPressed: (() {
                        Navigator.pop(context);
                      }),
                      child: const Text('Kembali')),
                  Column(
                    children: [
                      Text("Kode: " + tagihan.kode),
                      Text("Tagihan: " + tagihan.jumlahTagihan.toString()),
                      Text("Status: " +
                          (tagihan.isPaid ? "Lunas" : "Belum lunas")),
                      Text("Tanggal dibuat: " + tagihan.tanggalTerbuat),
                      (tagihan.isPaid
                          ? Text("Tanggal dibayar: " + tagihan.tanggalBayar)
                          : Button(
                              text: "bayar",
                              onPressed: (() async {
                                var body = convert.jsonEncode(<String, dynamic>{
                                  'username': widget.username,
                                  'kode': tagihan.kode
                                });
                                var response = await http.post(Uri.parse(url),
                                    headers: <String, String>{
                                      'Content-type':
                                          'application/json; charset=utf-8'
                                    },
                                    body: body);
                                if (response.statusCode == 200) {
                                  setState(() {});
                                  showDialog(
                                      context: context,
                                      builder: ((context) => AlertDialog(
                                            title: const Text(
                                                'Pembayaran Berhasil'),
                                            content: const Text(
                                                'Tagihan berhasil dibayar'),
                                            actions: [
                                              TextButton(
                                                  onPressed: () {
                                                    Navigator.pop(context);
                                                  },
                                                  child: const Text('OK'))
                                            ],
                                          )));
                                } else {
                                  showDialog(
                                      context: context,
                                      builder: (context) => AlertDialog(
                                            title:
                                                const Text("Pembayaran Gagal"),
                                            content: const Text(
                                                "Saldo anda tidak mencukupi untuk membayar tagihan"),
                                            actions: [
                                              TextButton(
                                                  onPressed: (() {
                                                    Navigator.pop(context);
                                                  }),
                                                  child: const Text("OK"))
                                            ],
                                          ));
                                }
                              })))
                    ],
                    mainAxisAlignment: MainAxisAlignment.center,
                  ),
                ],
              );
            } else if (snapshot.hasError) {
              return Text('${snapshot.error}');
            }
            return const CircularProgressIndicator();
          },
        )));
  }
}
