Software Tools

Group Project -- Part 1

LiftFlow 

System For Fundrisers 

Submitted By: T00257571 T00258226 T00258294 T00258326 

 

Computing with Software Development, Computer Systems and Networking 

 

Date Submitted: dd/mm/yyyy 

 

**Table of Contents**

[1. Introduction/overview
[3](#introductionoverview)](#introductionoverview)

[2. Functional Components
[3](#functional-components)](#functional-components)

[3. User Requirements [4](#user-requirements)](#user-requirements)

[3.1. LiftFlow will manage User
[5](#liftflow-will-manage-user)](#liftflow-will-manage-user)

[3.2. LiftFlow will manage Donation Jar
[5](#liftflow-will-manage-donation-jar)](#liftflow-will-manage-donation-jar)

[3.3. LiftFlow will manage Donation
[5](#liftflow-will-manage-donation)](#liftflow-will-manage-donation)

[3.4. LiftFlow will perform administrative reporting
[5](#liftflow-will-perform-administrative-reporting)](#liftflow-will-perform-administrative-reporting)

[4. System Requirements [5](#system-requirements)](#system-requirements)

[4.1. System Level Use Case Diagram
[6](#system-level-use-case-diagram)](#system-level-use-case-diagram)

[4.2. Manage Users [6](#manage-users)](#manage-users)

[4.2.1. Add User [7](#add-user)](#add-user)

[4.2.2. Update User [9](#update-user)](#update-user)

[4.2.3. Remove User [11](#remove-user)](#remove-user)

[4.2.4. Upgrade to Fundraiser
[13](#upgrade-to-fundraiser)](#upgrade-to-fundraiser)

[4.3. Manage Donation Jar Idea
[15](#manage-donation-jar-idea)](#manage-donation-jar-idea)

[4.3.1. Add Donation Jar
[15](#create-donation-jar)](#create-donation-jar)

[4.3.2. Update Donation Jar
[18](#update-donation-jar)](#update-donation-jar)

[4.3.3. Close Donation Jar
[20](#close-donation-jar)](#close-donation-jar)

[4.3.4. Withdraw money [22](#withdraw-money)](#withdraw-money)

[4.4. Manage Donations [25](#manage-donations)](#manage-donations)

[4.4.1. Pay Donation [25](#pay-donation)](#pay-donation)

[4.4.2. Return Donation [27](#return-donation)](#return-donation)

[4.5. Manage Revenue Analysis
[30](#manage-revenue-analysis)](#manage-revenue-analysis)

[4.5.1. Yearly Revenue Analysis by Month
[30](#yearly-revenue-analysis-by-month)](#yearly-revenue-analysis-by-month)

[4.5.2. Yearly Donation Type Analysis
[32](#yearly-donation-type-analysis)](#yearly-donation-type-analysis)

[5. Data Model (UML Class Diagram)
[34](#data-model-uml-class-diagram)](#data-model-uml-class-diagram)

[5.1. Class Diagram [35](#class-diagram)](#class-diagram)

[5.2. Relational Schema [35](#relational-schema)](#relational-schema)

[5.3. Database Schema [36](#database-schema)](#database-schema)

[6. Conclusion [38](#conclusion)](#conclusion)

# Introduction/overview

> \"LiftFlow\" is a comprehensive, transparent, and user-friendly
> desktop application designed to connect volunteers, donors, and
> charitable organizations from various industries. The platform
> facilitates the collection of donations (one-time or
> subscription-based) for different causes, ensuring full visibility and
> accountability of the funds. The core objective is to encourage trust
> among donors by eliminating fraud and providing verifiable proof of
> impact through visual content posted by volunteers. This document
> describes the key requirements, functionality, and structure of the
> Donation system.  The LiftFlow system is done according to the modular
> principle, ensuring the flexibility and scalability of the system.
> LiftFlow consists of four modules: \"User Management\", \"Idea Jar
> Management\", \"Donation Management\" and \"Administration\". Each
> module includes well-defined usage scenarios such as adding, updating,
> removing and analysing yearly revenue or performance by category. The
> document also has technical aspects such as database schemas and UML
> class diagrams. 

# 

# Functional Components

![](/media/imageb.png){width="6.000520559930009in"
height="3.526971784776903in"}

# User Requirements

## LiftFlow will manage User

1.  LiftFlow will add a user

2.  LiftFlow will update a user

3.  LiftFlow will upgrade to fundraiser

4.  LiftFlow will remove user

## LiftFlow will manage Donation Jar

5.  LiftFlow will create donation jar

6.  LiftFlow will update donation jar

7.  LiftFlow will withdraw money from donation jar

8.  LiftFlow will close donation jar

## LiftFlow will manage Donation

9.  LiftFlow will process donation

10. LiftFlow will process refund

## LiftFlow will perform administrative reporting

11. LiftFlow will produce a yearly revenue analysis by month

12. LiftFlow will produce a yearly revenue type analysis

# System Requirements

## System Level Use Case Diagram

> The following system level use case diagram illustrates the high-level
> system requirements.
>
> ![A diagram of a volunteer donation platform AI-generated content may
> be incorrect.](media/image2.png){width="4.197916666666667in"
> height="3.9375in"}

## Manage Users

### Add User

![](/media/imagec.png){width="6.177083333333333in"
height="2.0416666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Add User               |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 1                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | User (Donor /          |                         |
|                  | Fundraiser)            |                         |
+------------------+------------------------+-------------------------+
| **Primary        | User                   |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | Admin                  |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A new user registers   |                         |
|                  | in the system by       |                         |
|                  | providing personal     |                         |
|                  | details. The account   |                         |
|                  | is stored in the Users |                         |
|                  | table and requires     |                         |
|                  | email/phone            |                         |
|                  | verification.          |                         |
+------------------+------------------------+-------------------------+
| **               | Registration page      |                         |
| Preconditions**  | available; user not    |                         |
|                  | already registered;    |                         |
|                  | system online.         |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      |  User clicks "Sign     |                         |
|                  | Up."                   |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **User**               | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Opens      | **Step 2:** Displays    |
|                  | registration page.     | registration form.      |
|                  |                        |                         |
|                  |                        | ** **                   |
|                  |                        |                         |
|                  | **Step 3:** Enters     | **Step 4:** Validates   |
|                  | details (username,     | input (unique           |
|                  | email, password,       | email/username,         |
|                  | etc.).                 | password strength).     |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 5:** Submits    | **Step 6:** Creates     |
|                  | form.                  | user record in Users    |
|                  |                        | with status=ACTIVE,     |
|                  |                        | email_verified=false.   |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7**: Sends       |
|                  |                        | verification            |
|                  |                        | email/OTP.              |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8:** Displays    |
|                  |                        | message "Check your     |
|                  |                        | email".                 |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 9:** Click on a |                         |
|                  | link in the email.     |                         |
|                  |                        | **Step 10:** Set        |
|                  |                        | email_verified=true.    |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 11:** Display    |
|                  |                        | confirmation message.   |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 12:** Show home  |
|                  |                        | page                    |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Alternate      | **User**               | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     |                        | **Step 4:** Validation  |
| entered          |                        | test fails.             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3.                 |
+------------------+------------------------+-------------------------+
| Duplicate        |                        | System prevents         |
| email/username   |                        | registration.           |
+------------------+------------------------+-------------------------+
| Email service    |                        | System saves user but   |
| unavailable      |                        | retries verification    |
|                  |                        | email.                  |
+------------------+------------------------+-------------------------+
| **Conclusions**  | New user created.      |                         |
+------------------+------------------------+-------------------------+
| **Post           | User record exists in  |                         |
| conditions**     | database with verified |                         |
|                  | email.                 |                         |
+------------------+------------------------+-------------------------+
| **Business       | Passwords must be      |                         |
| Rules**          | hashed, unique email   |                         |
|                  | required, role         |                         |
|                  | defaults to Donor      |                         |
|                  | unless specified.      |                         |
+------------------+------------------------+-------------------------+
| **Implementation | Security compliance    |                         |
| Constraints**    | (e.g., password        |                         |
|                  | hashing, CAPTCHA).     |                         |
+------------------+------------------------+-------------------------+

### Update User

![](/media/imaged.png){width="6.375in" height="2.0in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Update User            |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 2                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | User                   |                         |
+------------------+------------------------+-------------------------+
| **Primary        | User                   |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | System                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A registered user      |                         |
|                  | updates their profile  |                         |
|                  | information (e.g.,     |                         |
|                  | name, phone,           |                         |
|                  | password). Changes are |                         |
|                  | validated and stored   |                         |
|                  | in the Users table.    |                         |
+------------------+------------------------+-------------------------+
| **               | User must be logged    |                         |
| Preconditions**  | in.                    |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      |  User clicks "Edit     |                         |
|                  | Profile."              |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **User**               | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Opens      | **Step 2:** Load data   |
|                  | profile page.          | from User File          |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | ** **                  | **Step 3:** Displays    |
|                  |                        | Update Details form.    |
|                  | ** **                  |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        |                         |
|                  | **Step 4:** Edits      |                         |
|                  | details (e.g., phone,  | **Step 5:** Validates   |
|                  | password).             | new input.              |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 6:** Saves      | **Step 7**: Displays    |
|                  | changes.               | confirmation message.   |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Alternate      | **User**               | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     |                        | **Step 4:** Validation  |
| entered          |                        | test fails.             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3.                 |
+------------------+------------------------+-------------------------+
| Unauthorized     |                        | System prevents         |
| update attempt   |                        | unauthorized actions.   |
+------------------+------------------------+-------------------------+
| **Conclusions**  | User details updated   |                         |
|                  | and stored in User     |                         |
|                  | file.                  |                         |
+------------------+------------------------+-------------------------+
| **Post           | Modified values stored |                         |
| conditions**     | in Users.              |                         |
+------------------+------------------------+-------------------------+
| **Business       | Email cannot be        |                         |
| Rules**          | changed to one that    |                         |
|                  | already exists. Role   |                         |
|                  | cannot be changed by   |                         |
|                  | general users.         |                         |
+------------------+------------------------+-------------------------+
| **Implementation | All updates logged for |                         |
| Constraints**    | auditing.              |                         |
+------------------+------------------------+-------------------------+

### Remove User

![](/media/imagee.png){width="6.375in" height="2.9791666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Remove User            |                         |
| Name**           | (Deactivate / Admin    |                         |
|                  | Removal)               |                         |
+==================+========================+=========================+
| **Use Case Id**  | 3                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | Medium                 |                         |
+------------------+------------------------+-------------------------+
| **Source**       | User / Admin           |                         |
+------------------+------------------------+-------------------------+
| **Primary        | User                   |                         |
| Business         | (self-deactivation) /  |                         |
| Actor**          | Admin (forced          |                         |
|                  | removal)               |                         |
+------------------+------------------------+-------------------------+
| **Other          | System                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A user may request     |                         |
|                  | deletion of their own  |                         |
|                  | account, or an admin   |                         |
|                  | may remove/deactivate  |                         |
|                  | an account (e.g.,      |                         |
|                  | inactive, fraudulent,  |                         |
|                  | violating policy).     |                         |
|                  | System deactivates     |                         |
|                  | users by updating      |                         |
|                  | their status instead   |                         |
|                  | of fully deleting, to  |                         |
|                  | preserve               |                         |
|                  | donation/fundraiser    |                         |
|                  | history.               |                         |
+------------------+------------------------+-------------------------+
| **               | User account exists.   |                         |
| Preconditions**  |                        |                         |
|                  | For user deletion:     |                         |
|                  | must be logged in.     |                         |
|                  |                        |                         |
|                  | For admin removal:     |                         |
|                  | admin must be          |                         |
|                  | authorized.            |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | User clicks "Delete    |                         |
|                  | Account" OR Admin      |                         |
|                  | selects "Remove User"  |                         |
|                  | in admin panel.        |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **User**               | **System **             |
| Scenario 1**     |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** User opens | **Step 2:** Displays    |
|                  | account settings.      | delete/deactivate       |
|                  |                        | option.                 |
|                  |                        |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        | **Step 4:** Updates     |
|                  | ** **                  | Users.status = \"D\".   |
|                  |                        |                         |
|                  | ** **                  |                         |
|                  |                        |                         |
|                  | **Step 3:** User       | **Step 5:** Revokes     |
|                  | confirms deletion.     | sessions/tokens.        |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6**: Displays    |
|                  |                        | confirmation "Your      |
|                  |                        | account has been        |
|                  |                        | deactivated."           |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Admin**              | **System **             |
| Scenario 2**     |                        |                         |
+------------------+------------------------+-------------------------+
|                  | **Step 1:** Admin      | **Step 2:** System      |
|                  | selects a user in      | shows user details and  |
|                  | management panel.      | removal options.        |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 3:** Admin      | **Step 4:** Updates     |
|                  | confirms removal.      | Users.status = \"D\" or |
|                  |                        | deletes record if       |
|                  |                        | policy allows.          |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Logs action |
|                  |                        | for audit.              |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Displays    |
|                  |                        | confirmation.           |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Alternate      | **User**               | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| User cancels     |  **Step 3:** User      | **Step 4:** No changes  |
| deletion         | cancels deletion.      | made.                   |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| User has active  |                        | **Step 4:**             |
| donations        |                        | Subscriptions           |
| subscri          |                        | cancelled.              |
| ptions/donations |                        |                         |
| jars             |                        | Deactivation allowed,   |
|                  |                        | but data retained.      |
+------------------+------------------------+-------------------------+
| Policy forbids   |                        | System enforces         |
| hard deletion    |                        | deactivation only.      |
+------------------+------------------------+-------------------------+
| **Conclusions**  | User account access    |                         |
|                  | removed                |                         |
|                  | successfully.          |                         |
+------------------+------------------------+-------------------------+
| **Post           | User cannot log in;    |                         |
| conditions**     | account marked         |                         |
|                  | inactive. Data remains |                         |
|                  | for                    |                         |
|                  | donation/fundraiser    |                         |
|                  | records.               |                         |
+------------------+------------------------+-------------------------+
| **Business       | Deactivation instead   |                         |
| Rules**          | of hard delete for     |                         |
|                  | audit and compliance.  |                         |
|                  |                        |                         |
|                  | Only admins can fully  |                         |
|                  | remove (if allowed by  |                         |
|                  | policy).               |                         |
|                  |                        |                         |
|                  | Users can only         |                         |
|                  | deactivate their own   |                         |
|                  | accounts               |                         |
|                  |                        |                         |
|                  | Donation jar must be   |                         |
|                  | closed. Money must be  |                         |
|                  | withdrawn.             |                         |
+------------------+------------------------+-------------------------+
| **Implementation | All updates logged for |                         |
| Constraints**    | auditing.              |                         |
+------------------+------------------------+-------------------------+

###  {#section-1 .list-paragraph}

### Upgrade to Fundraiser

![A diagram of a process AI-generated content may be
incorrect.](media/image6.png){width="6.375in" height="1.59375in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Upgrade User to        |                         |
| Name**           | Fundraiser             |                         |
+==================+========================+=========================+
| **Use Case Id**  | 4                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Donor                  |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Donor                  |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | Admin                  |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A Donor requests to    |                         |
|                  | upgrade their account  |                         |
|                  | to a Fundraiser to be  |                         |
|                  | able to create         |                         |
|                  | donation jars.         |                         |
|                  | Passport verification  |                         |
|                  | is required.           |                         |
+------------------+------------------------+-------------------------+
| **               | User exists with role  |                         |
| Preconditions**  | = Donor.               |                         |
|                  |                        |                         |
|                  | User must provide      |                         |
|                  | passport details.      |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | Donor clicks "Upgrade  |                         |
|                  | to Fundraiser."        |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **User**               | **System **             |
| Scenario **      |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Requests   | **Step 2:** Displays    |
|                  | upgrade to             | upgrade form (passport  |
|                  | Fundraiser.            | number, image upload).  |
|                  |                        |                         |
|                  |                        | ** **                   |
|                  |                        |                         |
|                  | ** **                  | **Step 4:** Validates   |
|                  |                        | input.                  |
|                  | **Step 3:** Submits    |                         |
|                  | details.               |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Creates     |
|                  |                        | FundRaiser record       |
|                  |                        | (passport_verified =    |
|                  |                        | FALSE).                 |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6**: Marks User  |
|                  |                        | as "Upgrade Pending     |
|                  |                        | Verification."          |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7:** Sends       |
|                  |                        | notification to Admin.  |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 9:** Admin sets  |
|                  |                        | passport_verified =     |
|                  |                        | TRUE if valid (or       |
|                  |                        | rejects if invalid).    |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 8:** Admin      |                         |
|                  | reviews.               | **Step 10:** System     |
|                  |                        | updates User role from  |
|                  |                        | Donor -\> Fundraiser    |
|                  |                        | (if approved).          |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 11:** Sends      |
|                  |                        | notification to User    |
|                  |                        | (approved or            |
|                  |                        | rejected).              |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Alternate      | **User**               | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid passport |                        | System rejects          |
| number           |                        | request.                |
+------------------+------------------------+-------------------------+
| Missing image    |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3                  |
+------------------+------------------------+-------------------------+
| Admin rejects    |                        | **Step 9:** Status =    |
|                  |                        | "Denied", notify user.  |
|                  |                        |                         |
|                  |                        | **Step 10:** Display    |
|                  |                        | appropriate Reject      |
|                  |                        | message                 |
+------------------+------------------------+-------------------------+
| **Conclusions**  | User is upgraded and   |                         |
|                  | can now create         |                         |
|                  | Donation Jars.         |                         |
+------------------+------------------------+-------------------------+
| **Post           | Fundraiser record      |                         |
| conditions**     | exists, linked to      |                         |
|                  | User. User role = "F"  |                         |
|                  | if approved.           |                         |
+------------------+------------------------+-------------------------+
| **Business       | Passport verification  |                         |
| Rules**          | mandatory.             |                         |
|                  |                        |                         |
|                  | Only Donors can        |                         |
|                  | upgrade.               |                         |
|                  |                        |                         |
|                  | Admin final approval   |                         |
|                  | required.              |                         |
+------------------+------------------------+-------------------------+
| **Implementation | Passport images must   |                         |
| Constraints**    | be securely stored and |                         |
|                  | verified by admin      |                         |
|                  | before role upgrade is |                         |
|                  | applied.               |                         |
+------------------+------------------------+-------------------------+

## Manage Donation Jar Idea

### **Create Donation Jar**

![](/media/imagef.png){width="6.375in" height="2.2916666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Create Jar             |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 5                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Fundraiser / Admin     |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Fundraiser             |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | System                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | An approved Fundraiser |                         |
|                  | can create Donation    |                         |
|                  | Jar to collect money   |                         |
+------------------+------------------------+-------------------------+
| **               | Fundraiser must have   |                         |
| Preconditions**  | approved account;      |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | Fundraiser clicked     |                         |
|                  | \"Create Jar" on a     |                         |
|                  | page.                  |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Fundraiser **        | **System**              |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step                 | **Step 2: ** Loads the  |
|                  | 1: ** Fundraiser opens | appropriate UI and      |
|                  | "Create Jar" page      | pre-fills available     |
|                  |                        | options. \              |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 4:** Fundraiser | **Step 3: ** Displays   |
|                  | Enters general         | UI.                     |
|                  | details:               |                         |
|                  |                        | ** **                   |
|                  | -   Jar_Name           |                         |
|                  |                        | ** **                   |
|                  | ```{=html}             |                         |
|                  | <!-- -->               |                         |
|                  | ```                    |                         |
|                  | -   Target_Amount      |                         |
|                  |                        |                         |
|                  | ```{=html}             |                         |
|                  | <!-- -->               |                         |
|                  | ```                    |                         |
|                  | -   Payment_Method     |                         |
|                  |                        |                         |
|                  | ```{=html}             |                         |
|                  | <!-- -->               |                         |
|                  | ```                    |                         |
|                  | -   Description        |                         |
|                  |                        |                         |
|                  | ```{=html}             | **Step 6: ** Validate   |
|                  | <!-- -->               | the data:               |
|                  | ```                    |                         |
|                  | -   Expiration date    | -   All fields must be  |
|                  |                        |     entered.            |
|                  |                        |                         |
|                  |                        | ```{=html}              |
|                  |                        | <!-- -->                |
|                  |                        | ```                     |
|                  | **Step 5: ** Clicks    | -   All field fits data |
|                  | "Create" Donation Jar  |     type                |
|                  |                        |                         |
|                  |                        | -   Must fit minimum    |
|                  |                        |     and maximum target  |
|                  |                        |     amount              |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7:**             |
|                  |                        |                         |
|                  |                        | Assing new jar_id;      |
|                  |                        |                         |
|                  |                        | Assing new start_date;  |
|                  |                        |                         |
|                  |                        | Assing new created_by;  |
|                  |                        |                         |
|                  |                        | Assing new created_at;  |
|                  |                        |                         |
|                  |                        | Assing current_amount = |
|                  |                        | 0;                      |
|                  |                        |                         |
|                  |                        | Update updated_at;      |
|                  |                        |                         |
|                  |                        | Assing status -         |
|                  |                        | pending;                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8: ** Create new |
|                  |                        | Donation Jar in DB      |
|                  |                        | file - "Donation_Jar"   |
|                  |                        |                         |
|                  |                        | **Step 9: ** Shows      |
|                  |                        | confirmation on UI      |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 10:** Reset UI.  |
|                  |                        |                         |
|                  |                        | **Step 11: ** Sends     |
|                  |                        | relevant message to     |
|                  |                        | email.                  |
|                  |                        |                         |
|                  |                        | **Step 12**: Pending    |
|                  |                        | until Admin             |
|                  |                        | Confirmation            |
|                  |                        |                         |
|                  |                        | **Step 12**: Change     |
|                  |                        | status to active        |
|                  |                        |                         |
|                  |                        | **Step 13: ** Sends     |
|                  |                        | relevant message to     |
|                  |                        | owner by email.         |
+------------------+------------------------+-------------------------+
| **Alternate      | **Fundraiser **        | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     |                        | **Step 6:**  Validation |
| entered          |                        | test fails.             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7:**  Display an |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8:**  Return to  |
|                  |                        | step 5.                 |
+------------------+------------------------+-------------------------+
| Admin reject     |                        | **Step 10:** Drop Jar   |
| creation         |                        | record in DB file --    |
|                  |                        | "Donation_Jar"          |
+------------------+------------------------+-------------------------+
| **Conclusions**  | New Donations Jar      |                         |
|                  | saved and active       |                         |
+------------------+------------------------+-------------------------+
| **Post           | Jar visible in System  |                         |
| conditions**     |                        |                         |
+------------------+------------------------+-------------------------+
| **Business       | Minimum target amount  |                         |
| Rules**          | applies.               |                         |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Implementation |                        |                         |
| Constraints**    |                        |                         |
+------------------+------------------------+-------------------------+

### **Update Donation Jar**

![](/media/image10.png){width="6.375in" height="2.2916666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Update Jar             |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 6                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Fundraiser / Admin     |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Fundraiser (request) / |                         |
| Business         | Admin (removal)        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | System                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | Fundraiser can update  |                         |
|                  | details of active Jar. |                         |
|                  | But cannot change its  |                         |
|                  | goal. All updates must |                         |
|                  | be approved by Admin.  |                         |
+------------------+------------------------+-------------------------+
| **               | Fundraiser must be     |                         |
| Preconditions**  | logged in.             |                         |
|                  |                        |                         |
|                  | Target Jar must be     |                         |
|                  | active                 |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      |  Fundraiser clicks     |                         |
|                  | "Update Jar"           |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Fundraiser**         | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Fundraiser | **Step 2: ** Loads the  |
|                  | opens "Update Jar"     | appropriate UI and      |
|                  | page.                  | pre-fills available     |
|                  |                        | options. \              |
|                  |                        |                         |
|                  |                        |                         |
|                  | ** **                  | **Step 3: ** Displays   |
|                  |                        | UI.                     |
|                  | ** **                  |                         |
|                  |                        | **Step 4:** Displays    |
|                  | ** **                  | Update Jar form.        |
|                  |                        |                         |
|                  | **Step 5:** Fundraiser | ** **                   |
|                  | edits details          |                         |
|                  |                        | **Step 5:** Validates   |
|                  | -   Jar_Name           | changed input fields.   |
|                  |                        |                         |
|                  | ```{=html}             | -   All fields must be  |
|                  | <!-- -->               |     entered.            |
|                  | ```                    |                         |
|                  | -   Target_Amount      | ```{=html}              |
|                  |                        | <!-- -->                |
|                  | ```{=html}             | ```                     |
|                  | <!-- -->               | -   All field fits data |
|                  | ```                    |     type                |
|                  | -   Payment_Method     |                         |
|                  |                        | -   Must fit minimum    |
|                  | ```{=html}             |     and maximum target  |
|                  | <!-- -->               |     amount              |
|                  | ```                    |                         |
|                  | -   Description        |                         |
|                  |                        |                         |
|                  | ```{=html}             |                         |
|                  | <!-- -->               |                         |
|                  | ```                    |                         |
|                  | -   Expiration date.   |                         |
|                  |                        | **Step 7: ** Update     |
|                  |                        | record in DB file --    |
|                  |                        | "Donation_Jar\" and set |
|                  |                        | status -- pending       |
|                  |                        |                         |
|                  | **Step 6:** Saves      |                         |
|                  | changes.               |                         |
|                  |                        | **Step 9: ** Shows      |
|                  |                        | confirmation on UI      |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 10: ** Sends     |
|                  |                        | relevant message to     |
|                  |                        | email.                  |
|                  |                        |                         |
|                  |                        | **Step 11:** Reset UI.  |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 12**: Pending    |
|                  |                        | until Admin             |
|                  |                        | Confirmation            |
|                  |                        |                         |
|                  |                        | **Step 12**: Change     |
|                  |                        | status to active        |
|                  |                        |                         |
|                  |                        | **Step 13: ** Sends     |
|                  |                        | relevant message to     |
|                  |                        | owner by email.         |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Alternate      | **Fundraiser **        | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     |                        | **Step 5:**             |
| entered          |                        |                         |
|                  |                        | Validation test fails.  |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:**             |
|                  |                        |                         |
|                  |                        | Display an appropriate  |
|                  |                        | error message.          |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7:**             |
|                  |                        |                         |
|                  |                        | Return to step 4.       |
+------------------+------------------------+-------------------------+
| Admin reject     |                        | **Step 10:** Drop Jar   |
| creation         |                        | record update in DB     |
|                  |                        | file -- "Donation_Jar"  |
|                  |                        | to previous.            |
|                  |                        |                         |
|                  |                        | **Step 11:** Set        |
|                  |                        | status - active         |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Donation Jar details   |                         |
|                  | updated and stored in  |                         |
|                  | Dotation_Jar file.     |                         |
+------------------+------------------------+-------------------------+
| **Post           | Information updated    |                         |
| conditions**     |                        |                         |
+------------------+------------------------+-------------------------+
| **Business       | New information        |                         |
| Rules**          | (details) does not     |                         |
|                  | break company policy   |                         |
|                  |                        |                         |
|                  | Admin must contact     |                         |
|                  | Fundraiser in case of  |                         |
|                  | rejected deletion      |                         |
+------------------+------------------------+-------------------------+
| **Implementation | All updates logged for |                         |
| Constraints**    | auditing and back ups  |                         |
+------------------+------------------------+-------------------------+

### **Close Donation Jar**

![](/media/image11.png){width="6.375in" height="2.2916666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Close Jar              |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 7                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Fundraiser / Admin     |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Fundraiser (request) / |                         |
| Business         | Admin (removal)        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | System                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | Fundraiser may request |                         |
|                  | deletion of his Jar.   |                         |
|                  | Admin may delete       |                         |
|                  | Donation Jar (e.g.,    |                         |
|                  | user request,          |                         |
|                  | inactive, fraudulent,  |                         |
|                  | violating policy).     |                         |
|                  | System deactivates Jar |                         |
|                  | by updating their      |                         |
|                  | status instead of      |                         |
|                  | fully deleting, to     |                         |
|                  | preserve               |                         |
|                  | donation/fundraiser    |                         |
|                  | history and system     |                         |
|                  | concurrency.           |                         |
+------------------+------------------------+-------------------------+
| **               | Fundraiser account     |                         |
| Preconditions**  | exists.                |                         |
|                  |                        |                         |
|                  | Fundraiser must be     |                         |
|                  | logged in.             |                         |
|                  |                        |                         |
|                  | Donation Jar exist and |                         |
|                  | belong to Fundraiser.  |                         |
|                  |                        |                         |
|                  | For admin removal or   |                         |
|                  | confirmation: admin    |                         |
|                  | must be authorized.    |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | User clicks "Close     |                         |
|                  | Jar" or Admin selects  |                         |
|                  | "Close Jar" in admin   |                         |
|                  | panel.                 |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Fundraiser**         | **System **             |
| Scenario 1**     |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Fundraiser | **Step 2: ** Loads the  |
|                  | opens "Close Jar       | appropriate UI and      |
|                  | page".                 | pre-fills available     |
|                  |                        | options. \              |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 5:** Fundraiser | **Step 3: ** Displays   |
|                  | enters reason to Close | UI.                     |
|                  | Jar into text field    |                         |
|                  | and press "Close".     | **Step 4:** Displays    |
|                  |                        | Close Jar form.         |
|                  | **Step 5:** Fundraiser |                         |
|                  | confirms deletion      | **Step 6:** Validates   |
|                  |                        | text input field.       |
|                  |                        |                         |
|                  |                        | -   Field not empty,    |
|                  |                        |                         |
|                  |                        | -   At least 20         |
|                  |                        |     characters.         |
|                  |                        |                         |
|                  |                        | **Step 4:** Displays    |
|                  |                        | Warning form.           |
|                  |                        |                         |
|                  |                        | **Step 6: ** Shows      |
|                  |                        | confirmation on UI      |
|                  |                        |                         |
|                  |                        | **Step 7: ** Sends      |
|                  |                        | relevant message to     |
|                  |                        | email.                  |
|                  |                        |                         |
|                  |                        | **Step 8:** Reset UI.   |
|                  |                        |                         |
|                  |                        | **Step 9: ** Update     |
|                  |                        | record in DB file --    |
|                  |                        | change status to -      |
|                  |                        | panding                 |
|                  |                        |                         |
|                  |                        | **Step 9**: Pending     |
|                  |                        | until Admin             |
|                  |                        | Confirmation            |
|                  |                        |                         |
|                  |                        | **Step 9: ** Update     |
|                  |                        | record in DB file --    |
|                  |                        | change status to -      |
|                  |                        | closed                  |
|                  |                        |                         |
|                  |                        | **Step 11: ** Sends     |
|                  |                        | relevant message to     |
|                  |                        | email                   |
+------------------+------------------------+-------------------------+
| **Expected       | **Admin**              | **System **             |
| Scenario 2**     |                        |                         |
+------------------+------------------------+-------------------------+
|                  | **Step 1:** Admin      | **Step 2:** System      |
|                  | selects a Jar in       | shows Jar details and   |
|                  | management panel.      | removal options.        |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | **Step 3:** Admin      | **Step 4: ** Update     |
|                  | confirms removal.      | record in DB file --    |
|                  |                        | change status.          |
|                  |                        |                         |
|                  |                        | **Step 5: ** Sends      |
|                  |                        | relevant message to     |
|                  |                        | owner email.            |
|                  |                        |                         |
|                  |                        | **Step 6:** Displays    |
|                  |                        | confirmation.           |
+------------------+------------------------+-------------------------+
| Admin reject     |                        | **Step 10:** Leave Jar  |
| creation         |                        | status in               |
|                  |                        | "Donation_Jar" -        |
|                  |                        | pending                 |
+------------------+------------------------+-------------------------+
| **Alternate      | **Fundraiser**         | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid Data     |                        | **Step 6:**  Validation |
| entered          |                        | of text input field     |
|                  |                        | fails.                  |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7:**  Display an |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8:**  Return to  |
|                  |                        | step 5                  |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Donation Jar removed   |                         |
|                  | successfully, but Data |                         |
|                  | still in DB.           |                         |
+------------------+------------------------+-------------------------+
| **Post           | Donor can not donate   |                         |
| conditions**     | to deleted Jar         |                         |
+------------------+------------------------+-------------------------+
| **Business       | Deactivation instead   |                         |
| Rules**          | of hard delete for     |                         |
|                  | audit and compliance.  |                         |
|                  |                        |                         |
|                  | Only admins can remove |                         |
|                  | (if allowed by         |                         |
|                  | policy).               |                         |
|                  |                        |                         |
|                  | Only owner can request |                         |
|                  | removal                |                         |
|                  |                        |                         |
|                  | Admin must contact     |                         |
|                  | Fundraiser in case of  |                         |
|                  | rejected deletion      |                         |
+------------------+------------------------+-------------------------+
| **Implementation | All updates logged for |                         |
| Constraints**    | auditing and back ups  |                         |
+------------------+------------------------+-------------------------+

### Withdraw money

![](/media/image12.png){width="6.375in" height="1.9166666666666667in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Withdraw money         |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 8                      |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Fundraiser / Admin     |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Fundraiser             |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | Admin                  |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A fundraiser requests  |                         |
|                  | a payout from a        |                         |
|                  | DonationJar. The       |                         |
|                  | system records a       |                         |
|                  | withdrawal request,    |                         |
|                  | routes it for          |                         |
|                  | approval, and upon     |                         |
|                  | approval creates a     |                         |
|                  | withdrawal record and  |                         |
|                  | adjusts jar totals.    |                         |
+------------------+------------------------+-------------------------+
| **               | Active DonationJar     |                         |
| Preconditions**  | exists; fundraiser is  |                         |
|                  | verified; available    |                         |
|                  | balance ≥ requested    |                         |
|                  | amount; policies allow |                         |
|                  | withdrawals.           |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | Fundraiser opens the   |                         |
|                  | jar and clicks Request |                         |
|                  | Withdrawal.            |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Fundraiser**         | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Opens      | **Step 2:** Displays    |
|                  | withdrawal form on a   | withdrawal UI (amount,  |
|                  | selected jar.          | purpose, evidence).     |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  | ** **                  | **Step 4:** Validates   |
|                  |                        | input (amount \> 0, ≤   |
|                  | **Step 3:** Enters     | available balance;      |
|                  | amount and purpose     | purpose length ≤ 200).  |
|                  | (optional: evidence    |                         |
|                  | URL/file).             | ** **                   |
|                  |                        |                         |
|                  |                        | **Step 6:** Creates     |
|                  |                        | record in               |
|                  | **Step 5:** Submits    | Withdrawal_Requests     |
|                  | the request.           | with status "Pending".  |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 7**: Notifies    |
|                  |                        | Admin for               |
|                  |                        | review/approval.        |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8**: Admin       |
|                  |                        | approves → creates      |
|                  |                        | Withdrawals record;     |
|                  |                        | updates                 |
|                  |                        | Withdrawal_Requests to  |
|                  |                        | "Approved".             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 9:** Decreases   |
|                  |                        | Don                     |
|                  |                        | ationJar.current_amount |
|                  |                        | by approved amount.     |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 10:** Stores     |
|                  |                        | payout details          |
|                  |                        | (recipient, evidence);  |
|                  |                        | logs audit trail.       |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 11:** Sends      |
|                  |                        | notification to         |
|                  |                        | fundraiser; Reset UI    |
+------------------+------------------------+-------------------------+
| **Alternate      | **Fundraiser**         | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     |                        | **Step 4:** Validation  |
| entered          |                        | test fails.             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3.                 |
+------------------+------------------------+-------------------------+
| Request rejected |                        | **Step 8:** Set         |
| by Admin.        |                        | Wit                     |
|                  |                        | hdrawal_Requests.status |
|                  |                        | = "Rejected"; capture   |
|                  |                        | rejection reason.       |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 9:** Notify      |
|                  |                        | fundraiser; no balance  |
|                  |                        | change.                 |
+------------------+------------------------+-------------------------+
| Payment          |                        | **Step 8/10:** Mark     |
| processing       |                        | payout as Failed (no    |
| error            |                        | Withdrawals record or   |
|                  |                        | rollback); notify       |
|                  |                        | fundraiser; allow       |
|                  |                        | retry.                  |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| Evidence         |                        | **Step 4**: Fail        |
| missing          |                        | validation; prompt to   |
|                  |                        | attach evidence.        |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Withdrawal is          |                         |
|                  | requested and either   |                         |
|                  | approved and paid, or  |                         |
|                  | rejected with a        |                         |
|                  | reason; notifications  |                         |
|                  | sent.                  |                         |
+------------------+------------------------+-------------------------+
| **Post           | If approved:           |                         |
| conditions**     | Withdrawals record     |                         |
|                  | exists and             |                         |
|                  | Dona                   |                         |
|                  | tionJar.current_amount |                         |
|                  | is decreased; full     |                         |
|                  | audit present. If      |                         |
|                  | rejected: request      |                         |
|                  | remains recorded, jar  |                         |
|                  | balance unchanged.     |                         |
+------------------+------------------------+-------------------------+
| **Business       | Minimum/maximum        |                         |
| Rules**          | withdrawal limits      |                         |
|                  | apply.                 |                         |
|                  |                        |                         |
|                  | Amount must not exceed |                         |
|                  | available balance      |                         |
|                  | (consider pending      |                         |
|                  | withdrawals).          |                         |
|                  |                        |                         |
|                  | Evidence may be        |                         |
|                  | required above a       |                         |
|                  | threshold.             |                         |
|                  |                        |                         |
|                  | Only verified          |                         |
|                  | fundraisers can        |                         |
|                  | request withdrawals;   |                         |
|                  | only Admins approve.   |                         |
|                  |                        |                         |
|                  | One Withdrawal per     |                         |
|                  | Withdrawal_Request     |                         |
|                  | (1--1).                |                         |
|                  |                        |                         |
|                  | Payouts follow the     |                         |
|                  | project's financial    |                         |
|                  | policy and regional    |                         |
|                  | compliance             |                         |
+------------------+------------------------+-------------------------+
| **Implementation | None.                  |                         |
| Constraints**    |                        |                         |
+------------------+------------------------+-------------------------+

## Manage Donations

### Pay Donation

![A diagram of a donation AI-generated content may be
incorrect.](media/image7.png){width="6.381944444444445in"
height="2.011111111111111in"}

+-------------------+------------------------+-------------------------+
| **Use Case        | Pay Donation           |                         |
| Name**            |                        |                         |
+===================+========================+=========================+
| **Use Case Id**   | 9                      |                         |
+-------------------+------------------------+-------------------------+
| **Priority**      | High                   |                         |
+-------------------+------------------------+-------------------------+
| **Source**        | Donor / Admin          |                         |
+-------------------+------------------------+-------------------------+
| **Primary         | Donor                  |                         |
| Business Actor**  |                        |                         |
+-------------------+------------------------+-------------------------+
| **Other           | Admin                  |                         |
| Participating     |                        |                         |
| Actors**          |                        |                         |
+-------------------+------------------------+-------------------------+
| **Description**   | A donor makes a        |                         |
|                   | one-time or recurring  |                         |
|                   | donation to a Donation |                         |
|                   | Jar. The donation is   |                         |
|                   | stored and linked to   |                         |
|                   | the donor, jar, and    |                         |
|                   | transactions.          |                         |
+-------------------+------------------------+-------------------------+
| *                 | Active Donation Jar    |                         |
| *Preconditions**  | exists; payment        |                         |
|                   | provider is available; |                         |
|                   | (optional) donor is    |                         |
|                   | logged in.             |                         |
+-------------------+------------------------+-------------------------+
| **Trigger**       | Donor clicks Donate on |                         |
|                   | the selected jar.      |                         |
+-------------------+------------------------+-------------------------+
| **Expected        | **Donor**              | **System **             |
| Scenario**        |                        |                         |
+-------------------+------------------------+-------------------------+
| ** **             | **Step 1**: Selects a  | **Step 2:** Displays    |
|                   | jar and amount.        | donation                |
|                   |                        |                         |
|                   |  \                     | form. \                 |
|                   | **Step 3:** Enters     |                         |
|                   | payment details and    |                         |
|                   | (optional)             | **Step 4:** Validates   |
|                   |                        | input (amount \> 0,     |
|                   | Message/anonymity.     | Message max length 150, |
|                   |                        | payment method          |
|                   |  \                     | available, email        |
|                   | **Step 5**: Confirms   | format). \              |
|                   | the donation.          |                         |
|                   |                        |                         |
|                   |                        | **Step 6:** Creates     |
|                   |                        | record in Donations     |
|                   |                        | with status "PENDING".  |
|                   |                        |                         |
|                   |                        |  \                      |
|                   |                        | **Step 7:** Initiates   |
|                   |                        | payment with provider.  |
|                   |                        |                         |
|                   |                        |  \                      |
|                   |                        | **Step 8:** Stores      |
|                   |                        | Transaction with status |
|                   |                        | Completed/Failed.       |
|                   |                        |                         |
|                   |                        |  \                      |
|                   |                        | **Step 9:** Updates     |
|                   |                        | Donations to SUCCESS if |
|                   |                        | paid.                   |
|                   |                        |                         |
|                   |                        |  \                      |
|                   |                        | **Step 10:** Increments |
|                   |                        | DonationJ               |
|                   |                        | ar.current_amount**.**  |
|                   |                        |                         |
|                   |                        |  \                      |
|                   |                        | **Step 11:** Sends      |
|                   |                        | confirmation and        |
|                   |                        | receipt.                |
|                   |                        |                         |
|                   |                        | ** **                   |
|                   |                        |                         |
|                   |                        | **Step 11:** Reset UI.  |
+-------------------+------------------------+-------------------------+
| **Alternate       | **Donor**              | **System **             |
| Scenarios**       |                        |                         |
+-------------------+------------------------+-------------------------+
| Invalid data      |                        | **Step 5:** Validation  |
| entered           |                        | test fails.             |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        | **Step 6:** Display an  |
|                   |                        | appropriate error       |
|                   |                        | message.                |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        | **Step 7:** Return to   |
|                   |                        | step 3.                 |
+-------------------+------------------------+-------------------------+
| User cancels      |                        | **Step 8:** status      |
| payment           |                        | CANCELED                |
+-------------------+------------------------+-------------------------+
| Payment fails     |                        | **Step 8:** status      |
|                   |                        | FAILED.                 |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        | **Step 9:** Display an  |
|                   |                        | appropriate error       |
|                   |                        | message.                |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        |                         |
|                   |                        | **Step 10:** Return to  |
|                   |                        | step 5.                 |
|                   |                        |                         |
|                   |                        |                         |
+-------------------+------------------------+-------------------------+
| **Conclusions**   | New donations are      |                         |
|                   | saved, campaign        |                         |
|                   | updated, receipt       |                         |
|                   | sent.                  |                         |
+-------------------+------------------------+-------------------------+
| **Post            | Donation status is     |                         |
| conditions**      | SUCCESS (or other      |                         |
|                   | final); subscription   |                         |
|                   | created if recurring.  |                         |
+-------------------+------------------------+-------------------------+
| **Business        | Minimum donation       |                         |
| Rules**           | amount applies. \      |                         |
|                   | - Provider fees may    |                         |
|                   | apply. \               |                         |
|                   | - Anonymous donations  |                         |
|                   | hide donor names but   |                         |
|                   | keep email for         |                         |
|                   | receipt.               |                         |
|                   |                        |                         |
|                   |                        |                         |
+-------------------+------------------------+-------------------------+
| **Implementation  | \- Integration with    |                         |
| Constraints**     | payment provider       |                         |
|                   | (REST/Webhooks). \     |                         |
|                   | - Idempotency on       |                         |
|                   | ProviderTxnId. \       |                         |
|                   | - Security compliance  |                         |
|                   | (e.g., PCI DSS, no     |                         |
|                   | storing card data).    |                         |
+-------------------+------------------------+-------------------------+

### Return Donation

![A diagram of a donation AI-generated content may be
incorrect.](media/image8.png){width="6.376388888888889in"
height="2.1041666666666665in"}

+------------------+------------------------+-------------------------+
| **Use Case       | Refund Donation        |                         |
| Name**           |                        |                         |
+==================+========================+=========================+
| **Use Case Id**  | 10                     |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | Medium                 |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Donor / Admin          |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Donor                  |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | Admin                  |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | A donor requests a     |                         |
|                  | refund of a previous   |                         |
|                  | donation (full or      |                         |
|                  | partial).              |                         |
+------------------+------------------------+-------------------------+
| **               | A successful donation  |                         |
| Preconditions**  | exists; refund window  |                         |
|                  | is open; funds not yet |                         |
|                  | distributed.           |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      | Donor requests refund  |                         |
|                  | from history, or Admin |                         |
|                  | initiates refund from  |                         |
|                  | admin panel.           |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Donor / Admin**      | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Opens      | **Step 2:** Shows       |
|                  | donation and chooses   | refund form.            |
|                  | Request Refund.        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |  \                     |                         |
|                  | **Step 3:** Enters     |  \                      |
|                  | details:               | **Step 4:** Validates   |
|                  |                        | request (eligibility,   |
|                  | Email                  | amount ≤ available,     |
|                  |                        | window).                |
|                  | Transaction ID         |                         |
|                  |                        |  \                      |
|                  |  confirms.             | **Step 5:** Creates     |
|                  |                        | refund Transaction.     |
|                  |                        |                         |
|                  |                        |  \                      |
|                  | ** **                  | **Step 6:** Submits     |
|                  |                        | refund to provider.     |
|                  |                        |                         |
|                  |                        |  \                      |
|                  |                        | **Step 7:** Updates     |
|                  |                        | refund_status (Pending  |
|                  |                        | -\> Completed/Failed).  |
|                  |                        |                         |
|                  |                        |  \                      |
|                  |                        | **Step 8:** Updates     |
|                  |                        | Donations/Jar amounts   |
|                  |                        | as needed.              |
|                  |                        |                         |
|                  |                        |  \                      |
|                  |                        | **Step 9:** Sends       |
|                  |                        | notification. \         |
|                  |                        |  \                      |
|                  |                        | ** **                   |
|                  |                        |                         |
|                  |                        | **Step 11:** Reset UI.  |
+------------------+------------------------+-------------------------+
| **Alternate      | **Donor / Admin**      | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Refund not       |                        | **Step 4:** Validation  |
| allowed          |                        | test fails.             |
| (                |                        |                         |
| expired/policy)  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3.                 |
+------------------+------------------------+-------------------------+
| Provider         |                        | **Step 7:** mark        |
| failure          |                        | FAILED, notify          |
|                  |                        | donor/admin             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 8:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message                 |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 9:** Return to   |
|                  |                        | step 3.                 |
+------------------+------------------------+-------------------------+
| Funds already    |                        | **Step 4:** Validation  |
| distributed      |                        | test fails.             |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 5:** Display an  |
|                  |                        | appropriate error       |
|                  |                        | message.                |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        |                         |
|                  |                        | **Step 6:** Return to   |
|                  |                        | step 3.                 |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Refund request is      |                         |
|                  | processed and donor    |                         |
|                  | notified.              |                         |
+------------------+------------------------+-------------------------+
| **Post           | Refund status          |                         |
| conditions**     | recorded; jar totals   |                         |
|                  | adjusted if refunded.  |                         |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Business       | \- Refund window (7    |                         |
| Rules**          | days). \               |                         |
|                  | - Partial refunds      |                         |
|                  | allowed if policy      |                         |
|                  | permits. \             |                         |
|                  | - Refund to original   |                         |
|                  | payment method only.   |                         |
|                  |                        |                         |
|                  |                        |                         |
+------------------+------------------------+-------------------------+
| **Implementation | None.                  |                         |
| Constraints**    |                        |                         |
+------------------+------------------------+-------------------------+

## Manage Revenue Analysis

![A diagram of a donation AI-generated content may be
incorrect.](media/image9.png){width="6.376388888888889in"
height="1.8180555555555555in"}

### Yearly Revenue Analysis by Month

+------------------+------------------------+-------------------------+
| **Use Case       | Yearly Revenue         |                         |
| Name**           | Analysis by Month      |                         |
+==================+========================+=========================+
| **Use Case Id**  | 11                     |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Manager                |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Manager                |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          | None.                  |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | This use case provides |                         |
|                  | revenue analysis for a |                         |
|                  | selected year, broken  |                         |
|                  | down by months, so the |                         |
|                  | manager can track      |                         |
|                  | monthly income         |                         |
|                  | trends.                |                         |
+------------------+------------------------+-------------------------+
| **               | Donation records exist |                         |
| Preconditions**  | in the system.         |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      |  Manager initiates the |                         |
|                  | **Yearly Revenue       |                         |
|                  | Analysis by Month**    |                         |
|                  | function.              |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Manager**            | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Starts the | **Step 2:** Retrieves   |
|                  | Yearly Revenue         | Transactions from       |
|                  | Analysis by Month      | Transaction file.       |
|                  | function.              |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        | **Step 3:** Displays    |
|                  | ** **                  | the analysis user       |
|                  |                        | interface.              |
|                  | ** **                  |                         |
|                  |                        | ** **                   |
|                  | **Step 4:** Selects    |                         |
|                  | the year for           | **Step 5:** Validates   |
|                  | analysis.              | input:                  |
|                  |                        |                         |
|                  | ** **                  | -   Year must be        |
|                  |                        |     entered.            |
|                  | ** **                  |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        | **Step 6**: Retrieves   |
|                  | ** **                  | donation records for    |
|                  |                        | the selected year.      |
|                  | ** **                  |                         |
|                  |                        |                         |
|                  | ** **                  |                         |
|                  |                        | **Step 7:** Groups      |
|                  | ** **                  | donations by month and  |
|                  |                        | calculates total        |
|                  | ** **                  | revenue by month.       |
|                  |                        |                         |
|                  | ** **                  | ** **                   |
|                  |                        |                         |
|                  |                        | **Step 8: L**oads       |
|                  |                        | results into the UI     |
|                  |                        | (report, chart).        |
|                  |                        |                         |
|                  | ** **                  | ** **                   |
|                  |                        |                         |
|                  | ** **                  | **Step 9:** Reset       |
|                  |                        | UI.** **                |
+------------------+------------------------+-------------------------+
| **Alternate      | **Manager**            | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     | ** **                  | **Step 5:** Display an  |
| entered          |                        | appropriate message \   |
|                  |                        |  \                      |
|                  |                        | **Step 6:** Return to   |
|                  |                        | Step 4.                 |
+------------------+------------------------+-------------------------+
| Data not found   | ** **                  | **Step 7:** Display "No |
|                  |                        | data avaliable"         |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Yearly Revenue for     |                         |
|                  | selected year is       |                         |
|                  | available for          |                         |
|                  | analysis.              |                         |
+------------------+------------------------+-------------------------+
| **Post           | Monthly revenue        |                         |
| conditions**     | breakdown for the      |                         |
|                  | selected year is       |                         |
|                  | available for          |                         |
|                  | analysis.              |                         |
+------------------+------------------------+-------------------------+
| **Business       | A valid year must be   |                         |
| Rules**          | entered before         |                         |
|                  | analysis can be        |                         |
|                  | generated.             |                         |
|                  |                        |                         |
|                  | Revenue must be        |                         |
|                  | aggregated by each     |                         |
|                  | month.                 |                         |
+------------------+------------------------+-------------------------+
| **Implementation |   None.                |                         |
| Constraints**    |                        |                         |
+------------------+------------------------+-------------------------+

### Yearly Donation Type Analysis 

+------------------+------------------------+-------------------------+
| **Use Case       | Yearly Donation Type   |                         |
| Name**           | Analysis               |                         |
+==================+========================+=========================+
| **Use Case Id**  | 12                     |                         |
+------------------+------------------------+-------------------------+
| **Priority**     | High                   |                         |
+------------------+------------------------+-------------------------+
| **Source**       | Manager                |                         |
+------------------+------------------------+-------------------------+
| **Primary        | Manager                |                         |
| Business         |                        |                         |
| Actor**          |                        |                         |
+------------------+------------------------+-------------------------+
| **Other          |  None.                 |                         |
| Participating    |                        |                         |
| Actors**         |                        |                         |
+------------------+------------------------+-------------------------+
| **Description**  | This use case provides |                         |
|                  | yearly revenue         |                         |
|                  | analysis across        |                         |
|                  | different donation     |                         |
|                  | categories for the     |                         |
|                  | manager.               |                         |
+------------------+------------------------+-------------------------+
| **               | Donation records exist |                         |
| Preconditions**  | in the system.         |                         |
+------------------+------------------------+-------------------------+
| **Trigger**      |  Manager initiates the |                         |
|                  | **Yearly Donation      |                         |
|                  | Category Analysis**    |                         |
|                  | function.              |                         |
+------------------+------------------------+-------------------------+
| **Expected       | **Manager**            | **System **             |
| Scenario**       |                        |                         |
+------------------+------------------------+-------------------------+
| ** **            | **Step 1:** Starts the | **Step 2:** Retrieves   |
|                  | Yearly Donation        | Categories from         |
|                  | Category Analysis      | Donation file.          |
|                  | function.              |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        | **Step 3:** Displays    |
|                  | ** **                  | the analysis user       |
|                  |                        | interface.              |
|                  | ** **                  |                         |
|                  |                        | ** **                   |
|                  | **Step 4:** Selects    |                         |
|                  | the year for           | **Step 5:** Validates   |
|                  | analysis.              | input:                  |
|                  |                        |                         |
|                  | ** **                  | -   Year must be        |
|                  |                        |     entered.            |
|                  | ** **                  |                         |
|                  |                        | ** **                   |
|                  | ** **                  |                         |
|                  |                        | **Step 6**: Retrieves   |
|                  | ** **                  | donation records for    |
|                  |                        | the selected year.      |
|                  | ** **                  |                         |
|                  |                        |                         |
|                  | ** **                  |                         |
|                  |                        | **Step 7:** Groups      |
|                  | ** **                  | donations by category   |
|                  |                        | and calculates total    |
|                  | ** **                  | revenue per category.   |
|                  |                        |                         |
|                  | ** **                  | ** **                   |
|                  |                        |                         |
|                  |                        | **Step 8:** Loads       |
|                  |                        | results into the UI     |
|                  |                        | (report, chart).        |
|                  |                        |                         |
|                  | ** **                  | ** **                   |
|                  |                        |                         |
|                  | ** **                  | **Step 9:** Reset       |
|                  |                        | UI.** **                |
+------------------+------------------------+-------------------------+
| **Alternate      | **Manager**            | **System **             |
| Scenarios**      |                        |                         |
+------------------+------------------------+-------------------------+
| Invalid data     | ** **                  | **Step 5:** Display an  |
| entered          |                        | appropriate message \   |
|                  |                        |  \                      |
|                  |                        | **Step 6:** Return to   |
|                  |                        | Step 4.                 |
+------------------+------------------------+-------------------------+
| Data not found   | ** **                  | **Step 7:** Display "No |
|                  |                        | data available"         |
+------------------+------------------------+-------------------------+
| **Conclusions**  | Yearly Revenue for     |                         |
|                  | selected year is       |                         |
|                  | available for          |                         |
|                  | analysis.              |                         |
+------------------+------------------------+-------------------------+
| **Post           | Yearly donation        |                         |
| conditions**     | revenue by category is |                         |
|                  | available for analysis |                         |
|                  | and reporting.         |                         |
+------------------+------------------------+-------------------------+
| **Business       | A valid year must be   |                         |
| Rules**          | entered before         |                         |
|                  | analysis can be        |                         |
|                  | generated.             |                         |
|                  |                        |                         |
|                  | Revenue must be        |                         |
|                  | grouped strictly by    |                         |
|                  | defined donation       |                         |
|                  | categories.            |                         |
+------------------+------------------------+-------------------------+
| **Implementation |   None.                |                         |
| Constraints**    |                        |                         |
+------------------+------------------------+-------------------------+

# Data Model (UML Class Diagram)

## Class Diagram 

> ![A screenshot of a computer AI-generated content may be
> incorrect.](media/image10.png){width="5.810995188101487in"
> height="3.8585968941382327in"}

## Relational Schema 

-   **Users** (user_id, username, password, email, first_name,
    last_name, phone_number, email_verified, phone_verified, role,
    status, created_at, updated_at) 

```{=html}
<!-- -->
```
-   **Fundraisers** (fund_raiser_id, user_id, passport_number,
    passport_img, passport_verified, created_at, updated_at) 

```{=html}
<!-- -->
```
-   **Donation_Jar** (jar_id, jar_name, target_amount, current_amount,
    description, start_date, end_date, fundriser_id, created_at,
    updated_at) 

```{=html}
<!-- -->
```
-   **Donations** (donation_id, donation_amount, donation_date, user_id,
    jar_id) 

```{=html}
<!-- -->
```
-   **Transactions** (transaction_id, transaction_amount,
    transaction_date, donation_id, transaction_status, payment_method,
    refund_amount, refund_date, refund_status, original_transaction_id) 

```{=html}
<!-- -->
```
-   **Reports** (report_id, jar_id, title, description, amount_spent,
    evidence_url, verified, created_at, updated_at) 

```{=html}
<!-- -->
```
-   **Withdrawal_Requests** (request_id, jar_id, fund_raiser_id, amount,
    purpose, request_date, status, approved_by, approved_date,
    created_at, updated_at) 

```{=html}
<!-- -->
```
-   **Withdrawals** (withdrawal_id, request_id, amount, withdrawal_date,
    recipient, evidence_url, created_at, updated_at) 

## Database Schema 

**Relation: Users**

**Shcema: LiftFlow system**

-   **Attributes: User**

    -   user_id: INT, PRIMARY KEY

    -   username: TEXT, NOT NULL

    -   password: TEXT, NOT NULL

    -   email: TEXT, UNIQUE, NOT NULL

    -   first_name: TEXT

    -   last_name: TEXT

    -   phone_number: TEXT

    -   email_verified: BOOLEAN, DEFAULT FALSE

    -   phone_verified: BOOLEAN, DEFAULT FALSE

    -   role: TEXT

    -   status: TEXT

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

**Relation: Fundraisers**

-   **Attributes:**

    -   fund_raiser_id: INT, PRIMARY KEY

    -   user_id: INT, FOREIGN KEY → Users(user_id)

    -   passport_number: TEXT

    -   passport_img: TEXT

    -   passport_verified: BOOLEAN, DEFAULT FALSE

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

**Relation: Donation_Jar**

-   **Attributes:**

    -   jar_id: INT, PRIMARY KEY

    -   jar_name: TEXT, NOT NULL

    -   target_amount: DECIMAL(12,2), NOT NULL

    -   current_amount: DECIMAL(12,2), DEFAULT 0

    -   description: TEXT

    -   start_date: DATE, NOT NULL

    -   end_date: DATE

    -   fund_raiser_id: INT, FOREIGN KEY → Fundraisers(fund_raiser_id)

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

**Relation: Donations**

-   **Attributes:**

    -   donation_id: INT, PRIMARY KEY

    -   donation_amount: DECIMAL(12,2), NOT NULL

    -   donation_date: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   user_id: INT, FOREIGN KEY → Users(user_id)

    -   jar_id: INT, FOREIGN KEY → Donation_Jar(jar_id)

**Relation: Transactions**

-   **Attributes:**

    -   transaction_id: INT, PRIMARY KEY

    -   transaction_amount: DECIMAL(12,2), NOT NULL

    -   transaction_date: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   donation_id: INT, FOREIGN KEY → Donations(donation_id)

    -   transaction_status: TEXT, NOT NULL

    -   payment_method: TEXT

    -   refund_amount: DECIMAL(12,2)

    -   refund_date: DATETIME

    -   refund_status: TEXT

    -   original_transaction_id: INT, FOREIGN KEY →
        Transactions(transaction_id)

**Relation: Reports**

-   **Attributes:**

    -   report_id: INT, PRIMARY KEY

    -   jar_id: INT, FOREIGN KEY → Donation_Jar(jar_id)

    -   title: TEXT, NOT NULL

    -   description: TEXT

    -   amount_spent: DECIMAL(12,2)

    -   evidence_url: TEXT

    -   verified: BOOLEAN, DEFAULT FALSE

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

**Relation: Withdrawal_Requests**

-   **Attributes:**

    -   request_id: INT, PRIMARY KEY

    -   jar_id: INT, FOREIGN KEY → Donation_Jar(jar_id)

    -   fund_raiser_id: INT, FOREIGN KEY → Fundraisers(fund_raiser_id)

    -   amount: DECIMAL(12,2), NOT NULL

    -   purpose: TEXT

    -   request_date: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   status: TEXT, NOT NULL

    -   approved_by: INT, FOREIGN KEY → Users(user_id)

    -   approved_date: DATETIME

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

**Relation: Withdrawals**

-   **Attributes:**

    -   withdrawal_id: INT, PRIMARY KEY

    -   request_id: INT, FOREIGN KEY → Withdrawal_Requests(request_id)

    -   amount: DECIMAL(12,2), NOT NULL

    -   withdrawal_date: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   recipient: TEXT, NOT NULL

    -   evidence_url: TEXT

    -   created_at: DATETIME, DEFAULT CURRENT_TIMESTAMP

    -   updated_at: DATETIME

# Conclusion

**LiftFlow** provides a scalable, effective platform to manage the many
complexities of running a volunteer-driven donation system. The platform
enables smooth interactions between donors, volunteers, and organizers,
supporting essential processes such as onboarding donors, launching and
managing fundraising jars, tracking contributions, and performing
transparent financial analysis. Its structured design, built on strong
data modelling and clear process visualization, ensures trust and ease
of use. With real-time donation updates, automatic validations, and
detailed reporting tools, **LiftFlow** empowers fundraising organizers
to deliver impactful community service while maintaining full oversight
of operations.
