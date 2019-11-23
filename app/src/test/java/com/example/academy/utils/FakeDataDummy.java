package com.example.academy.utils;
import com.example.academy.Data.source.local.entity.CourseEntity;
import com.example.academy.Data.source.local.entity.ModuleEntity;
import com.example.academy.Data.source.remote.response.ContentResponse;
import com.example.academy.Data.source.remote.response.CourseResponse;
import com.example.academy.Data.source.remote.response.ModuleResponse;

import java.util.ArrayList;

public class FakeDataDummy {
    // get data from static list
    public static ArrayList<CourseEntity> generateDummy() {
        // add data into static list
        ArrayList<CourseEntity> course = new ArrayList<>();
        course.add(new CourseEntity("a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah langsung diverifikasi oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi android dengan materi testing, Debugging, Aplication, Aplication UX, Fundamental Aplication Component, Persistent Data Storage, dan Enhanced System Integration. ",
                "100 Hari",
                null,
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"));
        course.add(new CourseEntity("a55",
                "Kotlin Android Developer Expert",
                "Pada Google I/O 2017, Kotlin diumumkan sebagai bahasa pemrograman yang termasuk dalam bahasa kelas satu (First class) yang didukung untuk pembuatan aplikasi Android, selain Java dan C++. Kotlin adalah bahasa pemrograman yang dibuat oleh JetBrains. Google juga akan memastikan bahwa semua fitur baru di Android, framework, IDE dan keseluruhan library, akan dapat bekerja dan terintegrasi baik dengan bahasa pemrograman Kotlin serta interopable dengan fungsi-fungsi Java yang telah ada sehingga memungkinkan para engineer melakukan perubahan bagian tertentu aplikasi dari Java ke Kotlin dan sebaliknya dengan sangat mudah.",
                "50 Hari",
                null,
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg"));
        course.add(new CourseEntity("a47",
                "Menjadi Game Developer Expert",
                "Semua modul dalam kelas ini telah diverifikasi langsung oleh Asosiasi Game Indonesia (AGI) untuk memastikan materi yang diajarkan relevan dan sesuai dengan kebutuhan industri game saat ini. Peserta akan belajar best practice membuat game seperti Script, Sprite, UI, Gameplay, Input Method, Porting ke Android / iOS, Modul Services (Collaboration, Ads, Analytics dan Google Play Games - Update Oktober 2018), Porting ke VR - Cardboard dan Gear VR (New Mei 2018) dengan Unity 3D. Peserta juga akan belajar langsung membuat 8 game yaitu Casual (Update Agustus 2018), Pilah Sampah (New November 2018), Tower Defense (New November 2018), Arcade (Update Maret 2018), Platformer (Update Januari 2019), FPS, Multiplayer (Update Maret 2018), serta Game Interaktif dengan VR (New Mei 2018).",
                "75 Hari",
                null,
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg"));
        course.add(new CourseEntity("a74",
                "Membangun Progressive Web Apps",
                "Progressive Web Apps adalah aplikasi web yang memanfaatkan beragam fitur web modern sehingga dapat menyajikan pengalaman pengguna seperti aplikasi native. PWA mengubah sajian tampilan yang umumnya dibuka melalui halaman browser menjadi jendela aplikasi tersendiri. Selain itu PWA juga memungkinkan konten halaman diakses dalam mode offline, menampilkan pesan pemberitahuan, hingga akses ke hardware dari perangkat seperti halnya native app.",
                "50 Hari",
                null,
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg"));
        course.add(new CourseEntity("a51",
                "Belajar Membuat Aplikasi Android untuk Pemula", "Kelas ini didesain oleh Google Authorized Training Partner untuk developer Android di Indonesia. Peserta akan mempelajari materi dasar Android dalam 30 hari dan diarahkan untuk membuat aplikasi sederhana. Terdapat 27 modul yang juga merupakan bagian dari Kelas Menjadi Android Developer Expert. Peserta disarankan setidaknya memiliki pengetahuan tentang programming Java dan atau pemrograman berorientasi objek. Sistem pembelajaran adalah online (dapat diakses kapanpun dan darimanapun selama tersedia internet) dan kehadiran tatap muka tidak diperlukan. Tools yang diwajibkan untuk kelas belajar Android ini adalah Android Studio. Peserta harus submit satu proyek akhir yang akan direview oleh developer expert untuk mendapatkan sertifikat dari kelas ini. Bila menginginkan materi yang lebih komprehensif, silahkan mengikuti kelas Menjadi Android Developer Expert.",
                "30 Hari",
                null,
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg"));
        return course;

    }

    public static ArrayList<ModuleEntity> generateDummyModules(String courseId){
        ArrayList<ModuleEntity> modules= new ArrayList<>();
        // insert file use object ModuleEntity
        modules.add(new ModuleEntity(String.format("%sm1", courseId),
                courseId,
                "Modul 0 : Introduction",
                0,
                null));
        modules.add(new ModuleEntity(String.format("%sm2", courseId),
                courseId,
                "Modul 1 : Teori 1",
                1,
                null));
        modules.add(new ModuleEntity(String.format("%sm3", courseId),
                courseId,
                "Latihan 1",
                3,
                null));
        modules.add(new ModuleEntity(String.format("%sm4", courseId),
                courseId,
                "Bedah Kode 1",
                3,
                null));
        modules.add(new ModuleEntity(String.format("%sm5", courseId),
                courseId,
                "Modul 2 : Teori 2",
                4,
                null));
        modules.add(new ModuleEntity(String.format("%sm6", courseId),
                courseId,
                "Latihan 2",
                5,
                null));
        modules.add(new ModuleEntity(String.format("%sm7", courseId),
                courseId,
                "Bedah Kode 2",
                6,
                null));

        return modules;

    }

    // get all fil efrom generateCourse
    public static CourseEntity getCourse(String courseId){
        for (int i = 0; i <generateDummy().size() ; i++) {
            // get dummmy file from array list
            CourseEntity entity= generateDummy().get(i);
            // if file not null
            if(entity.getCourseId().equals(courseId)){
                // return file
                return entity;
            }
        }
        // else return null
        return null;

    }

    public static ArrayList<CourseResponse> generateRemoteDummyCourse(){
        ArrayList<CourseResponse> course= new ArrayList<>();
        course.add(new CourseResponse(
                "a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
                "100 Hari",
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"));

        course.add(new CourseResponse(
                "a55",
                "Kotlin Android Developer Expert",
                "Pada Google I/O 2017, Kotlin diumumkan sebagai bahasa pemrograman yang termasuk dalam bahasa kelas satu (First class) yang didukung untuk pembuatan aplikasi Android, selain Java dan C++. Kotlin adalah bahasa pemrograman yang dibuat oleh JetBrains. Google juga akan memastikan bahwa semua fitur baru di Android, framework, IDE dan keseluruhan library, akan dapat bekerja dan terintegrasi baik dengan bahasa pemrograman Kotlin serta interopable dengan fungsi-fungsi Java yang telah ada sehingga memungkinkan para engineer melakukan perubahan bagian tertentu aplikasi dari Java ke Kotlin dan sebaliknya dengan sangat mudah.",
                "50 hari",
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg"));

        course.add(new CourseResponse(
               "a47",
                "Menjadi Game Developer Expert",
                "Semua modul dalam kelas ini telah diverifikasi langsung oleh Asosiasi Game Indonesia (AGI) untuk memastikan materi yang diajarkan relevan dan sesuai dengan kebutuhan industri game saat ini. Peserta akan belajar best practice membuat game seperti Script, Sprite, UI, Gameplay, Input Method, Porting ke Android / iOS, Modul Services (Collaboration, Ads, Analytics dan Google Play Games - Update Oktober 2018), Porting ke VR - Cardboard dan Gear VR (New Mei 2018) dengan Unity 3D. Peserta juga akan belajar langsung membuat 8 game yaitu Casual (Update Agustus 2018), Pilah Sampah (New November 2018), Tower Defense (New November 2018), Arcade (Update Maret 2018), Platformer (Update Januari 2019), FPS, Multiplayer (Update Maret 2018), serta Game Interaktif dengan VR (New Mei 2018).",
                "75 hari",
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg"));

        course.add(new CourseResponse(
                "a74",
                "Membangun Progressive Web Apps",
                "Progressive Web Apps adalah aplikasi web yang memanfaatkan beragam fitur web modern sehingga dapat menyajikan pengalaman pengguna seperti aplikasi native. PWA mengubah sajian tampilan yang umumnya dibuka melalui halaman browser menjadi jendela aplikasi tersendiri. Selain itu PWA juga memungkinkan konten halaman diakses dalam mode offline, menampilkan pesan pemberitahuan, hingga akses ke hardware dari perangkat seperti halnya native app.",
                "50 hari",
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg"));

        return course;

    }

    public static ArrayList<ModuleResponse> generateRemoteDummyModules(String courseId){
        ArrayList<ModuleResponse> modules= new ArrayList<>();
        modules.add(new ModuleResponse(
                String.format("%sm1", courseId),
                courseId,
                "Modul 0 : Introduction",
                0));

        modules.add(new ModuleResponse(String.format("%sm2", courseId),
                courseId,
                "Modul 1: Teori 1",
                1));

        modules.add(new ModuleResponse(String.format("%sm3", courseId),
                courseId,
                "Latihan 1",
                2));

        modules.add(new ModuleResponse(String.format("%sm4", courseId),
                courseId,
                "Bedah Kode 1",
                3));

        modules.add(new ModuleResponse(String.format("%sm5",courseId),
                courseId,
                "Modul 2 : Teori 2",
                4));

        modules.add(new ModuleResponse(String.format("%sm6", courseId),
                courseId,
                "Latihan 2",
                4));

        modules.add(new ModuleResponse(String.format("%sm7", courseId),
                courseId,
                "Bedah kode 2",
                4));

        return modules;

    }

    public static ContentResponse generateRemoteDummyContent(String moduleId){
        return new ContentResponse(moduleId, "This is a dummy content ");
    }

}