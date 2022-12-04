import 'package:login_logout_registration/screens/login_screen.dart';
import 'package:login_logout_registration/utils/network_service.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:login_logout_registration/screens/welcome_screen.dart';

/// This is the main application widget.
void main() {
  runApp(MyApp());
}

// ignore: use_key_in_widget_constructors
class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return Provider(
      create: (_) {
        NetworkService request = NetworkService();
        return request;
      },
      child: MaterialApp(
        title: "RumahSehat",
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
            primaryColor: Colors.indigo, scaffoldBackgroundColor: Colors.white),
        home: const WelcomeScreen(),
        routes: {
          LoginScreen.routeName: (context) => const LoginScreen(),
          WelcomeScreen.routeName: (context) => const WelcomeScreen(),
        },
      ),
    );
  }
}