package service.user;

import user.Gender;
import user.User;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{

    private static final String FILE_PATH = "users.txt";

    @Override
    public void createAccount(User user) {
        if (isUserExists(user.getTckn())){
            System.err.println("User already exists");
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(user.getUsername());
        sb.append("_");
        sb.append(user.getPassword());
        sb.append("_");
        sb.append(user.getName());
        sb.append("_");
        sb.append(user.getSurname());
        sb.append("_");
        sb.append(user.getTckn());
        sb.append("_");
        sb.append(user.getGender());

        writeToFile(sb.toString());
    }

    @Override
    public User login(String username, String password) {
        Optional<User> user = readFromFile().stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();
        if (user.isPresent()){
            return user.get();
        }else{
            System.err.println("User not found");
            return null;
        }
    }

    public boolean isUserExists(String tckn){
        Optional<User> user = readFromFile().stream().filter(u -> u.getTckn().equals(tckn)).findFirst();
        return user.isPresent();
    }

    private List<User> readFromFile() {
        List<String> lines = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(FILE_PATH);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        List<User> users = new ArrayList<>();
        for (String s : lines){
            String[] line = s.split("_");
            User user = new User(line[0],line[1],line[2],line[3],line[4], Gender.valueOf(line[5]));
            users.add(user);
        }
        return users;
    }

    private void writeToFile(String line) {
        try{
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(line + "\n");
            writer.close();
            System.out.println("Account has been created.");
        }catch (IOException e){
            System.err.println("An error accured. ");
            e.printStackTrace();
        }
    }
}
