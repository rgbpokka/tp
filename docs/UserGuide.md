---
layout: page
title: User Guide
---

<h3><i>Welcome to the Pocket Hotel User Guide!</i></h3>

**Pocket Hotel (PH)** is a specialized contact management desktop app targeted towards hotel front office staff such as front desk staff who
frequently interact with guests.

**PH** provides a centralized location to store, organize and manage information linked to your guests and vendors employed by the hotel. **PH**
streamlines your workflow and is optimized for use via the _Command Line Interface_ (**CLI**), whilst still embodying
the benefits of a _Graphical User Interface_ (**GUI**).

This user guide serves as an entry point for users to get oriented with how **PH** operates and how you may utilize it
fully to integrate it within your hotel management system.

--------------------------------------------------------------------------------------------------------------------

## **Table of Contents**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**
1. Ensure you have Java `11` or above installed in your Computer ([Instructions on how to check Java version](#faq)).

2. Download the latest `PH.jar` from [here](https://github.com/AY2122S1-CS2103T-W12-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for **PH**.

4. To start the application, you may either:
    1. Double-click the `PH.jar` file to boot up the app.
    2. Open up your shell terminal in the directory where **PH** resides, and run the command `java -jar PH.jar`.
// TODO change the GUI picture
The **GUI** similar to the below should appear in a few seconds. Note how the app contains some sample data.
![Ui](images/Ui.png)
<p align="center"><i>Figure 1. Pocket Hotel GUI</i></p>
    // TODO why is markdown in bullet point 5 not rendered e.g **Text** is not bolded here
5. Type any command in the command box (Denoted by "Enter command here" text) and press Enter to execute it. e.g. typing `help` and pressing Enter will
   open the help window. Click the tab for the list that you would like to view e.g click vendor to view vendors in stored in **PH**<br>
   Some example commands you can try:

    * **`checkin`**`n/John Doe pn/X12345678F r/123 e/johnd@example.com t/Senior Citizen` : Adds a guest
      named `John Doe` to the Address Book.

    * **`deleteguest`**`pn/X12345678F` : Deletes the guest with passport number X12345678F.

    * **`clearguest`** : Deletes all guests from guest list.

    * **`addvendor`**

    * **`exit`** : Exits the app.

You may refer to the [features](#features) below for details of each command and to get familiarized with the syntax of
the commands.

--------------------------------------------------------------------------------------------------------------------


// TODO

## **Features**

**PH**’s features revolve around managing your guests and vendors. For each command, a short description of its use is
given which is then followed by the format and a short example to help ensure that you have executed the command
correctly.

A quick overview of all the commands can be found in the [command summary](#command-summary).

Certain commands require parameters, which may have constraints. A quick overview of all the underlying
// TODO Fix link
constraints can be found in the [parameter constraints.](#parameter-constraints-summary)

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be entered by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameter prefixes such as `n/` and `pn/` are special keywords that indicate a start of a parameter.

* Fields with square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/Bing Cheng t/VIP` or as `n/Bing Cheng`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME pn/PASSPORT_NUMBER`, `pn/PASSPORT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* All fields cannot be empty
* The tag field is optional and can be omitted entirely
</div>

// TODO add note: commands can be executed regardless of current list being viewed, e.g clearguest can be executed though the current list viewed is the vendor


[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Contacts in Pocket Hotel
There are 2 types of contacts in **PH**, guests and vendors. Guests represent guests of the hotel, and vendors represent external companies employed by a hotel for services hotel. Guest are identified by their `PASSPORT_NUMBER`
and Vendors are identified by their `VENDOR_ID`. These fields are their unique identifier, and no two contacts can have the same unique identifier.

It is possible for guests and vendors to have the same unique identifier for example, `VENDOR_ID` of a vendor is 111 and `PASSPORT_NUMBER` for a guest is 111, as they represent 2 different entities.

Guests and vendors have different parameters, which can be found in the [parameter constraints table](#parameter-constraints-summary) or in the command instructions.

### Navigating between guest and vendor list
// TODO

# Commands

## Manage Guests

### Checking in a new guest: `checkin`

Checks in a new **guest** by adding their contact details into **PH**.

Format:
<br>`checkin pn/<PASSPORT_NUMBER> n/<NAME> e/<EMAIL> r/<ROOM_NUMBER> [t/<TAG>]`

Example 1 (Add guest):
// TODO pictures do not show the accurate state of the app (readers want to see the real thing)
<br>![AddDiagram](images/AddDiagram.png)

* `list` command lists all contact details of people in the address book.
* `checkin n/Bing Cheng pn/T0134568D p/99999999 r/69` , adds a new guest, Bing Cheng to **PH** and shows the new contact
  list.

  
* `PASSPORT_NUMBER`: Should only contain alphanumeric characters.
* `EMAIL`: A valid email address should be used.
* `ROOM_NUMBER`: Only numbers greater than 0 are valid.<br>Example
* `TAG`: An optional field, more than one can be included in the command.

### Checking in a returning guest: `checkin`
Checks in a returning **guest** into **PH**, by retrieving their details from the archive.

Format:
<br>`checkin pn/<PASSPORT_NUMBER> r/<ROOM_NUMBER>`

* `ROOM_NUMBER`: Only numbers greater than 0 are valid.<br>Example

### Checking out a guest: `checkout`
Checks out a **guest** by archiving their details and generate an invoice of all the services used by the guest.

Format:
<br>`checkout pn/<PASSPORT_NUMBER>`

Example:

* `editguest pn/X12345678A` checks out the guest Bing Cheng, whose passport number is X12345678A.

### Editing fields of a guest : `editguest`

Edit a **guest**' contact details. Only edits the fields that have been passed in as parameters.

Format:
`editguest pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>

* Existing field values will be overwritten by the newly provided field values.

* You can edit more than one field at a time (See example below).

* Note that when changing a guest, it is important that there is no pre-existing
  guest with that passport number already.
</div>

* `PASSPORT_NUMBER`: Should only contain alphanumeric characters.
* `EMAIL`: A valid email address should be used.
* `ROOM_NUMBER`: Only numbers greater than 0 are valid.<br>Example
* `TAG`: An optional field, more than one can be included in the command.

Example:

* `editguest pn/X12345678A r/123` locates the guest Bing Cheng, by his passport number X12345678A and overwrites the
  room number field with the new room number provided.
  
* `editguest pn/X87654321A r/124 e/jj@mailer.com` locates the guest Jeremy, by his passport number X87654321A and overwrites the
    room number field with the new room number provided, and overwrites the old email field with the new email provided.

[Back to Table of Contents](#table-of-contents)

### Deleting a guest's details: `deleteguest`

//TODO is this an archived guest?
Deletes an existing **guest**'s contact details from **PH**.

Format:
<br>`deleteguest pn/<PASSPORT_NUMBER>`

Example:
`deleteguest pn/XNOO19390` deletes the guest who has passport number XNOO19390 from **PH**.

[Back to Table of Contents](#table-of-contents)

### Clear all checked in guests : `clearguest`

Deletes all guests that are currently checked in.

Format:
<br>`clearguest`

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* **Archived guests** are not deleted.
</div>

[Back to Table of Contents](#table-of-contents)

### Show all guests: `listguest`

Removes any filters and shows all guests checked into hotel.

Format:
<br>`listguest`

<div markdown="block" class="alert alert-info">
**:information_source: Notes:**<br>
* Contacts are not arranged in any particular order
</div>

[Back to Table of Contents](#table-of-contents)

### Charge a guest for a service: `chargeguest`

Charges a guest for a service offered by a vendor, and adds it to the invoice that will be generated upon checkout

Format:
<br>`chargeguest pn/<PASSPORT_NUMBER> vid/<VENDOR_ID>`

[Back to Table of Contents](#table-of-contents)

### Filter guest list: `filterguest`

Applies a filter to the guests who are checked in and displays them in the guest list.

Format:
`filterguest <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`

Example:
`filterguest n/boon`, guests with name, "boon ", will be filtered from **PH**.

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Using the filter command a second time, would clear the filters previously applied.
</div>

[Back to Table of Contents](#table-of-contents)

## Manage Vendors

### Adding a vendor: `addvendor`

Adds a vendors details to **PH**

Format:
<br>`addvendor vid/<VENDOR_ID> n/<NAME> e/<EMAIL> p/<PHONE_NUMBER> a/<ADDRESS> sn/<SERVICE_NAME> c/<SERVICE_COST> oh/<OPERATING HOURS> [t/TAG]`

Example:
<br>`addvendor vid/123 n/Wang's Satay e/satayMan@email.com p/84711231 a/Geylang Street 31 sn/Satay c/5 oh/1 0800-2000`

<div markdown="block" class="alert alert-info">
**:information_source: Note on OPERATING_HOUR format:**<br>
Format:
<br>`DAYS STARTTIME-ENDTIME`
<br>Monday is represented using a 1 and Sunday is represented by 7.<br>Example:<br>`1234567 0800-2359`: Monday to Sunday 8am to 11:59pm<br>`1321 0800-0900`: Monday to Wednesday 7am to 9am
</div>

* VENDOR_ID: Should only contain alphanumeric characters
* NAME: Should only contain alphabetical characters
* EMAIL: A valid email address should be used.
* PHONE_NUMBER: At least 3 digits long, should only contain numbers.
* SERVICE_NAME: Alphabetical characters and spaces are allowed.
* SERVICE_COST: Number greater than 0, will be rounded to 2 decimal places.
* TAG: An optional field, more than one can be included in each command.

[Back to Table of Contents](#table-of-contents)

### Editing fields of a vendor : `editvendor`

Edit a **vendors**' contact details. Only edits the fields that have been passed in as parameters.

Format:
<br>`editvendor vid/<VENDOR_ID> <FIELD_NAME>/<NEW_FIELD_DETAILS>`

Example:
<br>`edit vid/123 p/99999999 e/j@mailer.com` locates the vendor, Wang's Satay, with `VENDOR_ID` 123 and overwrites the phone number
  field with the new phone number provided, and the email field with the new email provided.

[Back to Table of Contents](#table-of-contents)

### Deleting a vendor's details: `deletevendor`

Deletes a vendors contact details from **PH**.

Format:
<br>`deletevendor vid/<VENDOR_ID>`

Example:
<br>`deletevendor vid/321`  deletes the vendor with `VENDOR_ID` 321 from **PH**.

[Back to Table of Contents](#table-of-contents)

### Clear vendor list : `clearvendor`
Deletes all vendors

Format:
<br>`clearvendor`

[Back to Table of Contents](#table-of-contents)

### Filter vendor list: `filtervendor`

Applies a filter to all the vendors and displays them in the vendor list.

Format:
`filtervendor <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`

Example:
`filtervendor sn/Food`, vendors with the `SERVICE_NAME` food will be filtered from **PH**

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
* Using the filter command a second time, would clear the filters previously applied.
</div>

[Back to Table of Contents](#table-of-contents)

## Utility Commands

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

[Back to Table of Contents](#table-of-contents)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Back to Table of Contents](#table-of-contents)

## Saving your Data

Your data is saved automatically to the hard disk after every command you enter. The file is saved in `.json` format,
which allows you to edit the file manually without even booting up **PH**.

[Back to Table of Contents](#table-of-contents)

### Editing your data directly

Here’s a snippet of the editable text file in JSON that is found at:
`[JAR file location]/data/addressbook.json`
<img src="images/JsonSnippet.png" width="300">

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Editing the json file directly should only be done by a user experienced with .json format files.
</div>

### Guest in archive data format

The archived guests would have the room number and services field emptied in the Json file as shown in the image above.
<br> Archived guest are also not shown in the UI.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Command Summary**

### Guest Command Summary
Action | Format, Examples
--------|------------------
**checkin(new guest)** | `checkin pn/<PASSPORT_NUMBER> n/<NAME> e/<EMAIL> r/<ROOM_NUMBER> [t/<TAG>]`<br>Example: `checkin pn/T0134568D n/Bing Cheng e/bingcheng@email.com r/101 t/VIP`
**checkin(returning guest)** | `checkin pn/<PASSPORT_NUMBER> r/<ROOM_NUMBER>`<br>Example: `checkin pn/T0134568D r/101`
**checkout** | `checkout pn/<PASSPORT_NUMBER>`<br>Example: `checkout pn/T0134568D`
**editguest** | `editguest pn/<PASSPORT_NUMBER> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Example: `edit pn/X12345678A p/99999999`
**deleteguest** | `deleteguest pn/<PASSPORT_NUMBER>`<br>Example: `deleteguest pn/T0134568D`
**clearguest** | `clearguest`
**listguest** | `listguest`
**filterguest** | `filterguest <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`<br>Example: `filterguest n/Bing t/VIP`
**chargeguest** | `chargeguest pn/<PASSPORT_NUMBER> vid/<VENDOR_ID>`<br>Example: `charge pn/T0134568D vid/3`

### Vendor Command Summary
Action | Format, Examples
--------|------------------
**addvendor** | `addvendor vid/<VENDOR_ID> n/<NAME> e/<EMAIL> p/<PHONE_NUMBER> a/<ADDRESS> sn/<SERVICE_NAME> c/<SERVICE_COST> oh/<OPERATING HOURS> [t/TAG]`<br>Example: `addvendor vid/123 n/Wang's Satay e/satayMan@email.com p/84711231 a/Geylang Street 31 sn/Satay c/5 oh/1 0800-2000` 
**editvendor** | `editvendor vid/<VENDOR_ID> <FIELD_NAME>/<NEW_FIELD_DETAILS>`<br>Example: `edit vid/111 sn/Laundry`
**deletevendor** | `deletevendor vid/<VENDOR_ID>`<br>Example: `deletevendor vid/112`
**clearvendor** | `clearvendor`
**listvendor** | `listvendor`
**filtervendor** | `filtervendor vid/<VENDOR_ID> <FILTER_FIELD_NAME>/<FILTER_FIELD_VALUE>`<br>Example: `filtervendor sn/Food t/satay`
**help** | `Help`
**exit** | `exit`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
# **Parameter Constraints Section**
## **Guest Parameter Constraints Summary**

Parameter | Prefix | Constraints, Examples
----------|--------|-----------------------
**PASSPORT_NUMBER** | `pn/` | Blank inputs are not allowed.<br>Should only contain alphanumeric characters.<br>Example: `pn/X12345678A`
**NAME** | `n/` | Blank inputs are not allowed.<br>Example: `n/Bing Cheng`
**EMAIL** | `e/` | Blanks inputs are not allowed.<br>A valid email address should be used.<br>Example: `e/BingCheng@email.com`
**ROOM_NUMBER** | `r/` | Blank inputs are not allowed.<br>Only numbers greater than 0 are valid.<br>Example: `r/500`
**TAG** | `t/` | Blank inputs are not allowed.<br>An optional field, more than one can be included in each command.<br>Example: `t/Vaccinated t/Vegetarian`

## **Vendor Parameter Constraints Summary**

Parameter | Prefix | Constraints, Examples
----------|--------|-----------------------
**VENDOR_ID** | `vid/` |  Blank inputs are not allowed.<br>Should only contain alphanumeric characters.<br>Example: `vid/2131`
**NAME** | `n/` | Blank inputs are not allowed.<br>Should only contain alphabetical characters.<br>Example: `n/Wang's Satay`
**EMAIL** | `e/` | Blanks inputs are not allowed.<br>A valid email address should be used.<br>Example: `e/satayMan@email.com`
**PHONE_NUMBER** | `p/` | Blank inputs are not allowed.<br>At least 3 digits long, should only contain numbers<br>Example: `p/84711231`
**ADDRESS** | `a/` | Blank inputs are not allowed.<br>Example: `a/Geylang Street 31`
**SERVICE_NAME** | `sn/` | Blank inputs are not allowed.<br>Alphabetical characters and spaces are allowed.<br>Example: `sn/Satay`
**SERVICE_COST** | `c/` | Blank inputs are not allowed.<br>Number greater than 0, will be rounded to 2 decimal places.<br>Example: `c/5`
**OPERATING_HOURS** | `oh/` | Blank inputs are not allowed.<br>Duplicates are allowed.<br>Format: `DAYS STARTTIME-ENDTIME`<br>Monday is represented using a 1 and Sunday is represented by 7.<br>Example:<br>`1234567 0800-2359`: Monday to Sunday 8am to 11:59pm<br>`1321 0800-0900`: Monday to Wednesday 7am to 9am
**TAG** | `t/` | Blank inputs are not allowed.<br>An optional field, more than one can be included in each command.<br>Example: `t/Halal t/Free flow`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Glossary**

* **PH**: Acronym for Pocket Hotel
* **CLI**: Command line interface
* **GUI**: Graphical user interface
* **Guest**: A guest at the hotel
* **Vendor**: An external entity that a hotel uses for services 
* **Unique Identifier**: An attribute that uniquely identifies a contact in the address book.
* **Archived Guests**: Guests that are not checked into the hotel and are not displayed, but whose details are stored in **PH**.
* **Unarchived guests**: Guests who are currently checked into **PH** and can be viewed in the list.

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


