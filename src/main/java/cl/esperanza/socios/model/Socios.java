package cl.esperanza.socios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "socios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Socios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 13, nullable = true) 
    private String run;

    @Column(unique = true, length = 13, nullable = true) 
    private int telefono;

    @Column(nullable = true)
    private String nombre;

    @Column(nullable = true)
    private String apellido;

    @Column(nullable = true)
    private String direccion;

    @Column(nullable = false)
    private String correo;
}
