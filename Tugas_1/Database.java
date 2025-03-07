import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Database {
   private ArrayList<Mahasiswa> database12 = new ArrayList<>();
   private String filename = "src/database12.csv";
   private Path path = Path.of(filename);

    public Database() {
        open();
    }

    public ArrayList<Mahasiswa> getDatabase12() {
        return database12;
    }

    public void open(){
       try {
           List<String> lines = Files.readAllLines(path);
           database12 = new ArrayList<>();
           for (int i = 1; i<lines.size(); i++){
             String line = lines.get(i);
             String[]element = line.split(",");
             String nim = element[0];
             String nama = element[1];
             String alamat = element[2];
             int semester = Integer.parseInt(element[3]);
             int sks = Integer.parseInt(element[4]);
             double ipk = Double.parseDouble(element[5]);
             Mahasiswa mhs = new Mahasiswa (nim, nama, alamat, semester, sks, ipk);
             database12.add(mhs);

           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }
    public void save(){
       StringBuilder sb = new StringBuilder();
       sb.append("NIM,NAMA,ALAMAT (KOTA),SEMESTER,SKS,IPK\n");
       if (!database12.isEmpty()){
       for (int i = 0; i < database12.size(); i++){
            Mahasiswa mhs = database12.get(i);
            String line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemester() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
            sb.append(line);
           }

        }
        try {
            Files.writeString(path,sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void view(){
        System.out.println("====================================================================================");
        System.out.printf("| %-8.8S |","NIM");
        System.out.printf(" %-20.20S |","NAMA");
        System.out.printf(" %-20.20S |","ALAMAT");
        System.out.printf(" %8.8S  |","SEMESTER");
        System.out.printf(" %3.3S  |","SKS");
        System.out.printf(" %4.4S  |%n","IPK");
        System.out.println("------------------------------------------------------------------------------------");
        for (Mahasiswa mhs : database12){
            System.out.printf("| %-8S |",mhs.getNim());
            System.out.printf(" %-20.20S |",mhs.getNama());
            System.out.printf(" %-20.20S | ",mhs.getAlamat());
            System.out.printf(" %8.8S | ",mhs.getSemester());
            System.out.printf(" %3.3S | ", mhs.getSks());
            System.out.printf(" %4.4s | ",mhs.getIpk());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    boolean insert (String nim,String nama,String alamat,int semester,int sks,double ipk){
       boolean status = true;
       // cek primary key
        if(!database12.isEmpty()){
            for (int i = 0; i < database12.size(); i++){
                if(database12.get(i).getNim().equalsIgnoreCase(nim)){
                    status = false;
                    break;
                }
            }
        }
        if(status == true){
            Mahasiswa mhs = new Mahasiswa(nim,nama,alamat,semester,sks,ipk);
            database12.add(mhs);
            save();
        }

       return status;

    }
    public int search(String nim) {
       int index = -1;
       if(!database12.isEmpty()){
           for (int i = 0; i < database12.size(); i++){
               if(database12.get(i).getNim().equalsIgnoreCase(nim)){
                   index = i;
                   break;
               }
           }
       }
       return index;
    }
    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk){
       boolean status = false;
        if(!database12.isEmpty()) {
            //update
            if (index >= 0 && index < database12.size()) {
                Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                database12.set(index, mhs);
                save();
                status = true;
            }
        }
       return status;
    }
    public boolean delete (int index){
        boolean status = false;
       if(database12.isEmpty()){
           database12.remove(index);
           save();
           status = true;
       }
        return status;
        }

    }

