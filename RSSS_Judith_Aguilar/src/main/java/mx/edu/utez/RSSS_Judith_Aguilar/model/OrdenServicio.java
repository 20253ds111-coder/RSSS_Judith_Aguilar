package mx.edu.utez.RSSS_Judith_Aguilar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordenesServicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double costo;

    @Enumerated(EnumType.STRING)
    private OrdenServicioTipo tipo;


    @Enumerated(EnumType.STRING)
    private OrdenServicioEstado estado;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
}
