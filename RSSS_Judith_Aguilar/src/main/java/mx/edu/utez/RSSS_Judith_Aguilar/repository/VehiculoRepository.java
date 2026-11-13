package mx.edu.utez.RSSS_Judith_Aguilar.repository;

import mx.edu.utez.RSSS_Judith_Aguilar.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findByActivoTrue();
}
