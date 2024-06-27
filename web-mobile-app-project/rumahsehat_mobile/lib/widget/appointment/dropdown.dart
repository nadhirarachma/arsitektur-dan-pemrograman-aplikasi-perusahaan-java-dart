import 'package:dropdown_button2/dropdown_button2.dart';
import "package:flutter/material.dart";
import 'package:rumahsehat_mobile/models/doctor_model.dart';

class CreateAppoinmentDropdown extends StatelessWidget {
  final Function(DoctorModel?)? onChanged;
  final DoctorModel? selectedValue;
  final List<DoctorModel> dropdownItems;
  const CreateAppoinmentDropdown({
    Key? key,
    this.onChanged,
    this.selectedValue,
    required this.dropdownItems,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return DropdownButtonHideUnderline(
      child: DropdownButton2(
        isExpanded: true,
        hint: Row(
          children: const [
            Spacer(flex: 1),
            Text(
              'Pilih dokter',
              style: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.bold,
                color: Colors.white,
              ),
              overflow: TextOverflow.ellipsis,
            ),
            SizedBox(
              width: 8,
            ),
            Icon(
              Icons.arrow_forward_ios_outlined,
              color: Colors.white,
              size: 14,
            ),
            Spacer(flex: 1),
          ],
        ),
        items: dropdownItems
            .map((item) => DropdownMenuItem<DoctorModel>(
                  value: item,
                  child: Text(
                    '${item.username} - ${item.tarif}',
                    style: const TextStyle(
                      fontSize: 14,
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                    overflow: TextOverflow.ellipsis,
                  ),
                ))
            .toList(),
        value: selectedValue,
        onChanged: onChanged,
        icon: const SizedBox(),
        buttonHeight: 50,
        buttonWidth: double.infinity,
        buttonPadding: const EdgeInsets.only(left: 14, right: 14),
        buttonDecoration: BoxDecoration(
          borderRadius: BorderRadius.circular(8),
          color: Colors.blue[200],
        ),
        buttonElevation: 0,
        itemHeight: 40,
        itemPadding: const EdgeInsets.only(left: 14, right: 14),
        dropdownMaxHeight: 200,
        dropdownWidth: 200,
        dropdownPadding: null,
        dropdownDecoration: BoxDecoration(
          borderRadius: BorderRadius.circular(14),
          color: Colors.blue[200],
        ),
        dropdownElevation: 8,
        scrollbarRadius: const Radius.circular(40),
        scrollbarThickness: 6,
        scrollbarAlwaysShow: true,
        offset: const Offset(0, 0),
      ),
    );
  }
}
