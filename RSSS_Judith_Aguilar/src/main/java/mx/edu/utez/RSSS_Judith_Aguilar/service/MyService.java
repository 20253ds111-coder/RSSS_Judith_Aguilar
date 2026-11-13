package mx.edu.utez.RSSS_Judith_Aguilar.service;

import mx.edu.utez.RSSS_Judith_Aguilar.controller.dto.CrearOrdenServicioDTO;
import mx.edu.utez.RSSS_Judith_Aguilar.controller.dto.CrearVehiculoDTO;
import mx.edu.utez.RSSS_Judith_Aguilar.model.OrdenServicio;
import mx.edu.utez.RSSS_Judith_Aguilar.model.OrdenServicioEstado;
import mx.edu.utez.RSSS_Judith_Aguilar.model.Vehiculo;
import mx.edu.utez.RSSS_Judith_Aguilar.repository.OrdenServicioRepository;
import mx.edu.utez.RSSS_Judith_Aguilar.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    private final VehiculoRepository vehiculoRepository;
    private final OrdenServicioRepository ordenServicioRepository;

    public MyService(VehiculoRepository vehiculoRepository, OrdenServicioRepository ordenServicioRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.ordenServicioRepository = ordenServicioRepository;
    }

  //registra :D
    public Vehiculo registrarVehiculo(CrearVehiculoDTO payload) {
        Vehiculo vehiculoPlaca(payload.getPlaca());

        Vehiculo.setActivo(true);
        return vehiculoRepository.save(vehiculo);
    }


    // Listar
    public List<Vehiculo> listarTodosVehiculos() {

        return vehiculoRepository.findAll();
    }

    // listar activos
    public List<Vehiculo> listarVehiculosDisponibles() {
        return vehiculoRepository.findByActivoTrue();
    }

    // registrar orden
    public OrdenServicio registrarOrdenServicio(CrearOrdenServicioDTO payload) {
        Vehiculo vehiculo = vehiculoRepository.findById(payload.getIdVehiculo())
                .orElseThrow(() -> new CustomException("El registro del vehiculo no existe"));

        // Validar activo
        if (!vehiculo.isActivo()) {
            throw new CustomException("No se encuentra activo");
        }

        vehiculo.setActivo(false);
        vehiculoRepository.save(vehiculo);

        OrdenServicio ordenServicio= new OrdenServicio();
        ordenServicio.setDescripcion(payload.getDescripcion());

        ordenServicio.setEstado(OrdenServicioEstado.RECIBIDA);
        ordenServicio.setVehiculo(vehiculo);

        return ordenServicioRepository.save(ordenServicio);
    }

    // cancelar
    public OrdenServicio cancelar(Long idOrdenServicio) {
        OrdenServicio ordenServicio = ordenServicioRepository.findById(idOrdenServicio)
                .orElseThrow(() -> new CustomException("El registro no existe"));

        if (ordenServicio.getEstado() != OrdenServicioEstado.RECIBIDA) {
            throw new CustomException("Solo se puede modificar un registro activo");
        }

        ordenServicio.setEstado(OrdenServicioEstado.CANCELADA);

        Vehiculo vehiculo = ordenServicio.getVehiculo();
        vehiculo.setActivo(true);
        vehiculoRepository.save(vehiculo);

        return vehiculoRepository.save(vehiculo);
    }

    // consultar por estado
    public List<OrdenServicio> listarOrdenesServicioPorEstado(OrdenServicioEstado estado) {
        return ordenServicioRepository.findByEstado(estado);
    }
}
