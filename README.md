# Buying-selling-house
User Manual / README
Real Estate Management System
Overview
The Real Estate Management System allows users to register as customers or owners, view available houses, and make or manage offers on properties. The system provides a console-based interface for user interaction.

Getting Started
Prerequisites:
Java Development Kit (JDK) installed (version 8 or higher).
MySQL server set up with a database named bas.
JDBC driver for MySQL added to your project's classpath.

Database Setup:
First Create a Database Called "Bas (1)"
then import the bas.sql into the database

Running the Application:
Compile the Java files using your preferred IDE or command-line tool.
Run the Main class to start the application.
Follow the on-screen prompts to register or log in.


User Roles
Customer: Can view available houses and make offers.
Owner: Can add houses for sale and view offers made on their properties.
Admin: Can manage users and oversee the system.

Features
User Registration and Authentication: Users can create accounts and log in securely.
House Management: Owners can add and manage their property listings. Customers can browse available houses.
Offer Management: Customers can make offers on properties, and owners can view and manage these offers.
Role-Specific Menus: Users navigate through menus based on their roles, ensuring a tailored experience.


User Interaction Flow
1.Login/Register:
Users start by either logging in or registering for an account.
2.Role-Based Navigation:
After login, users are directed to their respective menus based on their roles.
Owners have options to add houses and view offers, while customers can view houses and make offers.
3.Making Offers:
Customers can select a house and submit an offer, which is processed and stored in the database.
4.Viewing Offers:
Owners can view offers made on their houses, allowing them to accept or reject them.

roubleshooting
Database Connection Issues: Ensure that the database connection details in HouseDAO and OfferDAO are correct.
MySQL Server Issues: Check that the MySQL server is running and accessible.
Data Integrity: Make sure that the database schema is correctly set up as per the provided SQL commands.

Conclusion
This documentation outlines the design and functionality of the Real Estate Management System. It serves as a guide for developers and users, providing insights into the architecture and usage of the application. For further assistance or feature requests, please contact the development team. Your feedback is valuable for improving the system!
