# 🛒 Smart‑Trolley-System 

A software-only smart trolley system that simulates the shopping experience using **QR code-based product scanning** and **automated billing**. Designed as a system enhances the virtual retail experience and removes the need for traditional checkout.

## 📌 Features

- 📷 **QR Code Scanning**: Detects and decodes product QR codes using webcam or image input.
- 🧾 **Live Billing Interface**: Automatically updates the cart and total price as products are scanned.
- 🛍️ **Virtual Trolley System**: No physical hardware needed — fully GUI-based.
- 🗃️ **Product Database**: Each QR code maps to product info (name, price, category).
- 📄 **Receipt Generation**: View or export a digital bill after shopping.

## 🛠️ Technologies Used

### 📌 Languages & Frameworks
- **Java** – Core application logic and activities
- **XML** – Layouts, drawables, themes, and UI design
- **SQLite** – Lightweight local database for storing user and product data

### 📌 Android Components
- **Activities** – Screens for login, register, QR scan/generate, product list, etc.
- **RecyclerView** – Used to display the product list efficiently
- **Intents** – Navigation and data transfer between activities
- **SQLiteOpenHelper** – Manages database creation and version management

### 📌 UI & Material Design
- **Material Components** – For modern and responsive UI
- **Vector Drawables** – Custom icons in XML format
- **Custom Layouts** – Activity and item layouts in `res/layout/`

### 📌 QR Code Functionality
- **ZXing Library** *(or similar)* – For scanning and generating QR codes
- **PNG, JPG Images** – For logos and product visuals

### 📌 Resource Management
- **colors.xml** – Manages app color palette
- **strings.xml** – Stores UI text for easy localization
- **styles.xml** – Application themes and widget styling

### 📌 Features Implemented
- **User Authentication** – Login and registration with SQLite
- **Splash Screen** – Initial launch screen
- **QR Code Generator and Scanner**
- **Product CRUD Operations** – Local product management
- **Dummy Payment Integration**

## 📂 Project Directory Structure

```bash
app/
├── manifests/
│
├── java/
│   └── com/example/qrcodescanner/
│       ├── models/
│       │   └── Product.java
│       ├── DatabaseHelper.java
│       ├── LoginActivity.java
│       ├── RegisterActivity.java
│       ├── SplashActivity.java
│       ├── MainActivity.java
│       ├── QRCodeGeneratorActivity.java
│       ├── ProductListActivity.java
│       ├── PaymentActivity.java
│       ├── ProductAdapter.java
│       └── ProductItem.java
│
├── res/
│   ├── drawable/
│   │   ├── darkfantasy.jpg
│   │   ├── dashboard.xml
│   │   ├── facebook.xml
│   │   ├── fanta.jpg
│   │   ├── google.xml
│   │   ├── gradient.xml
│   │   ├── home.xml
│   │   ├── ic_launcher_background.xml
│   │   ├── ic_launcher_foreground.xml
│   │   ├── logo.png
│   │   ├── logo1.png
│   │   ├── person.xml
│   │   ├── qrscan.png
│   │   ├── rounded_edittext.xml
│   │   ├── search.xml
│   │   ├── shopping.xml
│   │   └── sprite.jpg
│   │
│   ├── layout/
│   │   ├── activity_login.xml
│   │   ├── activity_register.xml
│   │   ├── activity_splash.xml
│   │   ├── activity_main.xml
│   │   ├── activity_qrcode_generator.xml
│   │   ├── activity_product_list.xml
│   │   ├── activity_payment.xml
│   │   ├── product_item.xml
│   │   ├── product_list_item.xml
│   │   └── product_list_item1.xml
│   │
│   ├── mipmap/                         # App icons
│   ├── values/
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   ├── styles.xml
│   │   └── themes/                    # Theme definitions
│   └── xml/







