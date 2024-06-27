import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/topup.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';

import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;

import '../models/pasien_model.dart';

Future<Pasien> fetchPasien(String username) async {
  String url = 'http://apap-087.cs.ui.ac.id/api/v1/pasien/profile/';
  // String url = 'http://localhost:10087/api/v1/pasien/profile/';
  // String url = 'http://localhost:8080/api/v1/pasien/profile/';
  final response = await http.get(Uri.parse(url + username));
  if (response.statusCode == 200) {
    return Pasien.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Gagal melihat profile');
  }
}

class Profile extends StatefulWidget {
  const Profile({Key? key, required this.username}) : super(key: key);

  final String username;
  @override
  State<Profile> createState() => _Profile();
}

class _Profile extends State<Profile> {
  late Future<Pasien> futurePasien;
  late Widget card;

  _getPasienProfile() async {
    futurePasien = fetchPasien(widget.username);
  }

  @override
  void initState() {
    super.initState();
    _getPasienProfile();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(title: const Text('Profile')),
        drawer: DrawerPage(username: widget.username),
        body: SingleChildScrollView(
            child: FutureBuilder<Pasien>(
          future: futurePasien,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              card = pasienCard(context, snapshot.data!);
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
                      TextButton(
                          onPressed: (() {
                            Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) =>
                                            Topup(username: widget.username)))
                                .then((value) {
                              setState(() {
                                _getPasienProfile();
                              });
                            });
                          }),
                          child: const Text('Top Up Saldo')),
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

Widget pasienCard(BuildContext context, Pasien pasien) {
  Widget card = Card(
    child: Column(
      children: [
        Text('username : ' + pasien.username),
        Text('nama : ' + pasien.nama),
        Text('email : ' + pasien.email),
        Text('saldo : ' + pasien.saldo.toString())
      ],
    ),
  );

  return card;
}
