
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Main {

    public static void main(String[] args) throws IOException {
        FileReader plik = new FileReader("dane.txt");
        BufferedReader bufor = new BufferedReader(plik);
        String readLine = "";


        Scanner scanner = new Scanner(new File("dane.txt"));  //opening the file stream
        scanner.useDelimiter(System.lineSeparator());
        Scanner scanner2 = new Scanner(new File("schools.txt"));

        int licznik = 0;
        List<Uczen> listaUczniow = new LinkedList<>(); // list of students
        Pattern pattern = Pattern.compile(";"); //sets the pattern for student separation in the file
        while (scanner.hasNext()) {

            if (scanner.hasNext(pattern)) {
                // if one student's info ends (which is signalled by a semicolon in dane.txt) create a new Student
                // Object and add it to the Students list
                ((LinkedList<Uczen>) listaUczniow).addLast(new Uczen());

                scanner.next(); // skip the semicolon
                licznik = 0; // reset the counter to allow for name and points to be set
                continue;
            }


            if (licznik == 0) ((LinkedList<Uczen>) listaUczniow).getLast().SetImie(scanner.next()); // sets name

            if (licznik == 1) ((LinkedList<Uczen>) listaUczniow).getLast().SetPunkty(scanner.nextInt()); // sets points

            if (licznik > 1)
                ((LinkedList<Uczen>) listaUczniow).getLast().SetPrefSzkola(scanner.nextInt(), scanner.next()); // stores school preference as a map

            licznik++;
        }


        int counter = 0;

        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        for(Uczen uczen : listaUczniow) {

            for (int i = 0; i < listaUczniow.size(); i++) {
                for (Map.Entry<Integer, String> entry : listaUczniow.get(i).GetPrefSzkola().entrySet()) {
                    newMap.put(entry.getValue(), entry.getKey());
                }
            }
        }

        List<Integer> min = new ArrayList<>();
        for(Uczen uczen : listaUczniow) {
            min.add(Collections.min(newMap.values()));

        }

        School school = new School();

        List<String> ls = new ArrayList<>();
            while (scanner2.hasNext()) {
                for (int i = 0; i < listaUczniow.size(); i++) {
                    //school.getSchoolStudent().put(scanner2.nextLine(), "");
                    ls.add(scanner2.nextLine());
                }
            }

        Map<String, Integer> imiePunkt = new HashMap<>();
        for(int i = 0; i < listaUczniow.size(); i++) {
            imiePunkt.put(listaUczniow.get(i).GetImie(), listaUczniow.get(i).GetPunkty());
        }
        for(int i = 1000; i < listaUczniow.size()+1000; i++) {
        String a = "1";
        a+= a;

        school.getSchools().put("szkola1", i);
        school.getSchools().put("szkola2", i);
        school.getSchools().put("szkola3", i);

        }

        System.out.println("szkoly: " + school.getSchools());
        System.out.println("szkoly: " + min.get(0));
        System.out.println("szkoly: " +listaUczniow.get(0).GetPrefSzkola().get(min.get(0)));
        System.out.println("szkoly: " +school.getSchools().get(listaUczniow.get(0).GetPrefSzkola().get(min.get(0))));
        System.out.println("szkoly: " + imiePunkt);

        System.out.println("szkoly: " +imiePunkt.get(school.getSchools().get(listaUczniow.get(0).GetPrefSzkola().get(min.get(0)))));

        // ALGORYTM START
        while(counter < listaUczniow.size()) {
            for (int i = 0; i < listaUczniow.size(); i++) {
                for (int j = 0; j < listaUczniow.size(); j++) {
                    // check for highest scores first, then preference
                    if(school.getSchools().get(listaUczniow.get(i).GetPrefSzkola().get(min.get(i))) > 1000) {

                        listaUczniow.get(i).SetWynikRekrutacji(listaUczniow.get(i).GetPrefSzkola().get(min.get(i)));
                        school.getSchools().put(listaUczniow.get(i).GetPrefSzkola().get(min.get(i)), listaUczniow.get(i).GetPunkty()); // nazwa szkoly -> punkty

                    }
                    else if(school.getSchools().get(listaUczniow.get(i).GetPrefSzkola().get(min.get(i))) < 1000 &&
                            listaUczniow.get(i).GetPunkty() > imiePunkt.get(school.getSchools().get(listaUczniow.get(i).GetPrefSzkola().get(min.get(i))))) {                      //imie -> punkty

                        listaUczniow.get(i).SetWynikRekrutacji(listaUczniow.get(i).GetPrefSzkola().get(min.get(i)));
                        school.getSchools().replace(listaUczniow.get(i).GetPrefSzkola().get(min.get(i)), listaUczniow.get(i).GetPunkty()); //trzeba zamienic bylego na nowego ucznia
                        school.getSchoolStudent().replace(listaUczniow.get(i).GetPrefSzkola().get(min.get(i)),  listaUczniow.get(i).GetImie());
                    }



/*
                    // now check for preference
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola1") < listaUczniow.get(i).GetSzkolaPref().get("szkola1") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola1");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola1").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);
                    }
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola2") < listaUczniow.get(i).GetSzkolaPref().get("szkola2") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola2");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola2").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);

                    }
                    if (listaUczniow.get(j).GetSzkolaPref().get("szkola3") < listaUczniow.get(i).GetSzkolaPref().get("szkola3") && !listaUczniow.get(j).isSatisfied()) {
                        listaUczniow.get(j).SetWynikRekrutacji("szkola3");
                        if (listaUczniow.get(j).GetSzkolaPref().get("szkola3").equals(Collections.min(listaUczniow.get(j).GetSzkolaPref().values())))
                            listaUczniow.get(j).setSatisfied(true);

                    }*/
                }

            }
        counter++;
        }
                /////////////////////
                ////////////////
                //TESTY
                ////////////////////
        for(Uczen uczen : listaUczniow) {
            uczen.GetSzkolaPref().remove("szkola1");
        }

                System.out.println("to jest uczen nr 1: " + listaUczniow.get(1).GetImie());
                System.out.println("to jest uczen nr 0: " + listaUczniow.get(0).GetSzkolaPref().get("szkola1"));
                System.out.println("wynik rekrutacji uczen nr 0: " + listaUczniow.get(0).GetWynikRekrutacji());
                System.out.println("wynik rekrutacji uczen nr 1: " + listaUczniow.get(1).GetWynikRekrutacji());
                System.out.println("wynik rekrutacji uczen nr 2: " + listaUczniow.get(2).GetWynikRekrutacji());
                System.out.println("min value: " + min);
                System.out.println("listSchool: " + school.getSchoolStudent());


            }
        }
