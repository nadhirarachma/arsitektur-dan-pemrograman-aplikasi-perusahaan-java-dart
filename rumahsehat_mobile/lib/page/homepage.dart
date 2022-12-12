import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/detail-resep.dart';
import 'package:rumahsehat_mobile/page/appointment_list_screen.dart';
import 'package:rumahsehat_mobile/page/create_appointment_screen.dart';
import 'package:rumahsehat_mobile/page/profile.dart';
import 'package:rumahsehat_mobile/page/tagihan_list.dart';
import 'package:rumahsehat_mobile/widget/drawer.dart';

class Home extends StatefulWidget {
  static const route = "/home";

  const Home({Key? key, required this.username}) : super(key: key);
  final String username;
  @override
  _Homepage createState() => _Homepage();
}

class _Homepage extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: const Color.fromRGBO(239, 248, 253, 0.9),
        appBar: AppBar(
          title: const Text('Rumah Sehat'),
        ),
        drawer: DrawerPage(
          username: widget.username,
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              GridView.count(
                  shrinkWrap: true,
                  primary: false,
                  padding: const EdgeInsets.all(20),
                  crossAxisCount: 3,
                  scrollDirection: Axis.vertical,
                  mainAxisSpacing: 5,
                  crossAxisSpacing: 5,
                  children: <Widget>[
                    Card(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12)),
                        elevation: 16,
                        margin: const EdgeInsets.all(9),
                        child: InkWell(
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) =>
                                      const CreateAppointmentScreen(),
                                ),
                              );
                            },
                            splashColor: Colors.lightBlue,
                            child: Center(
                              child: Column(
                                mainAxisSize: MainAxisSize.min,
                                children: const <Widget>[
                                  Icon(
                                    Icons.add,
                                    size: 25,
                                    color: Colors.blueGrey,
                                  ),
                                  Text(
                                    "Buat Appointment",
                                    softWrap: true,
                                    style: TextStyle(fontSize: 8),
                                  ),
                                ],
                              ),
                            ))),
                    Card(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12)),
                        elevation: 16,
                        margin: const EdgeInsets.all(9),
                        child: InkWell(
                            onTap: () {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const AppointmentListScreen()));
                            },
                            splashColor: Colors.lightBlue,
                            child: Center(
                              child: Column(
                                mainAxisSize: MainAxisSize.min,
                                children: const <Widget>[
                                  Icon(
                                    Icons.today,
                                    size: 25,
                                    color: Colors.blueGrey,
                                  ),
                                  Text(
                                    "Jadwal Appointment",
                                    softWrap: true,
                                    style: TextStyle(fontSize: 8),
                                  ),
                                ],
                              ),
                            ))),
                    GestureDetector(
                      onTap: () {
                        // Navigator.push(context,MaterialPageRoute(builder: (context) => Profile()));
                      },
                      child: Card(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12)),
                          elevation: 16,
                          margin: const EdgeInsets.all(9),
                          child: InkWell(
                              onTap: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => Profile(
                                              username: widget.username,
                                            )));
                              },
                              splashColor: Colors.lightBlue,
                              child: Center(
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: const <Widget>[
                                    Icon(
                                      Icons.account_circle,
                                      size: 25,
                                      color: Colors.blueGrey,
                                    ),
                                    Text(
                                      "Profile",
                                      softWrap: true,
                                      style: TextStyle(fontSize: 8),
                                    ),
                                  ],
                                ),
                              ))),
                    ),
                    GestureDetector(
                      onTap: () {
                        // Navigator.push(context,MaterialPageRoute(builder: (context) => Profile()));
                        //
                      },
                      child: Card(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12)),
                          elevation: 16,
                          margin: const EdgeInsets.all(9),
                          child: InkWell(
                              onTap: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => DetailResepPage(
                                              username: widget.username,
                                              id: 1,
                                            )));
                              },
                              splashColor: Colors.lightBlue,
                              child: Center(
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: const <Widget>[
                                    Icon(
                                      Icons.medication,
                                      size: 25,
                                      color: Colors.blueGrey,
                                    ),
                                    Text(
                                      "Detail Resep",
                                      softWrap: true,
                                      style: TextStyle(fontSize: 8),
                                    ),
                                  ],
                                ),
                              ))),
                    ),
                    GestureDetector(
                      onTap: () {
                        // Navigator.push(context,MaterialPageRoute(builder: (context) => Profile()));
                        //
                      },
                      child: Card(
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12)),
                          elevation: 16,
                          margin: const EdgeInsets.all(9),
                          child: InkWell(
                              onTap: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => TagihanList(
                                              username: widget.username,
                                            )));
                              },
                              splashColor: Colors.lightBlue,
                              child: Center(
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: const <Widget>[
                                    Icon(
                                      Icons.payment,
                                      size: 25,
                                      color: Colors.blueGrey,
                                    ),
                                    Text(
                                      "Daftar Tagihan",
                                      softWrap: true,
                                      style: TextStyle(fontSize: 8),
                                    ),
                                  ],
                                ),
                              ))),
                    ),
                  ])
            ],
          ),
        ));
  }
}
