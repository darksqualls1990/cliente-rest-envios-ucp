package com.cliente.rest.controllers;

import com.cliente.rest.model.RegistroMercanciaDTO;
import com.cliente.rest.model.ws.registro_mercancia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/registromercancia")
public class RegistroMercanciaController {

  @Autowired
  RestTemplate restTemplate;

  private static final String VIEW_INICIO = "register/inicio";
  private static final String VIEW_REGISTRO_MERCANCIA = "register/inicio :: form-data";
  private static final String VIEW_EDIT = "register/modal/edit-form :: default-form(tipo='%s')";

  /**
   * The Constant FILTRO_BUSQUEDA.
   */
  private static final String FILTRO_BUSQUEDA = "filtroBusqueda";

  private static final String REGISTRO_MERCANCIA = "registroMercancia";

  @GetMapping(value = {"/init"})
  public ModelAndView init(Model model) {
    model.addAttribute("titulo", "Busqueda Registro Mercancia");
    model.addAttribute(FILTRO_BUSQUEDA, new RegistroMercanciaDTO());
    model.addAttribute(REGISTRO_MERCANCIA,
      new RegistroMercanciaDTO());
    return new ModelAndView(VIEW_INICIO);
  }

  /**
   * Filter.
   *
   * @param filtroBusqueda the filtro busqueda
   * @param model          the model
   * @param session        the session
   * @return the model and view
   * @author hiramirezc
   */
  @PostMapping(value = {"/filter"})
  public final ModelAndView filter(@ModelAttribute("filtroBusqueda") RegistroMercanciaDTO filtroBusqueda, Model model, HttpSession session) {

    model.addAttribute(FILTRO_BUSQUEDA, filtroBusqueda);

    RegistroMercanciaDTO registroMercanciaDTO = new RegistroMercanciaDTO();
    registro_mercancia registro_mercancia = new registro_mercancia();

    try {
      registro_mercancia = restTemplate.getForEntity("http://apienvioshd.azurewebsites.net/api/registros_mercancias/" + filtroBusqueda.getNroGuia(), registro_mercancia.class).getBody();
      convertirRegistroMercanciaaRegistroMerccanciaDto(registro_mercancia, registroMercanciaDTO);
    }catch (Exception e){
      model.addAttribute(REGISTRO_MERCANCIA,
        null);
      return new ModelAndView(VIEW_REGISTRO_MERCANCIA);
    }
    model.addAttribute(REGISTRO_MERCANCIA,
      registroMercanciaDTO);
    return new ModelAndView(VIEW_REGISTRO_MERCANCIA);
  }

  @GetMapping(path = "/new")
  public ModelAndView newForm(@ModelAttribute RegistroMercanciaDTO registroMercanciaDTO, Model model) {
    return new ModelAndView(VIEW_EDIT);
  }

  @PostMapping(value = "/acciones/create")
  public ResponseEntity<HashMap<String, Object>> create(@Valid RegistroMercanciaDTO registroMercanciaDTO, BindingResult result, Model model) {
    registro_mercancia registro_mercancia = new registro_mercancia();
    convertirRegistroMercanciaDtoaRegistroMerccancia(registroMercanciaDTO, registro_mercancia);
    HashMap<String, Object> responseMessage = new HashMap<>();
    try {
      HttpEntity<registro_mercancia> request = new HttpEntity<>(registro_mercancia);
      registro_mercancia = restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/registros_mercancias", HttpMethod.POST, request, registro_mercancia.class).getBody();
    } catch (Exception e) {
      responseMessage.put("result", e.getMessage());
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    responseMessage.put("guia", registro_mercancia.getNro_guia());
    responseMessage.put("valor", registro_mercancia.getValor_numerico());
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
  }

  @PutMapping(value = "/acciones/edit")
  public ResponseEntity<HashMap<String, Object>> editar(@Valid RegistroMercanciaDTO registroMercanciaDTO, BindingResult result, Model model) {
    registro_mercancia registro_mercancia = new registro_mercancia();
    HashMap<String, Object> responseMessage = new HashMap<>();
    if(StringUtils.isEmpty(registroMercanciaDTO.getNroGuia())){
      responseMessage.put("result","Busque el registro de envio por el numero de guia");
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
    convertirRegistroMercanciaDtoaRegistroMerccancia(registroMercanciaDTO, registro_mercancia);
    try {
      HttpEntity<registro_mercancia> request = new HttpEntity<>(registro_mercancia);
      restTemplate.exchange("http://apienvioshd.azurewebsites.net/api/registros_mercancias", HttpMethod.PUT, request, registro_mercancia.class);
    } catch (Exception e) {
      responseMessage.put("result", e.getMessage());
      return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    responseMessage.put("result", "Registro Modificado");
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
  }

  private void convertirRegistroMercanciaDtoaRegistroMerccancia(RegistroMercanciaDTO registroMercanciaDTO, registro_mercancia registro_mercancia) {

    registro_mercancia.setCedula_cliente(registroMercanciaDTO.getCedulaCliente());

    registro_mercancia.setNombre_destinatario(registroMercanciaDTO.getNombreDestinatario());
    registro_mercancia.setCedula_destinatario(registroMercanciaDTO.getCedulaDestinatario());
    registro_mercancia.setApellido_destinatario(registroMercanciaDTO.getApellidoDestinatario());

    registro_mercancia.setCiudad_destino(registroMercanciaDTO.getCiudadDestino());
    registro_mercancia.setCiudad_origen(registroMercanciaDTO.getCiudadOrigen());

    registro_mercancia.setEstado_paquete(registroMercanciaDTO.getEstadoPaquete());
    registro_mercancia.setID(registroMercanciaDTO.getID());

    registro_mercancia.setNro_guia(registroMercanciaDTO.getNroGuia());
    registro_mercancia.setPeso_paquete(registroMercanciaDTO.getPesoPaquete());
    registro_mercancia.setValor_asegurado(registroMercanciaDTO.getValorAsegurado());
    registro_mercancia.setValor_numerico(registroMercanciaDTO.getValorNumerico());


  }

  private void convertirRegistroMercanciaaRegistroMerccanciaDto(registro_mercancia registro_mercancia,RegistroMercanciaDTO registroMercanciaDTO) {

    registroMercanciaDTO.setCedulaCliente(registro_mercancia.getCedula_cliente());

    registroMercanciaDTO.setNombreDestinatario(registro_mercancia.getNombre_destinatario()) ;
    registroMercanciaDTO.setCedulaDestinatario(registro_mercancia.getCedula_destinatario());
    registroMercanciaDTO.setApellidoDestinatario(registro_mercancia.getApellido_destinatario());

    registroMercanciaDTO.setCiudadDestino(registro_mercancia.getCiudad_destino());
    registroMercanciaDTO.setCiudadOrigen(registro_mercancia.getCiudad_origen());

    registroMercanciaDTO.setEstadoPaquete(registro_mercancia.getEstado_paquete());
    registroMercanciaDTO.setID(registro_mercancia.getID());

    registroMercanciaDTO.setNroGuia(registro_mercancia.getNro_guia());
    registroMercanciaDTO.setPesoPaquete(registro_mercancia.getPeso_paquete());
    registroMercanciaDTO.setValorAsegurado(registro_mercancia.getValor_asegurado());
    registroMercanciaDTO.setValorNumerico(registro_mercancia.getValor_numerico());
  }

}
