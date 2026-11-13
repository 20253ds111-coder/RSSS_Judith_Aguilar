package mx.edu.utez.RSSS_Judith_Aguilar.repository;

import mx.edu.utez.RSSS_Judith_Aguilar.model.OrdenServicio;
import mx.edu.utez.RSSS_Judith_Aguilar.model.OrdenServicioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {

    List<OrdenServicio> findByEstado(OrdenServicioEstado estado);


}
