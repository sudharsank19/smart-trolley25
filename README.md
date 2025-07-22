# 🛒 Smart‑Trolley25 (QR Code Based – Software Simulation)

A software-only smart trolley system that simulates the shopping experience using **QR code-based product scanning** and **automated billing**. Designed as a final year project, this system enhances the virtual retail experience and removes the need for traditional checkout.

---

## 📌 Features

- 📷 **QR Code Scanning**: Detects and decodes product QR codes using webcam or image input.
- 🧾 **Live Billing Interface**: Automatically updates the cart and total price as products are scanned.
- 🛍️ **Virtual Trolley System**: No physical hardware needed — fully GUI-based.
- 🗃️ **Product Database**: Each QR code maps to product info (name, price, category).
- 📄 **Receipt Generation**: View or export a digital bill after shopping.

---

## 📁 Project Structure
### smart-trolley25/
### ├── qr_scanner/ # QR code reader and handler
### │ └── qr_reader.py
### ├── products/ # JSON or CSV product data
### │ └── products.json
### ├── ui/ # GUI or frontend interface
### │ └── main.py / app.py
### ├── static/ # Images, styles (if using web-based UI)
### ├── docs/ # Project report, PPT, etc.
### ├── README.md # Project overview
### └── requirements.txt # Python dependencies


## ⚙️ Technologies Used

- **Python** (main programming language)
- **OpenCV** – webcam access and image processing
- **pyzbar** or **opencv-python** – QR code detection
- **Tkinter / PyQt / Flask** – for GUI (based on your stack)
- **JSON / CSV** – product database
- **Git & GitHub** – version control and collaboration

---


