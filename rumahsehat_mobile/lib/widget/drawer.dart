import 'package:flutter/material.dart';
import 'package:rumahsehat_mobile/page/homepage.dart';
import 'package:rumahsehat_mobile/page/profile.dart';

class DrawerPage extends StatefulWidget {
  const DrawerPage({Key? key, required this.username}) : super(key: key);

  final String username;
  @override
  _DrawerState createState() => _DrawerState();
}

class _DrawerState extends State<DrawerPage> {
  @override
  Widget build(BuildContext context) {
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
            )
          ],
        ),
      ),
    );
  }
}
