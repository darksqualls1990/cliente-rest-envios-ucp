package com.cliente.rest.controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cliente.rest.model.ClienteDTO;
import com.cliente.rest.model.ws.cliente;

/**
 * @author dfsalinas
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  RestTemplate restTemplate;

  private static final String VIEW_INICIO = "client/inicio";
  private static final String VIEW_CLIENTE = "client/inicio :: form-data";
  private static final String VIEW_EDIT = "client/modal/edit-form :: default-form(tipo='%s')";

  /**
   * The Constant FILTRO_BUSQUEDA.
   */
  private static final String FILTRO_BUSQUEDA = "filtroBusqueda";

  private static final String CLIENTE = "cliente";


  @GetMapping(value = {"/init"})
  public ModelAndView init(Model model) {
    model.addAttribute("titulo", "Busqueda Cliente");
    model.addAttribute(FILTRO_BUSQUEDA, new ClienteDTO());
    model.addAttribute(CLIENTE,
      new ClienteDTO());
    return new ModelAndView(VIEW_INICIO);
  }


  @GetMapping(path = "/new")
  public ModelAndView newForm(@ModelAttribute ClienteDTO clienteDTO, Model model) {
    return new ModelAndView(VIEW_EDIT);
  }

  @PostMapping(value = "/acciones/create")
  public ResponseEntity<HashMap<String, Object>> create(@Valid ClienteDTO cliente) {
    cliente clienteWs = new cliente();
    convertirClienteDtoaCliente(cliente, clienteWs);
    HashMap<String, Object> responseMessage = new HashMap<>();
    try {

      HttpEntity<cliente> request = new HttpEntity<>(clienteWs);
      clienteWs = restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/clientes/"+ cliente.getCedula() ,HttpMethod.GET, request,cliente.class).getBody();

      if(!StringUtils.isEmpty(clienteWs.getCedula())){
        responseMessage.put("result","Ya existe el cliente");
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
      }
      clienteWs = restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/clientes", HttpMethod.POST, request, cliente.class).getBody();
    } catch (Exception e) {
      responseMessage.put("result", e.getMessage());
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
    responseMessage.put("result", "Correcto");
    return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
  }


  private void convertirClienteDtoaCliente(ClienteDTO clientDto, cliente cliente) {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    cliente.setCedula(clientDto.getCedula());
    cliente.setApellido(clientDto.getApellidos());
    cliente.setCorreo_electronico(clientDto.getCorreoElectronico());
    cliente.setEstado(clientDto.getEstado());
    cliente.setFecha_nacimiento(simpleDateFormat.format(clientDto.getFechaNacimiento()));
    cliente.setLugar_residencia(clientDto.getLugarResidencia());
    cliente.setNombre(clientDto.getNombres());
    cliente.setTelefono(clientDto.getTelefono());
    cliente.setId(clientDto.getId());
  }

  @PostMapping(value = "/filter")
  public Object filtrar(@Valid ClienteDTO clienteDto, Model model) {

    ClienteDTO clienteDTO = new ClienteDTO();
    model.addAttribute(FILTRO_BUSQUEDA, clienteDto);
    cliente clienteWs = new cliente();
    HashMap<String, Object> responseMessage = new HashMap<>();

    try {
      //
      HttpEntity<cliente> request = new HttpEntity<>(clienteWs);
       clienteWs = restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/clientes/"+ clienteDto.getCedula() ,HttpMethod.GET, request,cliente.class).getBody();

       if(StringUtils.isEmpty(clienteWs.getCedula())){
         responseMessage.put("result","No existe el cliente");
         return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
       }
      convertirClienteaClienteDto(clienteWs, clienteDTO);
    } catch (Exception e) {
      responseMessage.put("result", e.getMessage());
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }


    model.addAttribute(CLIENTE, clienteDTO);

    return new ModelAndView(VIEW_CLIENTE);

  }

  @PutMapping(value = "/acciones/edit")
  public ResponseEntity<HashMap<String, Object>> editar(@Valid ClienteDTO clienteDTO, BindingResult result, Model model) {
    cliente cliente = new cliente();
    HashMap<String, Object> responseMessage = new HashMap<>();
    if(StringUtils.isEmpty(clienteDTO.getCedula())){
      responseMessage.put("result","Busque el registro de envio por el numero de cedula");
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
    convertirClienteDtoaCliente(clienteDTO, cliente);
    try {
      HttpEntity<cliente> request = new HttpEntity<>(cliente);
      restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/clientes", HttpMethod.PUT, request, cliente.class);
    } catch (Exception e) {
      responseMessage.put("result", e.getMessage());
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    responseMessage.put("result", "Registro Modificado");
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
  }

  private void convertirClienteaClienteDto(cliente cliente, ClienteDTO clientDto) throws Exception {


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    clientDto.setCedula(cliente.getCedula());
    clientDto.setApellidos(cliente.getApellido());
    clientDto.setCorreoElectronico(cliente.getCorreo_electronico());
    clientDto.setEstado(cliente.getEstado());

    clientDto.setFechaNacimiento(simpleDateFormat.parse(cliente.getFecha_nacimiento()));
    clientDto.setLugarResidencia(cliente.getLugar_residencia());
    clientDto.setNombres(cliente.getNombre());
    clientDto.setTelefono(cliente.getTelefono());
    clientDto.setId(cliente.getId());
  }


}
