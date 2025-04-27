package com.gestion.empleados.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestion.empleados.entidades.Empleado;
import com.gestion.empleados.servicio.EmpleadoService;
import com.gestion.empleados.util.paginacion.PageRender;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping({ "/", "/listar", "" })
	public String listarEmpleados(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);
		PageRender<Empleado> pageRender = new PageRender<>("/lista", empleados);

		modelo.addAttribute("titulo", "Listado de empleados");
		modelo.addAttribute("empleados", empleados);
		modelo.addAttribute("page", pageRender);

		return "listar";
	}
}
