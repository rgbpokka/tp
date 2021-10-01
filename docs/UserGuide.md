---
layout: page title: User Guide
---

**_Welcome to the Pocket Hotel User Guide!_**

**Pocket Hotel (PH)** is a specialized contact management desktop app targeted towards hotel managers who have many
guests and staff to handle.

**PH** provides a centralized location to store, organize and manage information linked to your guests and staff
members. **PH**
streamlines your workflow and is optimized for use via the Command Line Interface (CLI), whilst still embodying the
benefits of a Graphical User Interface (GUI).

This user guide serves as an entry point for users to get oriented with how **PH** operates and how you may utilize it
fully to integrate it within your hotel management system.

--------------------------------------------------------------------------------------------------------------------

## **Table of Contents**

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `PH.jar` from [here](https://github.com/AY2122S1-CS2103T-W12-3/tp/releases).

4. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

5. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe pn/X12345678F p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a guest
      named `John Doe` to the Address Book.

    * **`delete`**`pn/X12345678F` : Deletes the guest with passport number X12345678F.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Features**

**PH**’s features revolve around managing your guests and staff. For each command, a short description of its use is given
which is then followed by the format and a short example to help ensure that you have executed the command correctly.

A quick overview of all the commands can be found in the [command summary.](#command-summary)

Certain commands require parameters, which may have certain constraints. A quick overview of all the underlying
constraints can be found in the [parameter constraints.](#parameter-constraints-summary)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be entered by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameter prefixes such as `n/` and `pn/` are special keywords that indicate a start of a parameter.

* Fields with square brackets are optional.<br>
  e.g `n/NAME [p/PHONE_NUMBER]` can be used as `n/Bing Cheng p/99999999` or as `n/Bing Cheng`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME pn/PASSPORT_NUMBER`, `pn/PASSPORT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding guests/staff : `add`

Adds a new guest or staff and their contact details into **PH**.

Format:
<br>Guest: `add n/<NAME> pn/<PASSPORT_NUMBER> [p/<PHONE_NUMBER>] [r/<ROOM_NUMBER>]`
<br>Staff: `add n/<NAME> sid/<STAFF_ID> [p/<PHONE_NUMBER>]`

Example 1:
<br>![AddDiagram](images/AddDiagram.png)

* `list` command lists all contact details of people in the address book.
* `add n/Bing Cheng pn/T0134568D p/99999999 r/69` , adds a new guest, Bing Cheng to **PH** and shows the new
  contact list.

Example 2:
<br>![StaffAddDiagram](images/StaffAddDiagram.png)

* `list` command lists all contact details of people in the address book.
* `add n/Jeremy sid/321 p/87655432` , adds a new staff, Jeremy to **PH** and shows the new
  contact list.

[Back to Table of Contents](#table-of-contents)

### Editing fields of guests/staff: `edit`

Edit a guest or staff’s contact details by their passport number and staff id respectively. Only edits the fields that have been passed in as
parameters.

Format: 
<br>Guest: `edit pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`
<br>Staff: `edit sid/<STAFF_ID> <FIELD_NAME>/<NEW_FIELD_DETAILS>`

* Existing values will be updated to the input values.

Examples:

* `edit pn/X12345678A p/99999999` locates the guest Bing Cheng, by his passport number X12345678A and overwrites the phone number field with the new phone
  number provided.
* `edit pn/X98765432B r/123` locates the guest Jeremy, by his passport number X98765432B and overwrites the room number field with the new room number
  provided.

[Back to Table of Contents](#table-of-contents)

### Deleting guests/staff : `delete`

Deletes an existing guest or staff using the passport number or staff ID respectively.

Format:
<br>Guest: `delete pn/<PASSPORT_NUMBER>`
<br>Staff: `delete sid/<STAFF_ID>`


Example 1 (Delete guest):
<br>![GuestDeleteDiagram](images/GuestDeleteDiagram.png)

* `delete pn/XNOO19390 (PASSPORT_NUMBER)`, The guest, Jonny Jonny, who has passport number XNOO19390, is deleted from the system.

Example 2 (Delete staff):
<br>![StaffDeleteDiagram](images/StaffDeleteDiagram.png)
* `delete sid/123`, The staff, Thomas The Train, who has the staff ID 123, is deleted from the system.

[Back to Table of Contents](#table-of-contents)

### Viewing all your staff/guests: `list`

Shows a list of all people (staff and guests) found in **PH**.

* Contacts are not arranged in a particular order e.g staff contacts followed by guest contacts

Format: `list`

[Back to Table of Contents](#table-of-contents)

### Viewing a particular staff/guest: `view`

Views the staff or guest by their STAFF_ID or PASSPORT_NUMBER parameter. All the details associated with the 
staff/guest will be shown in the GUI.

Format:
<br>`view pn/<PASSPORT_NUMBER>`
<br>`view sid/<STAFF_ID>`

Example:

* `view pn/X12345678A` shows the details of the guest associated with the given passport number.
* `view sid/123` shows the details of the staff associated with the given staff ID.

[Back to Table of Contents](#table-of-contents)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[Back to Table of Contents](#table-of-contents)

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Saving your Data

Your data is saved automatically to the hard disk after every command you enter. The file is saved in `.json` format,
which allows you to edit the file manually without even booting up **PH**.

[Back to Table of Contents](#table-of-contents)

### Editing your data directly

Here’s a snippet of the editable text file in JSON that is found at:
`[JAR file location]/data/addressbook.json`

![JsonSnippet](images/JsonSnippet.png)

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing the json file directly should only be done by a user experienced with .json format files.
</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Command Summary**

Action | Format, Examples
--------|------------------
**Add** | Guest: `add n/<NAME> pn/<PASSPORT_NUMBER> [p/<PHONE_NUMBER>] [r/<ROOM_NUMBER>]`<br>Staff: `add n/<NAME> sid/<STAFF_ID> [p/<PHONE_NUMBER>] [r/<ROOM_NUMBER>]`<br>e.g.,<br>`add n/Bing Cheng pn/T0134568D p/99999999 r/69`<br>`add n/Jeremy sid/321 p/87655432`
**Edit** | Guest: `edit pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Staff:`edit pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>e.g.<br>`edit pn/X12345678A p/99999999`<br>`edit sid/S12345678A p/99999999`
**Delete** | Guest: `delete pn/<PASSPORT_NUMBER>`<br>Staff: `delete sid/<STAFF_ID>`<br>e.g.,<br>`delete pn/XNOO19390`<br>`delete sid/321`
**List** | `list`
**View** | Guest: `view pn/<PASSPORT_NUMBER>`<br>Staff: `view sid/<STAFF_ID>`<br>e.g.,<br>`view pn/X12345678A`<br>`view sid/123`
**Exit** | `exit`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Parameter Constraints Summary**

Parameter | Prefix | Constraints, Examples
----------|--------|-----------------------
**NAME** | `n/` | Blank inputs are not allowed, and should only contain alphanumeric characters. <br> e.g., `n/Bing Cheng`
**PASSPORT_NUMBER** | `pn/` | Blank inputs are not allowed, should not be used with `sid/` <br> e.g., `pn/X12345678A`
**STAFF_ID** | `sid/` |  Blank inputs are not allowed, not allowed to be used with `pn/` e.g., `sid/2131`
**PHONE_NUMBER** | `p/` | Local phone numbers are 8 digits long, and should start with 8 or 9. <br> e.g., `p/99999999`
**ROOM_NUMBER** | `r/` | Only room numbers that exist in the hotel should be used.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Glossary**

* **PH**: Acronym for Pocket Hotel
* **Guest**: A guest at the hotel
* **Staff**: An employee of the hotel

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app on your other computer and run it. Overwrite the empty .json file that is created with your old
.json file in your old computer.<br>

**Q**: How do I know if Java 11 is installed correctly on my computer?<br>
**A**: Open up your terminal and run `java --version`. The output should be java 11, if installed correctly.<br>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------


