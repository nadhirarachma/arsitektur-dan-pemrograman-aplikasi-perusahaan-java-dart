# Tutorial APAP
## Authors
* **Nadhira Rachma Salsabila Anandra** - *2006484974* - *C*

---
## Tutorial 5

### What I have learned today
1. Apa itu Postman? Apa kegunaannya?
- Postman merupakan sebuah tools berupa aplikasi yang dapat digunakan untuk membangun, menguji, mengubah, dan mendokumentasikan API (Application Programming Interface). Postman dapat memfasilitasi pengiriman dan penerimaan HTTP request maupun response, membuat environment, mengubah API menjadi kode untuk berbagai bahasa pemrograman, membuat collections untuk testing sehingga tidak diperlukannya kode HTTP client network, dan masih banyak lagi.

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.
- @JsonIgnoreProperties : mendefinisikan property atau atribut yang dapat diabaikan dalam suatu class khususnya ketika mengembalikan respon berupa JSON.
- @JsonProperty : mendefiniskan nama property atau atribut yang akan digunakan ketika mengembalikan respon berupa JSON.

3. Apa kegunaan atribut WebClient?
- WebClient merupakan sebuah non-blocking reactive web client yang berfungsi untuk melakukan HTTP request dan mengembalikan data dari sebuah URI tertentu.

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
- ResponseEntity : merepresentasikan keselurahan HTTP response berupa status code, header, dan body.
- BindingResult : menyimpan hasil dari proses validasi dan binding yang mengandung error yang mungkin terjadi. BindingResult haruslah muncul setelah proses validasi dari objek terjadi dikarenakan jika tidak Spring tidak berhasil untuk memvalidasi objek dan akan melempar exception. 

Referensi: 
- https://www.javatpoint.com/postman
- https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
- https://howtodoinjava.com/spring-webflux/webclient-get-post-example/
- https://www.baeldung.com/spring-response-entity
- https://zetcode.com/spring/bindingresult/

---
## Tutorial 4

### What I have learned today
1. Jelaskan perbedaan th:include dan th:replace!
- th:include : memasukkan konten fragmen spesifik pada host tag tanpa tag dari fragmen. 
- th:replace : mengganti host tag dengan konten fragmen spesifik yang diikuti oleh tag dari fragmen.

2. Jelaskan apa fungsi dari th:object! 
- th:object : merupakan sebuah bean object yang menyimpan atribut-atribut beserta nilai-nilai yang didapatkan dari input pada form.

3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?
- '*' = mendefinisikan atribut-atribut mana yang akan dievaluasi dari suatu objek/variabel.
- $ = mendefinisikan objek/variabel mana yang akan dievaluasi.

Referensi: 
- https://attacomsian.com/blog/thymeleaf-fragments
- https://frontbackend.com/thymeleaf/working-with-forms-in-thymeleaf
- https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html

---
## Tutorial 3

### What I have learned today
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
- @AllArgsConstructor : membuat sebuah constructor dengan 1 parameter untuk setiap field pada class.
- @NoArgsConstructor : membuat sebuah constructor tanpa parameter untuk setiap field pada class.
- @Setter : membuat setter.
- @Getter : membuat getter.
- @Entity : mendefinisikan bahwa sebuah class merupakan JPA entity yang telah dipetakan ke tabel pada database.
- @Table : mendefinisikan nama tabel pada database yang digunakan untuk mapping.

2. Pada class CourseDb, terdapat method findByCodeUsingQuery dan findByCode, apakah perbedaan kedua method tersebut? Jelaskan juga kegunaan @Query pada method findByCodeUsingQuery!
- findByCodeUsingQuery maupun findByCode sama-sama akan mengembalikan sebuah objek bertipe Optional berdasarkan code course. Perbedaan terletak pada penggunaan anotasi @Query pada method findByCodeUsingQuery dimana pencarian code akan dilakukan menggunakan query sedangkan findByCode tidak.
- @Query : mendefinisikan query yang akan dieksekusi pada model untuk sebuah method.

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn
- @JoinTable : digunakan ketika entitas pada tabel yang berbeda memiliki hubungan. Anotasi menyimpan ID dari kedua entitas pada tabel yang berbeda.
- @JoinColumn : digunakan ketika entitas memiliki hubungan langsung seperti adanya foreign key antar kedua entitas. Anotasi menyimpan ID dari entitas lain pada kolom baru pada tabel yang sama.

4. Pada class Pengajar, digunakan anotasi @JoinColumn pada atribut Course, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull
- name : mendefinisikan column yang akan menjadi foreign key.
- referencedColumnName : mendefinisikan column yang dijadikan referensi oleh foreign key.
- nullable : mendefinisikan bahwa return value dapat berupa null pada situasi tertentu.
- @NotNull : mendefinisikan bahwa method tidak dapat mengembalikan nilai null atau field yang memiliki anotasi tidak boleh bernilai null.
- Perbedaan nullable dan @NotNull terletak pada kemungkinan pengembalian nilai null.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
- FetchType.LAZY : mengambil entitas yang berhubungan saja pada suatu relationship dimamna atribut yang diload berupa atribut yang dibutuhkan saja.
- CascadeType.ALL : mendefiniskan seluruh operasi cascade seperti persist, merge, refresh, remove, serta detach dimana operasi pada salah satu entitas akan berdampak pada seluruh entitas lain yang berhubungan.
- FetchType.EAGER : mengambil seluruh elemen pada relationship ketika mengambil root entity dimana seluruh atribut akan diload dalam waktu yang bersamaan.

