import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/homepage.dart';
import 'package:rumahsehat_mobile/page/profile.dart';
import 'package:login_logout_registration/screens/welcome_screen.dart';
import 'package:login_logout_registration/utils/network_service.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;

class DrawerPage extends StatefulWidget {
  const DrawerPage({Key? key, required this.username}) : super(key: key);

  final String username;
  @override
  _DrawerState createState() => _DrawerState();
}

class _DrawerState extends State<DrawerPage> {
  @override
  Widget build(BuildContext context) {
    //String url = 'http://apap-087.cs.ui.ac.id/api/v1/logout';
    String url = "http://localhost:8080/api/v1/logout";
    final request = context.watch<NetworkService>();
    return Drawer(
      child: SafeArea(
        child: Column(
          children: [
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => Home(
                              username: widget.username,
                            )));
              },
              child: const ListTile(
                leading: Icon(
                  Icons.home,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Home",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => Profile(
                              username: widget.username,
                            )));
              },
              child: const ListTile(
                leading: Icon(
                  Icons.account_circle,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Profile",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => Home(
                              username: widget.username,
                            )));
              },
              child: const ListTile(
                leading: Icon(
                  Icons.add,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Buat Appointment",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => Home(
                              username: widget.username,
                            )));
              },
              child: const ListTile(
                leading: Icon(
                  Icons.today,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Jadwal Appointment",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => Home(
                              username: widget.username,
                            )));
              },
              child: const ListTile(
                leading: Icon(
                  Icons.payment,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Tagihan",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
            GestureDetector(
              onTap: () async {
                var result = await http.post(
                  Uri.parse(url),
                  headers: <String, String>{
                    "Content-Type": "application/json; charset=UTF-8",
                    "Accept": "application/json"
                  },
                );

                if (result.body.substring(2, 7) == "token") {
                  showDialog<String>(
                      context: context,
                      builder: (BuildContext context) => AlertDialog(
                              title: const Text('Log Out Success'),
                              content: const Text('Successfully Logged Out!'),
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(30),
                              ),
                              backgroundColor: Colors.blue[200],
                              actions: <Widget>[
                                TextButton(
                                  onPressed: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (context) =>
                                                const WelcomeScreen()));
                                  },
                                  child: const Text(
                                    'OK',
                                    style: TextStyle(
                                      fontSize: 14.0,
                                      fontWeight: FontWeight.bold,
                                      color: Colors.indigo,
                                    ),
                                  ),
                                )
                              ]));
                } else {
                  showDialog<String>(
                    context: context,
                    builder: (BuildContext context) => AlertDialog(
                      title: const Text('Logout Failed'),
                      content: const Text('Failed to Logout'),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(30),
                      ),
                      backgroundColor: Colors.blue[200],
                      actions: <Widget>[
                        TextButton(
                          onPressed: () => Navigator.pop(context, 'OK'),
                          child: const Text('OK'),
                        ),
                      ],
                    ),
                  );
                }
              },
              child: const ListTile(
                leading: Icon(
                  Icons.logout,
                  size: 30,
                  color: Colors.black26,
                ),
                title: Text(
                  "Logout",
                  softWrap: true,
                  style: TextStyle(fontSize: 14, fontWeight: FontWeight.w200),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
