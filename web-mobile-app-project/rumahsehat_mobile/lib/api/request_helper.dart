import 'dart:convert';
import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumahsehat_mobile/services/secure_storage.dart';

class RequestHelper {
  static final LocalStorageService _storageService = LocalStorageService();
  static const apiPath = "https://apap-087.cs.ui.ac.id/api/v1";

  static Future<dynamic> get(String path) async {
    final urlPath = '$apiPath$path';

    final response = await http.get(
      Uri.parse(urlPath),
    );

    return _responseHandler(response);
  }

  static Future<dynamic> getWithToken(String path) async {
    final token = await _storageService.readSecureData("token");
    final urlPath = '$apiPath$path';

    final response = await http.get(
      Uri.parse(urlPath),
      headers: {
        'Authorization': 'Bearer $token'
      },
    );
    return _responseHandler(response);
  }

  static Future<dynamic> postWithToken(
      String path, Map<String, dynamic>? body) async {
    final token = await _storageService.readSecureData("token");
    final urlPath = '$apiPath$path';

    final response = await http.post(
      Uri.parse(urlPath),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: json.encoder.convert(body),
    );

    return _responseHandler(response);
  }

  static _responseHandler(http.Response response) {
    int statusCode = response.statusCode;
    if (statusCode != 200) {
      throw ErrorDescription('Error $statusCode');
    }
    try {
      final decodedResponse = json.decode(response.body);
      return decodedResponse;
    } on FormatException catch (e) {
      log(e.toString());
      return response.body;
    }
  }
}
