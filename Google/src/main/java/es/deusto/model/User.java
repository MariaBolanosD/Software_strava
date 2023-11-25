package es.deusto.model;

import jakarta.persistence.*;

// "User" is a reserved word in many DBs. We can programmatically provide a name for the table to avoid problems.
@Table(name="userTable")
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String firstName;
    //private String lastName;
    
    @Column(unique = true)
    private String email;
    private String password;
      
    public User () {}
    
    public User (String password, String email) {
    	this.password = password;
    	this.email=email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
