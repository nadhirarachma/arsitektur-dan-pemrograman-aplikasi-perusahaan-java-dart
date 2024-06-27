import 'package:intl/intl.dart';

extension DateTimeExtension on DateTime {
  String get displayDate {
    final DateFormat dateFormatter = DateFormat(
      'd MMMM y', 'id'
    );
    final DateFormat timeFormatter = DateFormat('HH:mm', 'id');

    return '${dateFormatter.format(this)} pukul ${timeFormatter.format(this)}';
  }
}
