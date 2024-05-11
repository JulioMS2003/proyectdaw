package pe.edu.cibertec.proyectdaw.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyectdaw.model.bd.Distrito;
import pe.edu.cibertec.proyectdaw.model.bd.Empleado;
import pe.edu.cibertec.proyectdaw.model.dto.request.EmpleadoRequest;
import pe.edu.cibertec.proyectdaw.repository.EmpleadoRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmpleadoService implements IEmpleadoService{

    private EmpleadoRepository empleadoRepository;

    @Override
    public void guardarEmpleado(EmpleadoRequest empleadoRequest) throws Exception {
        empleadoRequest.setNomemp(empleadoRequest.getNomemp().trim());
        empleadoRequest.setApeemp(empleadoRequest.getApeemp().trim());
        empleadoRequest.setDireccion(empleadoRequest.getDireccion().trim());
        empleadoRequest.setEmail(empleadoRequest.getEmail().trim());
        empleadoRequest.setTelefono(empleadoRequest.getTelefono().trim());
        if(empleadoRequest.getFecnac() != null)
            empleadoRequest.setFecnac(this.unDiaMas(empleadoRequest.getFecnac()));
        if(empleadoRequest.getDireccion().isEmpty())
            empleadoRequest.setDireccion(null);

        if(empleadoRequest.getNomemp() == null || empleadoRequest.getNomemp().isEmpty())
            throw new Exception("Ingresar nombres del empleado");
        if(empleadoRequest.getNomemp().length() > 50)
            throw new Exception("Nombres superan el límite de 50 caracteres");
        if(empleadoRequest.getApeemp() == null || empleadoRequest.getApeemp().isEmpty())
            throw new Exception("Ingresar apellidos del empleado");
        if(empleadoRequest.getApeemp().length() > 50)
            throw new Exception("Apellidos superan el límite de 50 caracteres");
        if(empleadoRequest.getFecnac() == null)
            throw new Exception("Ingresar fecha de nacimiento del empleado");
        if(empleadoRequest.getEmail() == null || empleadoRequest.getEmail().isEmpty())
            throw new Exception("Ingresar email del empleado");
        if(empleadoRequest.getEmail().length() > 50)
            throw new Exception("Email supera el límite de 50 caracteres");
        if(empleadoRequest.getTelefono() == null || empleadoRequest.getTelefono().isEmpty())
            throw new Exception("Ingresar teléfono del empleado");
        if(empleadoRequest.getTelefono().length() != 9 || !empleadoRequest.getTelefono().matches("\\d+"))
            throw new Exception("Teléfono debe contener 9 dígitos");
        if(empleadoRequest.getDireccion() != null && empleadoRequest.getDireccion().length() > 50)
            throw new Exception("Dirección supera el límite de 50 caracteres");
        if(empleadoRequest.getDistritoid() == -1)
            throw new Exception("Seleccionar un distrito");

        Empleado empleado = new Empleado();
        if(empleadoRequest.getEmpleadoid() > 0)
            empleado.setEmpleadoid(empleadoRequest.getEmpleadoid());
        empleado.setNomemp(empleadoRequest.getNomemp());
        empleado.setApeemp(empleadoRequest.getApeemp());
        empleado.setEstado(empleadoRequest.getEstado());
        empleado.setDisponible(empleadoRequest.getDisponible());
        empleado.setFecnac(empleadoRequest.getFecnac());
        empleado.setEmail(empleadoRequest.getEmail());
        empleado.setTelefono(empleadoRequest.getTelefono());
        empleado.setDireccion(empleadoRequest.getDireccion());
        Distrito distrito = new Distrito();
        distrito.setDistritoid(empleadoRequest.getDistritoid());
        empleado.setDistrito(distrito);
        empleadoRepository.save(empleado);
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer empleadoid) {
        Empleado empleado = null;
        Optional<Empleado> optional = empleadoRepository.findById(empleadoid);
        if(optional.isPresent())
            empleado = optional.get();
        return empleado;
    }

    @Override
    public List<Empleado> listarEmpleado() {
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> listarEmpleadosOrdenadosPorApellido() {
        return empleadoRepository.findAllByOrderByApeemp();
    }

    @Override
    public List<Empleado> paginacionEmpleados(Integer nropagina) {
        return empleadoRepository.paginacionEmpleados((nropagina - 1) * 20);
    }

    @Override
    public List<Empleado> listarEmpleadosPorEstadoYDisponibilidad(Boolean estado, Boolean disponible) {
        return empleadoRepository.findAllByEstadoAndDisponibleOrderByApeemp(estado, disponible);
    }

    private Date unDiaMas(Date fecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
