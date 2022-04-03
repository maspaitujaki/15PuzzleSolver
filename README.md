# 15PuzzleSolver
Repositori ini berisi program java yang dapat mencari solusi dari sebuah _15 Puzzle_

# Requirement
Minimal memiliki Java SE dan RE 8 terinstal 

Selain itu, untuk memudahkan kompilasi disarankan memiliki [make](https://stackoverflow.com/questions/32127524/how-to-install-and-use-make-in-windows) terinstal

# Cara menggunakan program utama
Pertama, buka folder src pada console. Untuk menjalankan program dengan Puzle yang digenerate random jalankan perintah dibawah
```
make random
```
Jika ingin menggunakan file konfigurasi khusus, silakan memasukkan file ke dalam folder config dengan format angka 16 adalah ubin kosong. Contoh:
```
1 3 7 6
5 2 16 4
13 10 12 8
14 9 11 15
```
lalu, jalankan perintah berikut pada console
```
make fromfile FileName="XXX"
```
dengan XXX adalah nama file konfigurasi yang ingin digunakan.
JIKA tidak memiliki make terinstal, jalankan perintah berikut untuk mengcompile program
```
javac *.java
```
Selanjutnya, jalankan
```
java Main
```
untuk Puzzle random, atau
```
java Main XXX
```
dengan XXX nama file konfigurasi untuk menggunakan konfigurasi tertentu

# Author
| Nama      | NIM | Kelas     | Github|
| :---        |    :----:   |          :---: | :---: |
| Dimas Faidh Muzaki      | 13520156       | K03    | https://github.com/maspaitujaki|
