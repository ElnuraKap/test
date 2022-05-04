package repositories;

import config.DataBaseConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private final Connection connection;


    public StudentsRepository() throws SQLException {
       connection = new DataBaseConnection().getConnection();
    }

    public void createTable(){
        String query = """
    create table if not exists students(
    id serial primary key,
    name varchar(50)not null,
    age smallint not null);
    """;
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
           throw new RuntimeException();
        }
    }

    public void save (Student student){
        String query = """
                insert into students(name , age ) values (?,?)
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
           preparedStatement.setString(1,student.getName());
           preparedStatement.setByte(2,student.getAge());
           preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAll(){
        String query = "select * from students ;";
        List<Student> allStudents = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            while(resultSet.next()) {
               Student student = new Student();
               student.setId(resultSet.getLong(1));
               student.setName(resultSet.getString("name"));
               student.setAge(resultSet.getByte(3));
               allStudents.add(student);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStudents;
    }

    public void deleteById(Long id){
     String query = "delete from students where id = ?;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public Student findById(Long id) {
         String query = "select * from students where id = ?;";
         Student student = new Student();
         try {
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setLong(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
             student.setId(resultSet.getLong(1));
             student.setName(resultSet.getString("name"));
             student.setAge(resultSet.getByte(3));
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return student;
    }

    public void deleteAll () {
        String query = "truncate table students ;";
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
            System.out.println("You have successfully deleted all students in student ");
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    public void update (Long id ,Student newStudent){
        String query = """
                update students 
                set name = ?,
                set age = ?,
                where id = ?
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
           preparedStatement.setString(1,newStudent.getName());
           preparedStatement.setByte(2,newStudent.getAge());
           preparedStatement.setLong(3,id);
           preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean existsById(Long id){
        String sqlQuery = """
                select case when count(*) > 0 then true else false end
                from students 
                where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getBoolean(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