Referensi: 
- https://projectlombok.org/features/constructor
- https://projectlombok.org/features/GetterSetter
- https://www.baeldung.com/spring-data-jpa-query
- https://javakeypoint.wordpress.com/2020/04/21/difference-between-joincolumn-and-jointable-in-hibernate/
- https://www.objectdb.com/api/java/jpa/JoinColumn
- https://thorben-janssen.com/entity-mappings-introduction-jpa-fetchtypes/#FetchTypeEAGER_8211_Fetch_it_so_you8217ll_have_it_when_you_need_it
- https://howtodoinjava.com/hibernate/hibernate-jpa-cascade-types/

---
## Tutorial 2

### What I have learned today

1. Cobalah untuk menambahkan sebuah Course dengan mengakses link berikut:
http://localhost:8080/course/add?code=APAP&nameCourse=APAP%20Tutorial2&description=2020%20Fasilkom&jumlahSks=3 
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
note : Apabila Mahasiswa merubah port pada application.properties, silahkan atur localhost sesuai port yang kalian gunakan
- Ketika mengakses link tersebut, ditampilkan Whitelabel Error Page yang diakibatkan oleh view template yang dicantumkan pada Controller (“add-course”) belum dibuat.

2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat
- Anotasi @Autowired pada class Controller merupakan implementasi dari konsep dependency injection. Dengan menambahkan @Autowired pada class Controller, secara otomatis CourseService akan terinjeksi atau tidak diperlukannya untuk menulis ulang atau membuat objek baru. 

3. Cobalah untuk menambahkan sebuah Course dengan mengakses link berikut: http://localhost:8080/course/add?code=APAP&nameCourse=APAP%20Tutorial2&jumlahSks=3   Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
- Ketika mengakses link tersebut, ditampilkan Whitelabel Error Page yang diakibatkan oleh tidak adanya request parameter 'description' yang didefinisikan pada link.

4. Jika Papa APAP ingin melihat Course dengan kode APAP, link apa yang harus diakses?
http://localhost:8080/course/view?code=APAP

5. Tambahkan 1 contoh Course lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/course/viewAll, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu. 
- Daftar course beserta informasi lengkap dari course akan ditampilkan. ![bukti](bukti-viewAll-tutorial2.png)

Referensi: 
- https://www.baeldung.com/spring-autowire

---
## Tutorial 1

### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda
juga boleh menambahkan catatan apapun di bagian ini)

### GitLab
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue Tracker merupakan sebuah fitur yang memfasilitasi untuk melakukan kolaborasi seperti berdiskusi terkait implementasi kode, maupun melaporkan adanya error dan bugs.
2. Apa perbedaan dari git merge dan git merge --squash?
Git merge akan menampilkan masing-masing commit yang telah dilakukan satu per satu sedangkan git merge --squash akan menggabungkan seluruh perubahan yang dilakukan pada dokumen menjadi satu buah commit saja.
3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
Version Control System memfasilitasi pengembang untuk mengidentifikasi perubahan maupun kesalahan, membandingkan, serta mengelola dokumen-dokumen yang digunakan dalam mengembangkan suatu aplikasi.

### Spring
4. Apa itu library & dependency?
Library merupakan modul-modul yang berisi kode-kode atau program yang sudah tersedia dan dapat digunakan untuk mempermudah dengan hanya perlu dilakukan import. Dependency merupakan suatu istilah yang menggambarkan ketergantungan suatu objek dengan objek lainnya.
5. Apa itu Gradle? Mengapa kita menggunakan Gradle? Apakah ada alternatif dari Gradle?
Gradle merupakan sebuah open-source build automation tool yang mempermudah dalam mengembangkan suatu aplikasi melalui proses otomatisasi. Gradle digunakan dikarenakan kinerja yang cepat dan mudah untuk dikustomisasi sesuai dengan keinginan pengguna. Alternatif lain dari Gradle adalah Maven, Bazel, Jenkins, GNU Make, Bamboo, dan masih banyak lagi.
6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
Spring framework dapat digunakan untuk mengembangkan aplikasi mobile maupun aplikasi desktop.
7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
@RequestParam digunakan untuk mengekstraksi data berupa query parameter sedangkan @PathVariable digunakan untuk mengekstraksi data secara langsung dari URI. Contoh penggunaan @RequestParam adalah pada http://localhost:8080/penghitung-BMI?berat=55&tinggi=155 sedangkan penggunaan @PathVariable adalah pada http://localhost:8080/penghitung-BMI/62/159.

Referensi:
- https://docs.gitlab.com/ee/user/project/issues/
- https://docs.microsoft.com/en-us/azure/devops/repos/git/merging-with-squash?view=azure-devops
- https://reqtest.com/requirements-blog/what-are-benefits-of-version-control/
- https://www.javatpoint.com/gradle
- https://spring.io/blog/2010/11/20/spring-into-mobile-application-development
- https://javarevisited.blogspot.com/2017/10/differences-between-requestparam-and-pathvariable-annotations-spring-mvc.html#axzz7eD8NGyoa


### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda
sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [ ] Apa saja konsep-konsep dasar yang harus dipahami dalam Spring?

Karena ...
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitLab pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))