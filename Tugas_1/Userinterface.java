import java.sql.SQLOutput;
import java.util.Scanner;
public class UserInterface {
    public static void tampilkanMenu(){
        System.out.println();
        System.out.println("+===============+");
        System.out.println("|  PILIH MENU:  |");
        System.out.println("+---------------+");
        System.out.println("   [C] : Create |");
        System.out.println("   [R] : Read   |");
        System.out.println("   [U] : Update |");
        System.out.println("   [D] : Delete |");
        System.out.println("   [X] : Exit   |");
        System.out.println("+===============+");
    }
    public static void main(String[] args) {
        Database db = new Database();
        System.out.println(" SELAMAT DATANG DI APLIKASI SIMPLE CRUD TEXT DATABASE ");
        while(true){
            tampilkanMenu();
            Scanner in = new Scanner(System.in);
            System.out.print("Pilih  :  ");
            String pilihan = in.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan){
                case "C":
                    System.out.println("INFO: Anda memilih menu Create");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM            : ");
                    String nim = in.nextLine();
                    System.out.print("NAMA MAHASISWA : ");
                    String nama = in.nextLine();
                    System.out.print("ALAMAT         : ");
                    String alamat = in.nextLine();
                    System.out.print("SEMESTER       : ");
                    int semester = in.nextInt();
                    System.out.print("SKS            : ");
                    int sks = in.nextInt();
                    System.out.print("IPK            : ");
                    double ipk = in.nextDouble();
                    System.out.println("-----------------------------------------------------");
                    boolean status = db.insert(nim,nama,alamat,semester,sks,ipk);
                    if(status == true) {
                        System.out.println("DATA BARU BERHASIL DI TAMBAHKAN");
                    }else{
                        System.out.println("NIM "+nim+" sudah ada di database");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("-----------------------------------------------------");
                    break;
                case "R":
                    System.out.println("INFO : Anda memilih menu Read");
                    db.view();

                    break;
                case "U":
                    System.out.println("INFO : Anda memilih menu Update");
                    db.view();
                    System.out.print("Input Key (NIM Mahasiswa yang akan diupdate): ");
                    String key = in.nextLine();
                    int index = db.search(key);

                    if(index >= 0) {
                        System.out.println("Anda akan meng-update data "+ db.getDatabase12().get(index));
                        System.out.println("-----------------------------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM            : ");
                        String Nim = in.nextLine();
                        System.out.print("NAMA MAHASISWA : ");
                        String Nama = in.nextLine();
                        System.out.print("ALAMAT         : ");
                        String Alamat = in.nextLine();
                        System.out.print("SEMESTER       : ");
                        int Semester = in.nextInt();
                        System.out.print("SKS            : ");
                        int Sks = in.nextInt();
                        System.out.print("IPK            : ");
                        double Ipk = in.nextDouble();
                        System.out.println("-----------------------------------------------------");
                        status = db.update(index,Nim,Nama,Alamat,Semester,Sks,Ipk);
                        if(status == true) {
                            System.out.println("DATA BERHASIL DI PERBAHARUI");
                        }else{
                            System.err.println("GAGAL MEMPERBAHARUI DATA");
                        }
                        System.out.println("-----------------------------------------------------");
                    }else{
                        System.err.println("Mahasiswa dengan NIM : "+ key + " tidak ada di database");
                    }
                    break;
                case "D":
                    System.out.println("INFO : Anda memilih menu Delete");
                    db.view();
                    System.out.print("Input Key (NIM Mahasiswa yang akan di hapus):");
                    key = in.nextLine();
                    index = db.search(key);
                    if(index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA "+db.getDatabase12().get(index)+"? Y/N");
                        System.out.println("pilih   :  ");
                        pilihan = in.nextLine();
                        if(pilihan.equalsIgnoreCase("Y")){
                            status = db.delete(index);

                        }
                    }else{
                        System.err.println("Mahasiswa dengan NIM: "+key+" tidak ada di database");
                    }
                    break;
                case "X":
                    System.out.println("INFO : Anda memilih menu EXIT");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.print("Pilih :   ");
                    pilihan = in.nextLine();
                    if(pilihan.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }
                    break;
            }
        }

    }
}
